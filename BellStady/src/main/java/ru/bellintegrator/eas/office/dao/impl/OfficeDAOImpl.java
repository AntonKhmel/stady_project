package ru.bellintegrator.eas.office.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.office.dao.OfficeDAO;
import ru.bellintegrator.eas.office.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * @author Хмель А.В.
 * class DAO for work with office
 */
@Repository
public class OfficeDAOImpl implements OfficeDAO {
    private final Logger LOG = LoggerFactory.getLogger(OfficeDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    // get a list of offices by filters orgId, name, phone, isActive
    @Override
    public List<Office> filterOffices(Office office) throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Office> criteria = builder.createQuery(Office.class);

            Root<Office> root = criteria.from(Office.class);
            if (office != null) {
                if (office.getOrganization().getId() != 0) {
                    criteria.where(builder.equal(root.get("organization").get("id"), office.getOrganization().getId()));
                    if (office.getName() != null && !office.getName().isEmpty()) {
                        criteria.where(builder.equal(root.get("name"), office.getName()));
                        if (office.getPhone() != null && office.getPhone().getNumber() != null && !office.getPhone().getNumber().isEmpty()) {
                            criteria.where(builder.equal(root.get("phone").get("number"), office.getPhone().getNumber()));
                            if (office.getIsActive()) {
                                criteria.where(builder.equal(root.get("isActive"), office.getIsActive()));
                            }
                        }
                    }
                }
            }

            TypedQuery<Office> query = entityManager.createQuery(criteria);
            List<Office> officeList = query.getResultList();

            LOG.info("getting list officces by filter");

            return officeList != null ? officeList : Collections.emptyList();
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "filterOffices object the OfficeDAOImpl");
        }
    }

    // delete the office
    @Override
    public boolean deleteOfficeById(int id) throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Office> criteria = builder.createCriteriaDelete(Office.class);
            Root<Office> root = criteria.from(Office.class);
            criteria.where(builder.equal(root.get("id"), id));
            int result = entityManager.createQuery(criteria).executeUpdate();

            LOG.info("delete the office by id");

            return result > 0 ? true : false;
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "deleteOfficeById object the OfficeDAOImpl");
        }
    }

     // update the office
    @Override
    public void updateOffice(Office office) throws DataAccessError {
        try {
            Office getOffice = entityManager.find(Office.class, office.getId());
            getOffice.setName(office.getName());
            getOffice.getAddress().getCountry().setName(office.getAddress().getCountry().getName());
            getOffice.getAddress().getCountry().setCode(office.getAddress().getCountry().getCode());
            getOffice.getAddress().getRegion().setName(office.getAddress().getRegion().getName());
            getOffice.getAddress().getCity().setName(office.getAddress().getCity().getName());
            getOffice.getAddress().getStreet().setName(office.getAddress().getStreet().getName());
            getOffice.getAddress().getHouse().setNumberHouse(office.getAddress().getHouse().getNumberHouse());
            getOffice.getAddress().getHouse().setNumberFlat(office.getAddress().getHouse().getNumberFlat());
            getOffice.getAddress().getHouse().setNumberOffice(office.getAddress().getHouse().getNumberOffice());
            getOffice.getPhone().setNumber(office.getPhone().getNumber());
            getOffice.setIsActive(office.getIsActive());
            entityManager.merge(getOffice);

            LOG.info("update the office");
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "updateOffice object the OfficeDAOImpl");
        }
    }

    // save the office
    @Override
    public void saveOffice(Office office) throws DataAccessError {
        try {
            entityManager.persist(office);

            LOG.info("save the office");
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "saveOffice object the OfficeDAOImpl");
        }
    }
}

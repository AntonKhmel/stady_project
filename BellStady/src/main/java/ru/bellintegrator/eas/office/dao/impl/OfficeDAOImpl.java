package ru.bellintegrator.eas.office.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.office.dao.OfficeDAO;
import ru.bellintegrator.eas.office.model.Office;
import ru.bellintegrator.eas.office.model.view.OfficeView;

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
    public List<Office> filterOffices(OfficeView officeView) throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Office> criteria = builder.createQuery(Office.class);

            Root<Office> root = criteria.from(Office.class);
            if (officeView != null) {
                if (officeView.getOrganizationView().getId() != 0) {
                    criteria.where(builder.equal(root.get("organization").get("id"), officeView.getOrganizationView().getId()));
                    if (officeView.getName() != null && !officeView.getName().isEmpty()) {
                        criteria.where(builder.equal(root.get("name"), officeView.getName()));
                        if (officeView.getPhoneView() != null && officeView.getPhoneView().getNumber() != null && !officeView
                                .getPhoneView().getNumber().isEmpty()) {
                            criteria.where(builder.equal(root.get("phone").get("number"), officeView.getPhoneView().getNumber()));
                            if (officeView.getIsActive()) {
                                criteria.where(builder.equal(root.get("isActive"), officeView.getIsActive()));
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
    public void updateOffice(OfficeView officeView) throws DataAccessError {
        try {
            Office office = entityManager.find(Office.class, officeView.getId());
            office.setName(officeView.getName());
            office.getAddress().getCountry().setName(officeView.getAddressView().getCountryView().getName());
            office.getAddress().getRegion().setName(officeView.getAddressView().getRegionView().getName());
            office.getAddress().getCity().setName(officeView.getAddressView().getCityView().getName());
            office.getAddress().getStreet().setName(officeView.getAddressView().getStreetView().getName());
            office.getAddress().getHouse().setNumberHouse(officeView.getAddressView().getHouseView().getNumberHouse());
            office.getAddress().getHouse().setNumberFlat(officeView.getAddressView().getHouseView().getNumberFlat());
            office.getAddress().getHouse().setNumberOffice(officeView.getAddressView().getHouseView().getNumberOffice());
            office.getPhone().setNumber(officeView.getPhoneView().getNumber());
            office.setIsActive(officeView.getIsActive());
            entityManager.merge(office);

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

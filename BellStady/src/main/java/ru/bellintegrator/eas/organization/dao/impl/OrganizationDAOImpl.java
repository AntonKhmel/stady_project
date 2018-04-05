package ru.bellintegrator.eas.organization.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.dao.OrganizationDAO;
import ru.bellintegrator.eas.organization.model.Organization;
import ru.bellintegrator.eas.organization.model.view.OrganizationView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * @author Хмель А.В.
 * class DAO for work with organization
 */
@Repository
public class OrganizationDAOImpl implements OrganizationDAO {
    private final Logger LOG = LoggerFactory.getLogger(OrganizationDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

     // get a list of organizations by filters name, inn, isActive
    @Override
    public List<Organization> filterOrganizations(OrganizationView organizationView) throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

            Root<Organization> root = criteria.from(Organization.class);
            if (organizationView != null) {
                if (organizationView.getName() != null && !organizationView.getName().isEmpty()) {
                    criteria.where(builder.equal(root.get("name"), organizationView.getName()));
                    if (organizationView.getRequisiteView() != null && organizationView.getRequisiteView().getInn() != null
                            && !organizationView.getRequisiteView().getInn().isEmpty()) {
                        criteria.where(builder.equal(root.get("recvisit").get("inn"), organizationView.getRequisiteView().getInn()));
                        if (organizationView.getIsActive()) {
                            criteria.where(builder.equal(root.get("isActive"), organizationView.getIsActive()));
                        }
                    }
                }
            }

            TypedQuery<Organization> query = entityManager.createQuery(criteria);
            List<Organization> organizationList = query.getResultList();

            LOG.info("getting list organizations by filter");

            return organizationList != null ? organizationList : Collections.emptyList();
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "filterOrganizations object the OrganizationDAOImpl");
        }
    }

    // get organization by id
    @Override
    @SuppressWarnings("unchecked")
    public Organization getOrganizationById(int id) throws DataAccessError {
        try {
            Organization organization = entityManager.find(Organization.class, id);

            LOG.info("get the organization by id");

            return organization;
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "getOrganizationById object the OrganizationDAOImpl");
        }
    }

    // update the organization
    @Override
    public void updateOrganization(OrganizationView organizationView) throws DataAccessError {
        try {
            Organization organization = entityManager.find(Organization.class, organizationView.getId());
            organization.setName(organizationView.getName());
            organization.setFullName(organizationView.getFullName());
            organization.getRequisite().setInn(organizationView.getRequisiteView().getInn());
            organization.getRequisite().setCpp(organizationView.getRequisiteView().getCpp());
            organization.getAddress().getCountry().setName(organizationView.getAddressView().getCountryView().getName());
            organization.getAddress().getRegion().setName(organizationView.getAddressView().getRegionView().getName());
            organization.getAddress().getCity().setName(organizationView.getAddressView().getCityView().getName());
            organization.getAddress().getStreet().setName(organizationView.getAddressView().getStreetView().getName());
            organization.getAddress().getHouse().setNumberHouse(organizationView.getAddressView().getHouseView().getNumberHouse());
            organization.getAddress().getHouse().setNumberFlat(organizationView.getAddressView().getHouseView().getNumberFlat());
            organization.getAddress().getHouse().setNumberOffice(organizationView.getAddressView().getHouseView().getNumberOffice());
            organization.getPhone().setNumber(organizationView.getPhoneView().getNumber());
            organization.setIsActive(organizationView.getIsActive());
            entityManager.merge(organization);

            LOG.info("update the organization");
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "updateOrganization object the OrganizationDAOImpl");
        }
    }

    // save the organization
    @Override
    public void saveOrganization(Organization organization) throws DataAccessError {
        try {
            entityManager.persist(organization);

            LOG.info("save the organization");
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "saveOrganization object the OrganizationDAOImpl");
        }
    }
}


package ru.bellintegrator.eas.organization.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.dao.OrganizationDAO;
import ru.bellintegrator.eas.organization.model.Organization;

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
    public List<Organization> filterOrganizations(Organization organization) throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

            Root<Organization> root = criteria.from(Organization.class);
            if (organization != null) {
                if (organization.getName() != null && !organization.getName().isEmpty()) {
                    criteria.where(builder.equal(root.get("name"), organization.getName()));
                    if (organization.getRequisite() != null && organization.getRequisite().getInn() != null
                            && !organization.getRequisite().getInn().isEmpty()) {
                        criteria.where(builder.equal(root.get("recvisit").get("inn"), organization.getRequisite().getInn()));
                        if (organization.getIsActive()) {
                            criteria.where(builder.equal(root.get("isActive"), organization.getIsActive()));
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
    public void updateOrganization(Organization organization) throws DataAccessError {
        try {
            Organization getOrganization = entityManager.find(Organization.class, organization.getId());
            getOrganization.setName(organization.getName());
            getOrganization.setFullName(organization.getFullName());
            getOrganization.getRequisite().setInn(organization.getRequisite().getInn());
            getOrganization.getRequisite().setCpp(organization.getRequisite().getCpp());
            getOrganization.getAddress().getCountry().setName(organization.getAddress().getCountry().getName());
            getOrganization.getAddress().getCountry().setCode(organization.getAddress().getCountry().getCode());
            getOrganization.getAddress().getRegion().setName(organization.getAddress().getRegion().getName());
            getOrganization.getAddress().getCity().setName(organization.getAddress().getCity().getName());
            getOrganization.getAddress().getStreet().setName(organization.getAddress().getStreet().getName());
            getOrganization.getAddress().getHouse().setNumberHouse(organization.getAddress().getHouse().getNumberHouse());
            getOrganization.getAddress().getHouse().setNumberFlat(organization.getAddress().getHouse().getNumberFlat());
            getOrganization.getAddress().getHouse().setNumberOffice(organization.getAddress().getHouse().getNumberOffice());
            getOrganization.getPhone().setNumber(organization.getPhone().getNumber());
            getOrganization.setIsActive(organization.getIsActive());
            entityManager.merge(getOrganization);

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


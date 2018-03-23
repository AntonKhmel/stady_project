package ru.bellintegrator.eas.organization.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.dao.OrganizationDAO;
import ru.bellintegrator.eas.organization.model.Organization;
import ru.bellintegrator.eas.organization.service.OrganizationService;

import java.util.List;

/**
 * @author Хмель А.В.
 * class Service for object the Organization by work with business logic
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final Logger LOG = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    OrganizationDAO organizationDAO;

    // calls the DAO object method on filters name, inn, isActive
    @Transactional(readOnly = true)
    @Override
    public List<Organization> filterOrganizations(Organization organization) throws DataAccessError {
        LOG.info("call method DAO for getting list organizations by filter");

        return organizationDAO.filterOrganizations(organization);
    }

    // calls the DAO object method for get organization by id
    @Transactional(readOnly = true)
    @Override
    public Organization getOrganizationById(int id) throws DataAccessError {
        LOG.info("call method DAO for get the organization by id");

        return organizationDAO.getOrganizationById(id);
    }

    // calls the DAO object method for update organization
    @Transactional()
    @Override
    public void updateOrganization(Organization organization) throws DataAccessError {
        organizationDAO.updateOrganization(organization);

        LOG.info("call method DAO for update the organization");
    }

    // calls the DAO object method for save organization
    @Transactional()
    @Override
    public void saveOrganization(Organization organization) throws DataAccessError {
        organizationDAO.saveOrganization(organization);

        LOG.info("call method DAO for save the organization");
    }
}

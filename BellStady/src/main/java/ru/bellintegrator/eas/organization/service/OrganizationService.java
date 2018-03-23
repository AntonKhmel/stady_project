package ru.bellintegrator.eas.organization.service;

import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.Organization;

import java.util.List;

/**
 * @author Хмель А.В.
 */
public interface OrganizationService {
    /**
     * @param organization
     * @return List<Organization>
     * @throws DataAccessError If an exception access data
     */
    List<Organization> filterOrganizations(Organization organization) throws DataAccessError;


    /**
     * @param id
     * @return Organization
     * @throws DataAccessError If an exception access data
     */
    Organization getOrganizationById(int id) throws DataAccessError;

    /**
     * @param organization
     * @throws DataAccessError If an exception access data
     */
    void updateOrganization(Organization organization) throws DataAccessError;

    /**
     * @param organization
     * @throws DataAccessError If an exception access data
     */
    void saveOrganization(Organization organization) throws DataAccessError;
}

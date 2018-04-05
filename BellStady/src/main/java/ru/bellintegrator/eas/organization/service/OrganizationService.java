package ru.bellintegrator.eas.organization.service;

import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.view.OrganizationView;

import java.util.List;

/**
 * @author Хмель А.В.
 */
public interface OrganizationService {
    /**
     * @param organizationView
     * @return List<OrganizationView>
     * @throws DataAccessError If an exception access data
     */
    List<OrganizationView> filterOrganizations(OrganizationView organizationView) throws DataAccessError;


    /**
     * @param id
     * @return OrganizationView
     * @throws DataAccessError If an exception access data
     */
    OrganizationView getOrganizationById(int id) throws DataAccessError;

    /**
     * @param organizationView
     * @throws DataAccessError If an exception access data
     */
    void updateOrganization(OrganizationView organizationView) throws DataAccessError;

    /**
     * @param organizationView
     * @throws DataAccessError If an exception access data
     */
    void saveOrganization(OrganizationView organizationView) throws DataAccessError;
}

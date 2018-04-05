package ru.bellintegrator.eas.authentication.service;

import ru.bellintegrator.eas.authentication.model.view.ActivationView;
import ru.bellintegrator.eas.authentication.model.view.AuthenticationView;
import ru.bellintegrator.eas.exception.DataAccessError;

/**
 * @author Хмель А.В.
 */
public interface AuthenticationService {
    /**
     * @param authenticationView
     * @throws DataAccessError If an exception access data
     */
    void registration(AuthenticationView authenticationView) throws DataAccessError;

    /**
     * @return Activation
     * @throws DataAccessError If an exception access data
     */
    ActivationView sendActivationCode() throws DataAccessError;

    /**
     * @param authenticationView
     * @return boolean
     * @throws DataAccessError If an exception access data
     */
    boolean checkAuthentication(AuthenticationView authenticationView) throws DataAccessError;
}

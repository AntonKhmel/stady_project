package ru.bellintegrator.eas.authentication.dao;

import ru.bellintegrator.eas.authentication.model.Authentication;
import ru.bellintegrator.eas.authentication.model.view.ActivationView;
import ru.bellintegrator.eas.authentication.model.view.AuthenticationView;
import ru.bellintegrator.eas.exception.DataAccessError;

/**
 * @author Хмель А.В.
 */
public interface AuthenticationDAO {
    /**
     * @param authentication
     * @throws DataAccessError If an exception access data
     */
    void registration(Authentication authentication) throws DataAccessError;

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

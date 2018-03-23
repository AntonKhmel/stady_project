package ru.bellintegrator.eas.authentication.dao;

import ru.bellintegrator.eas.authentication.model.Activation;
import ru.bellintegrator.eas.authentication.model.Authentication;
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
    Activation sendActivationCode() throws DataAccessError;

    /**
     * @param authentication
     * @return boolean
     * @throws DataAccessError If an exception access data
     */
    boolean checkAuthentication(Authentication authentication) throws DataAccessError;
}

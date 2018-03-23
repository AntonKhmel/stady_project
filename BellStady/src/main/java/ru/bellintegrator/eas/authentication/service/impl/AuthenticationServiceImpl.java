package ru.bellintegrator.eas.authentication.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.authentication.dao.AuthenticationDAO;
import ru.bellintegrator.eas.authentication.model.Activation;
import ru.bellintegrator.eas.authentication.model.Authentication;
import ru.bellintegrator.eas.authentication.service.AuthenticationService;
import ru.bellintegrator.eas.exception.DataAccessError;

/**
 * @author Хмель А.В.
 * class Service for object authentication by work with business logic
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private AuthenticationDAO authenticationDAO;

    //calls the DAO object method for registration
    @Override
    @Transactional()
    public void registration(Authentication authentication) throws DataAccessError {
        authenticationDAO.registration(authentication);

        LOG.info("call method DAO for save data new registration");
    }

    // calls the DAO object method for send activation code
    @Override
    public Activation sendActivationCode() throws DataAccessError {
        LOG.info("call method DAO for send activation code");

        return authenticationDAO.sendActivationCode();
    }

    // calls the DAO object method for check authentication
    @Override
    @Transactional(readOnly = true)
    public boolean checkAuthentication(Authentication authentication) throws DataAccessError {
        LOG.info("call method DAO for check authentication");

        return authenticationDAO.checkAuthentication(authentication);
    }
}

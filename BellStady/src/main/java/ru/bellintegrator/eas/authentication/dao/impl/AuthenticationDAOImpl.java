package ru.bellintegrator.eas.authentication.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.authentication.dao.AuthenticationDAO;
import ru.bellintegrator.eas.authentication.model.Authentication;
import ru.bellintegrator.eas.authentication.model.view.ActivationView;
import ru.bellintegrator.eas.authentication.model.view.AuthenticationView;
import ru.bellintegrator.eas.exception.DataAccessError;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Хмель А.В.
 * class DAO for work with authentication
 */
@Repository
public class AuthenticationDAOImpl implements AuthenticationDAO {
    private final Logger LOG = LoggerFactory.getLogger(AuthenticationDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    // save the registration
    @Override
    public void registration(Authentication authentication) throws DataAccessError {
        try {
            entityManager.persist(authentication);

            LOG.info("save data new registration");
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "registration object the AuthenticationDAOImpl");
        }
    }

    // send activation code
    @Override
    public ActivationView sendActivationCode() throws DataAccessError {
        try {
            LOG.info("send activation code");

            return new ActivationView();
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "sendActivationCode object the AuthenticationDAOImpl");
        }
    }

    // check authentication
    @Override
    public boolean checkAuthentication(AuthenticationView authenticationView) throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Authentication> criteria = builder.createQuery(Authentication.class);

            Root<Authentication> root = criteria.from(Authentication.class);
            criteria.where(builder.equal(root.get("login"), authenticationView.getLogin()));
            criteria.where(builder.equal(root.get("password"), authenticationView.getPassword()));
            TypedQuery<Authentication> query = entityManager.createQuery(criteria);
            int result = query.getMaxResults();

            LOG.info("check authentication");

            return result > 0 ? true : false;
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "checkAuthentication object the AuthenticationDAOImpl");
        }
    }
}

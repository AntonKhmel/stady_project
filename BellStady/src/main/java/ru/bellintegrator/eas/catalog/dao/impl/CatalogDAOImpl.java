package ru.bellintegrator.eas.catalog.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.catalog.dao.CatalogDAO;
import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.address.dependent.Country;

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
 * class DAO for work with objects document and country
 */
@Repository
public class CatalogDAOImpl implements CatalogDAO {
    private final Logger LOG = LoggerFactory.getLogger(CatalogDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    // get all docs
    @Override
    public List<DocType> allDocs() throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<DocType> criteria = builder.createQuery(DocType.class);
            Root<DocType> root = criteria.from(DocType.class);
            CriteriaQuery<DocType> select =criteria.select(root);
            TypedQuery<DocType> query = entityManager.createQuery(select);
            List<DocType> documentList = query.getResultList();

            LOG.info("getting list documents");

            return documentList != null ? documentList : Collections.emptyList();
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "allDocs object the CatalogDAOImpl");
        }
    }

    // get all countries
    @Override
    public List<Country> allCountries() throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
            Root<Country> root = criteria.from(Country.class);
            CriteriaQuery<Country> select =criteria.select(root);
            TypedQuery<Country> query = entityManager.createQuery(select);
            List<Country> countryList = query.getResultList();

            LOG.info("getting list countries");

            return countryList != null ? countryList : Collections.emptyList();
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "allCountries object the CatalogDAOImpl");
        }
    }
}

package ru.bellintegrator.eas.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.catalog.dao.CatalogDAO;
import ru.bellintegrator.eas.catalog.service.CatalogService;
import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.address.dependent.Country;

import java.util.List;

/**
 * @author Хмель А.В.
 * class Service for objects Document and Country by work with business logic
 */
@Service
public class CatalogServiceImpl implements CatalogService {
    private final Logger LOG = LoggerFactory.getLogger(CatalogServiceImpl.class);

    @Autowired
    private CatalogDAO catalogDAO;

     // calls the DAO object method for get all docs
    @Override
    @Transactional(readOnly = true)
    public List<DocType> allDocs() throws DataAccessError {
        LOG.info("call method DAO for getting list documents");

        return catalogDAO.allDocs();
    }

    // calls the DAO object method for get all countries
    @Override
    @Transactional(readOnly = true)
    public List<Country> allCountries() throws DataAccessError {
        LOG.info("call method DAO for getting list countries");

        return catalogDAO.allCountries();
    }
}

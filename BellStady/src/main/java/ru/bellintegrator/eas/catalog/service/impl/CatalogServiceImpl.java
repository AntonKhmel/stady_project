package ru.bellintegrator.eas.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.catalog.dao.CatalogDAO;
import ru.bellintegrator.eas.catalog.service.CatalogService;
import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.employee.model.view.dependent.DocTypeView;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.address.dependent.Country;
import ru.bellintegrator.eas.organization.model.view.address.dependent.CountryView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Хмель А.В.
 * class Service for objects DocumentView and CountryView by work with business logic
 */
@Service
public class CatalogServiceImpl implements CatalogService {
    private final Logger LOG = LoggerFactory.getLogger(CatalogServiceImpl.class);

    @Autowired
    private CatalogDAO catalogDAO;

     // calls the DAO object method for get all docs
    @Override
    @Transactional(readOnly = true)
    public List<DocTypeView> allDocs() throws DataAccessError {
        LOG.info("call method DAO for getting list documents");

        List<DocType> docTypeList = catalogDAO.allDocs();

        Function<DocType, DocTypeView> mapDocType = p -> {
            DocTypeView view = new DocTypeView();
            view.setDocName(p.getDocName());
            view.setDocCode(p.getDocCode());

            return view;
        };

        return docTypeList.stream()
                .map(mapDocType)
                .collect(Collectors.toList());
    }

    // calls the DAO object method for get all countries
    @Override
    @Transactional(readOnly = true)
    public List<CountryView> allCountries() throws DataAccessError {
        LOG.info("call method DAO for getting list countries");

        List<Country> countryList = catalogDAO.allCountries();

        Function<Country, CountryView> mapCountry = p -> {
            CountryView view = new CountryView();
            view.setName(p.getName());
            view.setCode(p.getCode());

            return view;
        };

        return countryList.stream()
                .map(mapCountry)
                .collect(Collectors.toList());
    }
}

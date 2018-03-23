package ru.bellintegrator.eas.catalog.service;

import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.address.dependent.Country;

import java.util.List;

/**
 * @author Хмель А.В.
 */
public interface CatalogService {
    /**
     * @return List<Document>
     * @throws DataAccessError If an exception access data
     */
    List<DocType> allDocs() throws DataAccessError;

    /**
     * @return List<Country>
     * @throws DataAccessError If an exception access data
     */
    List<Country> allCountries() throws DataAccessError;
}

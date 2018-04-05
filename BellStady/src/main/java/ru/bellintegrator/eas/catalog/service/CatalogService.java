package ru.bellintegrator.eas.catalog.service;

import ru.bellintegrator.eas.employee.model.view.dependent.DocTypeView;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.view.address.dependent.CountryView;

import java.util.List;

/**
 * @author Хмель А.В.
 */
public interface CatalogService {
    /**
     * @return List<DocTypeView>
     * @throws DataAccessError If an exception access data
     */
    List<DocTypeView> allDocs() throws DataAccessError;

    /**
     * @return List<CountryView>
     * @throws DataAccessError If an exception access data
     */
    List<CountryView> allCountries() throws DataAccessError;
}

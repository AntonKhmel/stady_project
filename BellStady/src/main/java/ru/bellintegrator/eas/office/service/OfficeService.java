package ru.bellintegrator.eas.office.service;

import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.office.model.view.OfficeView;

import java.util.List;

/**
 * @author Хмель А.В.
 */
public interface OfficeService {
    /**
     * @param officeView
     * @return List<OfficeView>
     * @throws DataAccessError If an exception access data
     */
    List<OfficeView> filterOffices(OfficeView officeView) throws DataAccessError;

    /**
     * @param id
     * @return boolean
     * @throws DataAccessError If an exception access data
     */
    boolean deleteOfficeById(int id) throws DataAccessError;

    /**
     * @param officeView
     * @throws DataAccessError If an exception access data
     */
    void updateOffice(OfficeView officeView) throws DataAccessError;

    /**
     * @param officeView
     * @throws DataAccessError If an exception access data
     */
    void saveOffice(OfficeView officeView) throws DataAccessError;
}

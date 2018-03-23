package ru.bellintegrator.eas.office.service;

import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.office.model.Office;

import java.util.List;

/**
 * @author Хмель А.В.
 */
public interface OfficeService {
    /**
     * @param office
     * @return List<Office>
     * @throws DataAccessError If an exception access data
     */
    List<Office> filterOffices(Office office) throws DataAccessError;

    /**
     * @param id
     * @return boolean
     * @throws DataAccessError If an exception access data
     */
    boolean deleteOfficeById(int id) throws DataAccessError;

    /**
     * @param office
     * @throws DataAccessError If an exception access data
     */
    void updateOffice(Office office) throws DataAccessError;

    /**
     * @param office
     * @throws DataAccessError If an exception access data
     */
    void saveOffice(Office office) throws DataAccessError;
}

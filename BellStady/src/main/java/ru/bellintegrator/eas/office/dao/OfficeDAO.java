package ru.bellintegrator.eas.office.dao;

import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.office.model.Office;
import ru.bellintegrator.eas.office.model.view.OfficeView;

import java.util.List;

/**
 * @author Хмель А.В.
 */
public interface OfficeDAO {
    /**
     * @param officeView
     * @return List<Office>
     * @throws DataAccessError If an exception access data
     */
    List<Office> filterOffices(OfficeView officeView) throws DataAccessError;

    /**
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
     * @param office
     * @throws DataAccessError If an exception access data
     */
    void saveOffice(Office office) throws DataAccessError;
}

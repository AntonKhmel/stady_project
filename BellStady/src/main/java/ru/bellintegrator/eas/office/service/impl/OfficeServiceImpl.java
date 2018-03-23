package ru.bellintegrator.eas.office.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.office.dao.OfficeDAO;
import ru.bellintegrator.eas.office.model.Office;
import ru.bellintegrator.eas.office.service.OfficeService;

import java.util.List;

/**
 * @author Хмель А.В.
 * class Service for object the Office by work with business logic
 */
@Service
public class OfficeServiceImpl implements OfficeService {
    private final Logger LOG = LoggerFactory.getLogger(OfficeServiceImpl.class);

    @Autowired
    private OfficeDAO officeDAO;

    // calls the DAO object method on filters orgId, name, phone, isActive
    @Transactional(readOnly = true)
    @Override
    public List<Office> filterOffices(Office office) throws DataAccessError {
        LOG.info("call method DAO for getting list officces by filter");

        return officeDAO.filterOffices(office);
    }

    // calls the DAO object method for delete office
    @Transactional()
    @Override
    public boolean deleteOfficeById(int id) throws DataAccessError {
        LOG.info("call method DAO for delete the office by id");

        return officeDAO.deleteOfficeById(id);
    }

    // calls the DAO object method for update office
    @Transactional()
    @Override
    public void updateOffice(Office office) throws DataAccessError {
        officeDAO.updateOffice(office);

        LOG.info("call method DAO for update the office");
    }

    // calls the DAO object method for save office
    @Transactional()
    @Override
    public void saveOffice(Office office) throws DataAccessError {
        officeDAO.saveOffice(office);

        LOG.info("call method DAO for save the office");
    }
}

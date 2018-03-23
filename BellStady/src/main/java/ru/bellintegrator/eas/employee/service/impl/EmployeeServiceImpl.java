package ru.bellintegrator.eas.employee.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.employee.dao.EmployeeDAO;
import ru.bellintegrator.eas.employee.model.Employee;
import ru.bellintegrator.eas.employee.service.EmployeeService;
import ru.bellintegrator.eas.exception.DataAccessError;

import java.util.List;

/**
 * @author Хмель А.В.
 * class Service for object the Employee by work with business logic
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeDAO employeeDAO;

    // calls the DAO object method on filters officeId, firstName, secondName, middleName,
    // position, docCode, citizenshipCode
    @Transactional(readOnly = true)
    @Override
    public List<Employee> filterEmployees(Employee employee) throws DataAccessError {
        LOG.info("call method DAO for getting list employees by filter");

        return employeeDAO.filterEmployees(employee);
    }

    // calls the DAO object method for delete employee
    @Transactional()
    @Override
    public boolean deleteEmployeeById(int id) throws DataAccessError {
        LOG.info("call method DAO for delete the employee by id");

        return employeeDAO.deleteEmployeeById(id);
    }

    // calls the DAO object method for update employee
    @Transactional()
    @Override
    public void updateEmployee(Employee employee) throws DataAccessError {
        employeeDAO.updateEmployee(employee);

        LOG.info("call method DAO for update the employee");
    }

    // calls the DAO object method for save employee
    @Transactional()
    @Override
    public void saveEmployee(Employee employee) throws DataAccessError {
        employeeDAO.saveEmployee(employee);

        LOG.info("call method DAO for save the employee");
    }
}

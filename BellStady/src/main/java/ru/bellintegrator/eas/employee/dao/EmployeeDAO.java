package ru.bellintegrator.eas.employee.dao;

import ru.bellintegrator.eas.employee.model.Employee;
import ru.bellintegrator.eas.exception.DataAccessError;

import java.util.List;

/**
 * @author Хмель А.В.
 */
public interface EmployeeDAO {
    /**
     * @param employee
     * @return List<Employee>
     * @throws DataAccessError If an exception access data
     */
    List<Employee> filterEmployees(Employee employee) throws DataAccessError;

    /**
     * @return Employee
     * @throws DataAccessError If an exception access data
     */
    boolean deleteEmployeeById(int id) throws DataAccessError;

    /**
     * @param employee
     * @throws DataAccessError If an exception access data
     */
    void updateEmployee(Employee employee) throws DataAccessError;

    /**
     * @param employee
     * @throws DataAccessError If an exception access data
     */
    void saveEmployee(Employee employee) throws DataAccessError;
}

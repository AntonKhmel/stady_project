package ru.bellintegrator.eas.employee.service;

import ru.bellintegrator.eas.employee.model.view.EmployeeView;
import ru.bellintegrator.eas.exception.DataAccessError;

import java.util.List;

/**
 * @author Хмель А.В.
 */
public interface EmployeeService {
    /**
     * @param employeeView
     * @return List<Employee>
     * @throws DataAccessError If an exception access data
     */
    List<EmployeeView> filterEmployees(EmployeeView employeeView) throws DataAccessError;

    /**
     * @param id
     * @return Employee
     * @throws DataAccessError If an exception access data
     */
    boolean deleteEmployeeById(int id) throws DataAccessError;

    /**
     * @param employeeView
     * @throws DataAccessError If an exception access data
     */
    void updateEmployee(EmployeeView employeeView) throws DataAccessError;

    /**
     * @param employeeView
     * @throws DataAccessError If an exception access data
     */
    void saveEmployee(EmployeeView employeeView) throws DataAccessError;
}

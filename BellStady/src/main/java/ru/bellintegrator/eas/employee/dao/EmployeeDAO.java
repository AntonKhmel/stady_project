package ru.bellintegrator.eas.employee.dao;

import ru.bellintegrator.eas.employee.model.Employee;
import ru.bellintegrator.eas.employee.model.view.EmployeeView;
import ru.bellintegrator.eas.exception.DataAccessError;

import java.util.List;

/**
 * @author Хмель А.В.
 */
public interface EmployeeDAO {
    /**
     * @param employeeView
     * @return List<Employee>
     * @throws DataAccessError If an exception access data
     */
    List<Employee> filterEmployees(EmployeeView employeeView) throws DataAccessError;

    /**
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
     * @param employee
     * @throws DataAccessError If an exception access data
     */
    void saveEmployee(Employee employee) throws DataAccessError;
}

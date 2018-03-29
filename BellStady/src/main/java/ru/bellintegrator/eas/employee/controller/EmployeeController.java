package ru.bellintegrator.eas.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.eas.employee.model.Employee;
import ru.bellintegrator.eas.employee.service.EmployeeService;
import ru.bellintegrator.eas.exception.DataAccessError;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Хмель А.В.
 * class Controller for processing requests associated with the object the Employee and calling business logic
 */
@RestController
@RequestMapping(value="/api", produces = APPLICATION_JSON_UTF8_VALUE)
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * processes a request for getting list employees by filters officeId, firstName, lastName,
     * middleName, position, docCode, citizenshipCode,
     * and calls the corresponding method of business logic
     * @param employee
     * @return List<Employee>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/user/list", method = {POST})
    public List<Employee> filterEmployees(@RequestBody Employee employee) throws DataAccessError {
        LOG.info("getting list employees by filter");

        List<Employee> employeeList = employeeService.filterEmployees(employee);

        return employeeList;
    }

    /**
     * processes a request for delete the employee by id
     * @param id
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/user/{id}", method= {GET})
    public HttpStatus deleteEmployeeById(@PathVariable("id") int id) throws DataAccessError {
        LOG.info("delete the employee by id: {}", id);

        boolean delete = employeeService.deleteEmployeeById(id);

        if (delete) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * processes a request for update the employee
     * @param employee
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/user/update", method = {POST})
    public HttpStatus updateEmployee(@RequestBody Employee employee) throws DataAccessError {
        LOG.info("update the employee: {}", employee);

        employeeService.updateEmployee(employee);

        return HttpStatus.OK;
    }

    /**
     * processes a request for save the employee
     * @param employee
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/user/save", method = {POST})
    public HttpStatus saveEmployee(@RequestBody Employee employee) throws DataAccessError {
        LOG.info("save the employee: {}", employee);

        employeeService.saveEmployee(employee);

        return HttpStatus.CREATED;
    }

    /**
     * intercepts exceptions and sends as a response to the page
     * @param exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exception) {
        LOG.info("exception: {}", exception);

        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}

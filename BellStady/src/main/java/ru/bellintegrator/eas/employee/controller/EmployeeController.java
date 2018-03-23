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
     * @return result ResponseEntity<List<Employee>>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/user/list", method = {POST})
    public ResponseEntity<List<Employee>> filterEmployees(@RequestBody Employee employee) throws DataAccessError {
        List<Employee> employeeList = employeeService.filterEmployees(employee);
        LOG.info("getting list employees by filter");
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    /**
     * processes a request for delete the employee by id
     * @param id
     * @return ResponseEntity<Boolean>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/user/{id}", method= {GET})
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable("id") int id) throws DataAccessError {
        boolean delete = employeeService.deleteEmployeeById(id);
        LOG.info("delete the employee by id: {}", id);

        return new ResponseEntity<Boolean>(delete, HttpStatus.OK);
    }

    /**
     * processes a request for update the employee
     * @param employee
     * @return ResponseEntity<Void>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/user/update", method = {POST})
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) throws DataAccessError {
        employeeService.updateEmployee(employee);
        LOG.info("update the employee: {}", employee);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * processes a request for save the employee
     * @param employee
     * @return ResponseEntity<Void>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/user/save", method = {POST})
    public ResponseEntity<Void> saveEmployee(@RequestBody Employee employee) throws DataAccessError {
        employeeService.saveEmployee(employee);
        LOG.info("save the employee: {}", employee);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
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

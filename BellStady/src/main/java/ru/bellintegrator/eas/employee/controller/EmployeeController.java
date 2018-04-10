package ru.bellintegrator.eas.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.eas.employee.model.view.EmployeeView;
import ru.bellintegrator.eas.employee.service.EmployeeService;
import ru.bellintegrator.eas.exception.DataAccessError;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Хмель А.В.
 * class Controller for processing requests associated with the object the EmployeeView and calling business logic
 */
@Api(value = "Employee", description = "APIs for working with employee")
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
     * @param employeeView
     * @return List<EmployeeView>
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "filterEmployees", nickname = "filterEmployees", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/user/list", method = {POST})
    public List<EmployeeView> filterEmployees(@RequestBody EmployeeView employeeView) throws DataAccessError {
        LOG.info("getting list employees by filter");

        List<EmployeeView> employeeViewList = employeeService.filterEmployees(employeeView);

        return employeeViewList;
    }

    /**
     * processes a request for delete the employee by id
     * @param id
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "deleteEmployeeById", nickname = "deleteEmployeeById", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
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
     * @param employeeView
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "updateEmployee", nickname = "updateEmployee", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/user/update", method = {POST})
    public HttpStatus updateEmployee(@RequestBody EmployeeView employeeView) throws DataAccessError {
        LOG.info("update the employee: {}", employeeView);

        employeeService.updateEmployee(employeeView);

        return HttpStatus.OK;
    }

    /**
     * processes a request for save the employee
     * @param employeeView
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "saveEmployee", nickname = "saveEmployee", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/user/save", method = {POST})
    public HttpStatus saveEmployee(@RequestBody EmployeeView employeeView) throws DataAccessError {
        LOG.info("save the employee: {}", employeeView);

        employeeService.saveEmployee(employeeView);

        return HttpStatus.OK;
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

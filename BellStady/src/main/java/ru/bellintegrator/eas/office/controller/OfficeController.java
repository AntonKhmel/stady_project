package ru.bellintegrator.eas.office.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.office.model.view.OfficeView;
import ru.bellintegrator.eas.office.service.OfficeService;

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
 * class Controller for processing requests associated with the object the OfficeView and calling business logic
 */
@Api(value = "Office", description = "APIs for working with office")
@RestController
@RequestMapping(value="/api", produces = APPLICATION_JSON_UTF8_VALUE)
public class OfficeController {
    private final Logger LOG = LoggerFactory.getLogger(OfficeController.class);

    @Autowired
    private OfficeService officeService;

    /**
     * processes a request for getting list offices by filters officeId, firstName, lastName,
     * middleName,
     * and calls the corresponding method of business logic
     * @param officeView
     * @return List<OfficeView>
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "filterOffices", nickname = "filterOffices", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/office/list", method = {POST})
    public List<OfficeView> filterOffices(@RequestBody OfficeView officeView) throws DataAccessError {
        LOG.info("getting list offices by filter");

        List<OfficeView> officeViewList = officeService.filterOffices(officeView);

        return officeViewList;
    }

    /**
     * processes a request for delete the Office by id
     * @param id
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "deleteOfficeById", nickname = "deleteOfficeById", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/office/{id}", method= {GET})
    public HttpStatus deleteOfficeById(@PathVariable("id") int id) throws DataAccessError {
        LOG.info("delete the office by id: {}", id);

        boolean delete = officeService.deleteOfficeById(id);

        if (delete) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * processes a request for update the Office
     * @param officeView
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "updateOffice", nickname = "updateOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/office/update", method = {POST})
    public HttpStatus updateOffice(@RequestBody OfficeView officeView) throws DataAccessError {
        LOG.info("update the office: {}", officeView);

        officeService.updateOffice(officeView);

        return HttpStatus.OK;
    }

    /**
     * processes a request for save the Office
     * @param officeView
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "saveOffice", nickname = "saveOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/office/save", method = {POST})
    public HttpStatus saveOffice(@RequestBody OfficeView officeView) throws DataAccessError {
        LOG.info("save the office: {}", officeView);

        officeService.saveOffice(officeView);

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

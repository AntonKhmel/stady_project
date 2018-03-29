package ru.bellintegrator.eas.office.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.office.model.Office;
import ru.bellintegrator.eas.office.service.OfficeService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Хмель А.В.
 * class Controller for processing requests associated with the object the Office and calling business logic
 */
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
     * @param office
     * @return List<Office>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/office/list", method = {POST})
    public List<Office> filterOffices(@RequestBody Office office) throws DataAccessError {
        LOG.info("getting list offices by filter");

        List<Office> officeList = officeService.filterOffices(office);

        return officeList;
    }

    /**
     * processes a request for delete the Office by id
     * @param id
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
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
     * @param office
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/office/update", method = {POST})
    public HttpStatus updateOffice(@RequestBody Office office) throws DataAccessError {
        LOG.info("update the office: {}", office);

        officeService.updateOffice(office);

        return HttpStatus.OK;
    }

    /**
     * processes a request for save the Office
     * @param office
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/office/save", method = {POST})
    public HttpStatus saveOffice(@RequestBody Office office) throws DataAccessError {
        LOG.info("save the office: {}", office);

        officeService.saveOffice(office);

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

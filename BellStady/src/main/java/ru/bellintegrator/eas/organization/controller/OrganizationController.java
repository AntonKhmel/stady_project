package ru.bellintegrator.eas.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.Organization;
import ru.bellintegrator.eas.organization.service.OrganizationService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Хмель А.В.
 * class Controller for processing requests associated with the object the Organization and calling business logic
 */
@RestController
@RequestMapping(value="/api", produces = APPLICATION_JSON_UTF8_VALUE)
public class OrganizationController {
    private final Logger LOG = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    /**
     * processes a request for getting list organizations by filters name, inn, isActive,
     * and calls the corresponding method of business logic
     * @param organization
     * @return ResponseEntity<List<Organization>>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/organization/list", method = {POST})
    public ResponseEntity<List<Organization>> filterOrganizations(@RequestBody Organization organization) throws DataAccessError {
        List<Organization> organizationList = organizationService.filterOrganizations(organization);
        LOG.info("getting list organizations by filter");

        return new ResponseEntity<List<Organization>>(organizationList, HttpStatus.OK);
    }

    /**
     * processes a request for get the Organization by id
     * @param id
     * @return ResponseEntity<Organization>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/organization/{id}", method= {GET})
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") int id) throws DataAccessError {
        Organization organization = organizationService.getOrganizationById(id);
        LOG.info("get the organization by id: {}", id);

        return new ResponseEntity<Organization>(organization, HttpStatus.OK);
    }

    /**
     * processes a request for update the Organization
     * @param organization
     * @return ResponseEntity<Void>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/organization/update", method = {POST})
    public ResponseEntity<Void> updateOrganization(@RequestBody Organization organization) throws DataAccessError {
        organizationService.updateOrganization(organization);
        LOG.info("update the organization: {}", organization);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * processes a request for save the Organization
     * @param organization
     * @return ResponseEntity<Void>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/organization/save", method = {POST})
    public ResponseEntity<Void> saveOrganization(@RequestBody Organization organization) throws DataAccessError {
        organizationService.saveOrganization(organization);
        LOG.info("save the organization: {}", organization);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * intercepts exceptions and sends as a response to the page
     * @param exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exception) {
        LOG.info("exception (OrganizationController): {}", exception);
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}

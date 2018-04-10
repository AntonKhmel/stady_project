package ru.bellintegrator.eas.organization.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.Organization;
import ru.bellintegrator.eas.organization.model.view.OrganizationView;
import ru.bellintegrator.eas.organization.service.OrganizationService;

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
 * class Controller for processing requests associated with the object the OrganizationView and calling business logic
 */
@Api(value = "Organization", description = "APIs for working with organization")
@RestController
@RequestMapping(value="/api", produces = APPLICATION_JSON_UTF8_VALUE)
public class OrganizationController {
    private final Logger LOG = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    /**
     * processes a request for getting list organizations by filters name, inn, isActive,
     * and calls the corresponding method of business logic
     * @param organizationView
     * @return List<OrganizationView>
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "filterOrganizations", nickname = "filterOrganizations", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/organization/list", method = {POST})
    public List<OrganizationView> filterOrganizations(@RequestBody OrganizationView organizationView) throws DataAccessError {
        LOG.info("getting list organizations by filter");

        List<OrganizationView> organizationViewList = organizationService.filterOrganizations(organizationView);

        return organizationViewList;
    }

    /**
     * processes a request for get the Organization by id
     * @param id
     * @return OrganizationView
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "getOrganizationByIde", nickname = "getOrganizationById", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrganizationView.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/organization/{id}", method= {GET})
    public OrganizationView getOrganizationById(@PathVariable("id") int id) throws DataAccessError {
        LOG.info("get the organization by id: {}", id);

        OrganizationView organizationView = organizationService.getOrganizationById(id);

        return organizationView;
    }

    /**
     * processes a request for update the Organization
     * @param organizationView
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "updateOrganization", nickname = "updateOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/organization/update", method = {POST})
    public HttpStatus updateOrganization(@RequestBody OrganizationView organizationView) throws DataAccessError {
        LOG.info("update the organization: {}", organizationView);

        organizationService.updateOrganization(organizationView);

        return HttpStatus.OK;
    }

    /**
     * processes a request for save the Organization
     * @param organizationView
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "saveOrganization", nickname = "saveOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/organization/save", method = {POST})
    public HttpStatus saveOrganization(@RequestBody OrganizationView organizationView) throws DataAccessError {
        LOG.info("save the organization: {}", organizationView);

        organizationService.saveOrganization(organizationView);

        return HttpStatus.OK;
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

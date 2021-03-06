package ru.bellintegrator.eas.catalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.eas.catalog.service.CatalogService;
import ru.bellintegrator.eas.employee.model.view.dependent.DocTypeView;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.view.address.dependent.CountryView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Хмель А.В.
 * class Controller for processing requests associated with the objects DocumentView and CountryView and calling business logic
 */
@Api(value = "Catalog", description = "APIs for working with catalog")
@RestController
@RequestMapping(value="/api", produces = APPLICATION_JSON_UTF8_VALUE)
public class CatalogController {
    private final Logger LOG = LoggerFactory.getLogger(CatalogController.class);

    @Autowired
    private CatalogService catalogService;

    /**
     * processes a request for get all docs
     * @return List<DocTypeView>
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "allDocs", nickname = "allDocs", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/docs", method = {POST})
    public List<DocTypeView> allDocs() throws DataAccessError {
        LOG.info("getting all docs");

        List<DocTypeView> docTypeViewList = catalogService.allDocs();

        return docTypeViewList;
    }

    /**
     * processes a request for get all countries
     * @return List<CountryView>
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "allCountries", nickname = "allCountries", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/countries", method = {POST})
    public List<CountryView> allCountries() throws DataAccessError {
        LOG.info("getting all countries");

        List<CountryView> countryViewList = catalogService.allCountries();

        return countryViewList;
    }

    /**
     * intercepts exceptions and sends as a response to the page
     * @param exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exception) {
        LOG.info("exception: {}", exception);

        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NO_CONTENT);
    }
}

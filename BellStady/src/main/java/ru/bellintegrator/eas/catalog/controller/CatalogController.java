package ru.bellintegrator.eas.catalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.eas.catalog.service.CatalogService;
import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.address.dependent.Country;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Хмель А.В.
 * class Controller for processing requests associated with the objects Document and Country and calling business logic
 */
@RestController
@RequestMapping(value="/api", produces = APPLICATION_JSON_UTF8_VALUE)
public class CatalogController {
    private final Logger LOG = LoggerFactory.getLogger(CatalogController.class);

    @Autowired
    private CatalogService catalogService;

    /**
     * processes a request for get all docs
     * @return List<DocType>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/docs", method = {POST})
    public List<DocType> allDocs() throws DataAccessError {
        LOG.info("getting all docs");

        List<DocType> documentList = catalogService.allDocs();

        return documentList;
    }

    /**
     * processes a request for get all countries
     * @return List<Country>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/countries", method = {POST})
    public List<Country> allCountries() throws DataAccessError {
        LOG.info("getting all countries");

        List<Country> countryList = catalogService.allCountries();

        return countryList;
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

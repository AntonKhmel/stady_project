package ru.bellintegrator.eas.authentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.eas.authentication.model.view.ActivationView;
import ru.bellintegrator.eas.authentication.model.view.AuthenticationView;
import ru.bellintegrator.eas.authentication.service.AuthenticationService;
import ru.bellintegrator.eas.exception.DataAccessError;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Хмель А.В.
 * class Controller for processing requests associated with the object the AuthenticationView and calling business logic
 */
@Api(value = "Authentication", description = "APIs for working with authentication")
@RestController
@RequestMapping(value="/api", produces = APPLICATION_JSON_UTF8_VALUE)
public class AuthenticationController {
    private final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * processes a request for save registration
     * @param authenticationView
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "registration", nickname = "registration", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/registr", method = {POST})
    public HttpStatus registration(@RequestBody AuthenticationView authenticationView) throws DataAccessError {
        LOG.info("creating new registration: {}", authenticationView);

        authenticationService.registration(authenticationView);

        return HttpStatus.OK;
    }

    /**
     * processes a request for send activation code
     * @return ActivationView
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "sendActivationCode", nickname = "sendActivationCode", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActivationView.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/activation/code", method= {GET})
    public ActivationView sendActivationCode() throws DataAccessError {
        LOG.info("sended activation code");

        ActivationView activationView = authenticationService.sendActivationCode();

        return activationView;
    }

    /**
     * processes a request for check authentication
     * @param authenticationView
     * @return HttpStatus
     * @throws DataAccessError If an exception access data
     */
    @ApiOperation(value = "checkAuthentication", nickname = "checkAuthentication", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 404, message = "NOT_FOUND")})
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public HttpStatus checkAuthentication(@RequestBody AuthenticationView authenticationView) throws DataAccessError {
        LOG.info("check authentication: {}", authenticationView);

        boolean check = authenticationService.checkAuthentication(authenticationView);

        if (check) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
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

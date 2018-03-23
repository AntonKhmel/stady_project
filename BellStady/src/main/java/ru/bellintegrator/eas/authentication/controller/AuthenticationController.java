package ru.bellintegrator.eas.authentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.eas.authentication.model.Activation;
import ru.bellintegrator.eas.authentication.model.Authentication;
import ru.bellintegrator.eas.authentication.service.AuthenticationService;
import ru.bellintegrator.eas.exception.DataAccessError;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Хмель А.В.
 * class Controller for processing requests associated with the object the Authentication and calling business logic
 */
@RestController
@RequestMapping(value="/api", produces = APPLICATION_JSON_UTF8_VALUE)
public class AuthenticationController {
    private final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * processes a request for save registration
     * @param authentication
     * @return ResponseEntity<Void>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/registr", method = {POST})
    public ResponseEntity<Void> registration(@RequestBody Authentication authentication) throws DataAccessError {
        authenticationService.registration(authentication);
        LOG.info("creating new registration: {}", authentication);

        return new ResponseEntity<Void>(null, null, HttpStatus.CREATED);
    }

    /**
     * processes a request for send activation code
     * @return ResponseEntity<Activation>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/activation/code", method= {GET})
    public ResponseEntity<Activation> sendActivationCode() throws DataAccessError {
        Activation activation = authenticationService.sendActivationCode();
        LOG.info("sended activation code: {}", activation);

        return new ResponseEntity<Activation>(activation, HttpStatus.OK);
    }

    /**
     * processes a request for check authentication
     * @param authentication
     * @return ResponseEntity<Boolean>
     * @throws DataAccessError If an exception access data
     */
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public ResponseEntity<Boolean> checkAuthentication(@RequestBody Authentication authentication) throws DataAccessError {
        boolean check = authenticationService.checkAuthentication(authentication);
        LOG.info("check authentication: {}", authentication);

        return new ResponseEntity<Boolean>(check, HttpStatus.OK);
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

package ru.bellintegrator.eas.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.eas.authentication.model.view.ActivationView;
import ru.bellintegrator.eas.authentication.model.view.AuthenticationView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
public class AuthenticationControllerIntegrationTest {
    private final Logger LOG = LoggerFactory.getLogger(AuthenticationControllerIntegrationTest.class);

    private static final String BASE_URI = "http://localhost:7030/BellStady_war_exploded/api";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void addRegistration(){
        AuthenticationView authenticationView = new AuthenticationView(0,"Admin", "123456", "Anton");
        try {
            HttpStatus hs = restTemplate.postForEntity(BASE_URI + "/registr", authenticationView, HttpStatus.class).getStatusCode();
        } catch (HttpClientErrorException e){
            LOG.info("Create registration error: {}", e);
        }
    }

    @Test
    public void registration(){
        AuthenticationView authenticationView = new AuthenticationView(0,"Anton", "root1981", "Anton");
        try {
            HttpStatus hs = restTemplate.postForEntity(BASE_URI + "/registr", authenticationView, HttpStatus.class).getStatusCode();
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void sendActivationCode(){
        try {
            ActivationView activationView = restTemplate.getForObject(BASE_URI + "/activation/code", ActivationView.class);
            assertNotNull(activationView);
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void checkAuthentication(){
        AuthenticationView authenticationView = new AuthenticationView(0, "Admin", "123456", "");
        try {
            HttpStatus hs = restTemplate.postForEntity(BASE_URI + "/login", authenticationView, HttpStatus.class).getStatusCode();
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }
}

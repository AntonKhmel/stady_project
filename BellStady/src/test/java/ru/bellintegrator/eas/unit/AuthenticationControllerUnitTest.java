package ru.bellintegrator.eas.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.bellintegrator.eas.authentication.controller.AuthenticationController;
import ru.bellintegrator.eas.authentication.model.view.ActivationView;
import ru.bellintegrator.eas.authentication.model.view.AuthenticationView;
import ru.bellintegrator.eas.authentication.service.AuthenticationService;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthenticationControllerUnitTest {
    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(authenticationController)
                .build();
    }

    @Test
    public void registration() throws Exception {
        AuthenticationView authenticationView = new AuthenticationView(0, "Admin", "123456", "Anton");

        doNothing().when(authenticationService).registration(authenticationView);

        mockMvc.perform(post("/api/registr")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(authenticationView)))
                .andExpect(status().isOk());

        verify(authenticationService, times(1)).registration(authenticationView);
        verifyNoMoreInteractions(authenticationService);
    }

    @Test
    public void sendActivationCode() throws Exception {
        ActivationView activationView = new ActivationView();

        when(authenticationService.sendActivationCode()).thenReturn(activationView);

        mockMvc.perform(get("/api/activation/code"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.value", is(activationView.getValue())));

        verify(authenticationService, times(1)).sendActivationCode();
        verifyNoMoreInteractions(authenticationService);
    }

    @Test
    public void checkAuthentication() throws Exception {
        AuthenticationView authenticationView = new AuthenticationView(0, "user", "password", "");
        boolean check = true;

        when(authenticationService.checkAuthentication(authenticationView)).thenReturn(check);

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(authenticationView)))
                .andExpect(status().isOk());

        verify(authenticationService, times(1)).checkAuthentication(authenticationView);
        verifyNoMoreInteractions(authenticationService);
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

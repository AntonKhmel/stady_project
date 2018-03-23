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
import ru.bellintegrator.eas.authentication.model.Activation;
import ru.bellintegrator.eas.authentication.model.Authentication;
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
        Authentication authentication = new Authentication(0, "Admin", "123456", "Anton");

        doNothing().when(authenticationService).registration(authentication);

        mockMvc.perform(post("/api/registr")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(authentication)))
                .andExpect(status().isCreated());

        verify(authenticationService, times(1)).registration(authentication);
        verifyNoMoreInteractions(authenticationService);
    }

    @Test
    public void sendActivationCode() throws Exception {
        Activation activation = new Activation();

        when(authenticationService.sendActivationCode()).thenReturn(activation);

        mockMvc.perform(get("/api/activation/code"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.value", is(activation.getValue())));

        verify(authenticationService, times(1)).sendActivationCode();
        verifyNoMoreInteractions(authenticationService);
    }

    @Test
    public void checkAuthentication() throws Exception {
        Authentication authentication = new Authentication(0, "user", "password", "");
        boolean check = true;

        when(authenticationService.checkAuthentication(authentication)).thenReturn(check);

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(authentication)))
                .andExpect(status().isOk());

        verify(authenticationService, times(1)).checkAuthentication(authentication);
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

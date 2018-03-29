package ru.bellintegrator.eas.unit;

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
import ru.bellintegrator.eas.catalog.controller.CatalogController;
import ru.bellintegrator.eas.catalog.service.CatalogService;
import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.organization.model.address.dependent.Country;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CatalogControllerUnitTest {
    private MockMvc mockMvc;

    @Mock
    private CatalogService catalogService;

    @InjectMocks
    private CatalogController catalogController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(catalogController)
                .build();
    }

    @Test
    public void allDocs() throws Exception {
        List<DocType> docTypeList = Arrays.asList(new DocType(0, 377, "Nacladnaya", 0, null));

        when(catalogService.allDocs()).thenReturn(docTypeList);

        mockMvc.perform(post("/api/docs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].docCode", is(377)))
                .andExpect(jsonPath("$[0].docName", is("Nacladnaya")));

        verify(catalogService, times(1)).allDocs();
        verifyNoMoreInteractions(catalogService);
    }

    @Test
    public void allCountries() throws Exception {
        List<Country> countryList = Arrays.asList(new Country(0, "Russia", 172));

        when(catalogService.allCountries()).thenReturn(countryList);

        mockMvc.perform(post("/api/countries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Russia")))
                .andExpect(jsonPath("$[0].code", is(172)));

        verify(catalogService, times(1)).allCountries();
        verifyNoMoreInteractions(catalogService);
    }
}

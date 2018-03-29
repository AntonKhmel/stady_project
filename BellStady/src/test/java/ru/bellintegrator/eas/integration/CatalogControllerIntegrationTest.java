package ru.bellintegrator.eas.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.organization.model.address.dependent.Country;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
public class CatalogControllerIntegrationTest {
    private static final String BASE_URI = "http://localhost:7030/BellStady_war_exploded/api";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void allDocs() throws Exception {
        try {
            List<DocType> docTypeList = Arrays.asList(restTemplate.postForObject(BASE_URI + "/docs", null, DocType[].class));
            assertNotNull(docTypeList);
            assertThat(docTypeList.get(0).getId(), is(1));
            assertThat(docTypeList.get(0).getDocCode(), is(377));
            assertThat(docTypeList.get(0).getDocName(), is("Nacladnaya"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        };
    }

    @Test
    public void allCountries() throws Exception {
        try {
            List<Country> countryList = Arrays.asList(restTemplate.postForObject(BASE_URI + "/countries", null, Country[].class));
            assertNotNull(countryList);
            assertThat(countryList.get(0).getId(), is(1));
            assertThat(countryList.get(0).getCode(), is(172));
            assertThat(countryList.get(0).getName(), is("Russia"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        };
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

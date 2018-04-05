package ru.bellintegrator.eas.integration;

import org.junit.*;
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
import ru.bellintegrator.eas.employee.model.view.EmployeeView;
import ru.bellintegrator.eas.employee.model.view.dependent.CitizenshipView;
import ru.bellintegrator.eas.employee.model.view.dependent.DocTypeView;
import ru.bellintegrator.eas.employee.model.view.dependent.PositionView;
import ru.bellintegrator.eas.office.model.view.OfficeView;
import ru.bellintegrator.eas.organization.model.view.OrganizationView;
import ru.bellintegrator.eas.organization.model.view.address.AddressView;
import ru.bellintegrator.eas.organization.model.view.address.dependent.*;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
public class CatalogControllerIntegrationTest {
    private final Logger LOG = LoggerFactory.getLogger(CatalogControllerIntegrationTest.class);

    private static final String BASE_URI = "http://localhost:7030/BellStady_war_exploded/api";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void addDocsAndCountries() {
        EmployeeView employeeView1 = new EmployeeView(0, "newFirstName", "newSecondName",
                "newMiddleName", null, new DocTypeView(0, 432, "Nakladnaya",
                21, "02.03.2018"), null, null, null, true);
        EmployeeView employeeView2 = new EmployeeView(0, "newFirstName", "newSecondName",
                "newMiddleName", null, new DocTypeView(0, 117, "Doverennost",
                24, "02.03.2018"), null, null, null, true);
        OfficeView officeView1 = new OfficeView(0, "Milex", new AddressView(0, new CountryView(0,
                "Japan", 135), null, null, null, null),
                new OrganizationView(0, "newName", "newFullName", null, null,
                        null, true), null, true);
        OfficeView officeView2 = new OfficeView(0, "newNameOffice", new AddressView(0, new CountryView(0,
                "Russia", 64), null, null, null, null),
                null, null, true);
        try {
            HttpStatus hs1 = restTemplate.postForObject(BASE_URI + "/user/save", employeeView1, HttpStatus.class);
            HttpStatus hs2 = restTemplate.postForObject(BASE_URI + "/user/save", employeeView2, HttpStatus.class);
            HttpStatus hs3 = restTemplate.postForObject(BASE_URI + "/office/save", officeView1, HttpStatus.class);
            HttpStatus hs4 = restTemplate.postForObject(BASE_URI + "/office/save", officeView2, HttpStatus.class);
        } catch (HttpClientErrorException e){
            LOG.info("Create employees and offices error: {}", e);
        }
    }

    @Test
    public void allDocs() throws Exception {
        try {
            List<DocTypeView> docTypeViewList = Arrays.asList(restTemplate.postForObject(BASE_URI + "/docs", null, DocTypeView[].class));
            assertNotNull(docTypeViewList);
            assertThat(docTypeViewList.get(0).getDocCode(), is(432));
            assertThat(docTypeViewList.get(0).getDocName(), is("Nakladnaya"));
            assertThat(docTypeViewList.get(1).getDocCode(), is(117));
            assertThat(docTypeViewList.get(1).getDocName(), is("Doverennost"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void allCountries() throws Exception {
        try {
            List<CountryView> countryViewList = Arrays.asList(restTemplate.postForObject(BASE_URI + "/countries", null, CountryView[].class));
            assertNotNull(countryViewList);
            assertThat(countryViewList.get(0).getCode(), is(135));
            assertThat(countryViewList.get(0).getName(), is("Japan"));
            assertThat(countryViewList.get(1).getCode(), is(64));
            assertThat(countryViewList.get(1).getName(), is("Russia"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @After
    public void deleteDocsAndCountries() {
        try {
            HttpStatus hs1 = restTemplate.getForObject(BASE_URI + "/user/1", HttpStatus.class);
            HttpStatus hs2 = restTemplate.getForObject(BASE_URI + "/user/2", HttpStatus.class);
            HttpStatus hs3 = restTemplate.getForObject(BASE_URI + "/office/1", HttpStatus.class);
            HttpStatus hs4 = restTemplate.getForObject(BASE_URI + "/office/2", HttpStatus.class);
        } catch (HttpClientErrorException e){
            LOG.info("Delete employees error: {}", e);
        }
    }
}

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
import ru.bellintegrator.eas.office.model.view.OfficeView;
import ru.bellintegrator.eas.organization.model.view.OrganizationView;
import ru.bellintegrator.eas.organization.model.view.address.AddressView;
import ru.bellintegrator.eas.organization.model.view.address.dependent.*;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;
import ru.bellintegrator.eas.organization.model.view.requisite.RequisiteView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
public class OfficeControllerIntegrationTest {
    private final Logger LOG = LoggerFactory.getLogger(OfficeControllerIntegrationTest.class);

    private static final String BASE_URI = "http://localhost:7030/BellStady_war_exploded/api";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void addOffices() throws Exception {
        OfficeView officeView1 = new OfficeView(0, "Milex", null,
                new OrganizationView(0, "newName", "newFullName", null, null,
                        null, true), new PhoneView(0, "333-33-33"), true);
        OfficeView officeView2 = new OfficeView(0, "Optima", null,
                null, null, true);
        OfficeView officeView3 = new OfficeView(0, "newNameOffice", null,
                null, null, true);

        try {
            HttpStatus hs1 = restTemplate.postForObject(BASE_URI + "/office/save", officeView1, HttpStatus.class);
            HttpStatus hs2 = restTemplate.postForObject(BASE_URI + "/office/save", officeView2, HttpStatus.class);
            HttpStatus hs3 = restTemplate.postForObject(BASE_URI + "/office/save", officeView3, HttpStatus.class);
        } catch (HttpClientErrorException e){
            LOG.info("Create offices error: {}", e);
        }
    }

    @Test
    public void filterOffices() throws Exception {
        OrganizationView organizationView = new OrganizationView(1, "newName", "newFullName", null,
                null, null, true);
        OfficeView officeView = new OfficeView(0, "Milex", null, organizationView, null, false);
        try {
            List<OfficeView> officeViewList = Arrays.asList(restTemplate.postForObject(
                    BASE_URI + "/office/list", officeView, OfficeView[].class));
            assertNotNull(officeViewList);
            assertThat(officeViewList.get(0).getName(), is("Milex"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void deleteOfficeById() throws Exception {
        addOffices();
        try {
            HttpStatus hs = restTemplate.getForObject(BASE_URI + "/office/3", HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void updateOffice() throws Exception {
        CountryView cauntryView = new CountryView(0, "newCountry", 0);
        RegionView regionView = new RegionView(0, "newRegion");
        CityView cityView = new CityView(0, "newCity");
        StreetView streetView = new StreetView(0, "newStreet");
        HouseView houseView = new HouseView(0, "21C", 32, "4A");
        AddressView addressView = new AddressView(0, cauntryView, regionView, cityView, streetView, houseView);
        PhoneView phoneView = new PhoneView(0, "89174444444");
        OfficeView officeView = new OfficeView(2, "newNameOffice", addressView, null, phoneView, true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/office/update", officeView, HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void saveOffice() throws Exception {
        CountryView cauntryView = new CountryView(0, "newCountry", 0);
        RegionView regionView = new RegionView(0, "newRegion");
        CityView cityView = new CityView(0, "newCity");
        StreetView streetView = new StreetView(0, "newStreet");
        HouseView houseView = new HouseView(0, "22M", 32, "4M");
        AddressView addressView = new AddressView(0, cauntryView, regionView, cityView, streetView, houseView);
        PhoneView phoneView = new PhoneView(0, "89175555555");
        OfficeView officeView = new OfficeView(0, "nameOffice", addressView, null, phoneView, true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/office/save", officeView, HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @After
    public void deleteOffices() {
        try {
            HttpStatus hs1 = restTemplate.getForObject(BASE_URI + "/office/1", HttpStatus.class);
            HttpStatus hs2 = restTemplate.getForObject(BASE_URI + "/office/2", HttpStatus.class);
            HttpStatus hs3 = restTemplate.getForObject(BASE_URI + "/office/2", HttpStatus.class);
            HttpStatus hs4 = restTemplate.getForObject(BASE_URI + "/office/4", HttpStatus.class);
        } catch (HttpClientErrorException e){
            LOG.info("Delete offices error: {}", e);
        }
    }
}

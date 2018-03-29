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
import ru.bellintegrator.eas.office.model.Office;
import ru.bellintegrator.eas.organization.model.Organization;
import ru.bellintegrator.eas.organization.model.address.Address;
import ru.bellintegrator.eas.organization.model.address.dependent.*;
import ru.bellintegrator.eas.organization.model.phone.Phone;
import ru.bellintegrator.eas.organization.model.requisite.Requisite;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
public class OfficeControllerIntegrationTest {
    private static final String BASE_URI = "http://localhost:7030/BellStady_war_exploded/api";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void filterOffices() throws Exception {
        Organization organization = new Organization(1, "", "", new Requisite(), new Address(), new Phone(), true);
        Office office = new Office(1, "Milex", new Address(), organization, new Phone(), false);

        try {
            List<Office> officeList = Arrays.asList(restTemplate.postForObject(BASE_URI + "/user/list", office, Office[].class));
            assertNotNull(officeList);
            assertThat(officeList.get(0).getId(), is(2));
            assertThat(officeList.get(0).getName(), is("Milex"));
            assertThat(officeList.get(0).getPhone(), is(""));
            assertThat(officeList.get(0).getIsActive(), is(true));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        };
    }

    @Test
    public void deleteOfficeById() throws Exception {
        try {
            HttpStatus hs = restTemplate.getForObject(BASE_URI + "/office/3", HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void updateOffice() throws Exception {
        Country cauntry = new Country(0, "newCountry", 0);
        Region region = new Region(0, "newRegion");
        City city = new City(0, "newCity");
        Street street = new Street(0, "newStreet");
        House house = new House(0, "21C", 32, "4A");
        Address address = new Address(0, cauntry, region, city, street, house);
        Phone phone = new Phone(0, "89174444444");
        Office office = new Office(3, "newNameOffice", address, new Organization(), phone, true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/office/update", office, HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        };
    }

    @Test
    public void saveOffice() throws Exception {
        Country cauntry = new Country(0, "newCountry", 0);
        Region region = new Region(0, "newRegion");
        City city = new City(0, "newCity");
        Street street = new Street(0, "newStreet");
        House house = new House(0, "22M", 32, "4M");
        Address address = new Address(0, cauntry, region, city, street, house);
        Phone phone = new Phone(0, "89175555555");
        Office office = new Office(0, "nameOffice", address, new Organization(), phone, true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/office/save", office, HttpStatus.class);
            assertThat(hs, is(HttpStatus.CREATED));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
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

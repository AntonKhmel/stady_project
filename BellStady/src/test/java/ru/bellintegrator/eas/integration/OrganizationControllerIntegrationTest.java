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
public class OrganizationControllerIntegrationTest {
    private static final String BASE_URI = "http://localhost:7030/BellStady_war_exploded/api";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void filterOrganizations() throws Exception {
        Organization organization = new Organization(0, "Abradoks", "", new Requisite(),
                new Address(), new Phone(), true);

        try {
            List<Organization> organizationList = Arrays.asList(restTemplate.postForObject(BASE_URI + "/organization/list", organization, Organization[].class));
            assertNotNull(organizationList);
            assertThat(organizationList.get(0).getId(), is(2));
            assertThat(organizationList.get(0).getName(), is("Abradoks"));
            assertThat(organizationList.get(0).getFullName(), is("OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU \"ABRADOKS\""));
            assertThat(organizationList.get(0).getPhone().getNumber(), is("8(111)222-22-22"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        };
    }

    @Test
    public void getOrganizationById() throws Exception {
        try {
            Organization organization = restTemplate.getForObject(BASE_URI + "/organization/3", Organization.class);
            assertNotNull(organization);
            assertThat(organization.getId(), is(3));
            assertThat(organization.getName(), is("newName"));
            assertThat(organization.getFullName(), is("newFullName"));
            assertThat(organization.getPhone().getNumber(), is("89172222222"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void updateOrganization() throws Exception {
        Requisite requisite = new Requisite(0, "6464646464", "464646464");
        Country cauntry = new Country(0, "newCountry", 0);
        Region region = new Region(0, "newRegion");
        City city = new City(0, "newCity");
        Street street = new Street(0, "newStreet");
        House house = new House(0, "17A", 32, "4M");
        Address address = new Address(0, cauntry, region, city, street, house);
        Phone phone = new Phone(0, "89172222222");
        Organization organization = new Organization(3, "newName", "newFullName",
                requisite, address, phone, true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/organization/update", organization, HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        };
    }

    @Test
    public void saveOrganization() throws Exception {
        Requisite requisite = new Requisite(0, "6464646464", "464646464");
        Country cauntry = new Country(0, "newCountry", 0);
        Region region = new Region(0, "newRegion");
        City city = new City(0, "newCity");
        Street street = new Street(0, "newStreet");
        House house = new House(0, "18B", 32, "25T");
        Address address = new Address(0, cauntry, region, city, street, house);
        Phone phone = new Phone(0, "89173333333");
        Organization organization = new Organization(0, "OOO Test", "OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU PRIMER",
                requisite, address, phone, true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/organization/save", organization, HttpStatus.class);
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

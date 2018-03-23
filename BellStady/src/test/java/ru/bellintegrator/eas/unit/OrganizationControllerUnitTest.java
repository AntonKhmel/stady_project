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
import ru.bellintegrator.eas.organization.controller.OrganizationController;
import ru.bellintegrator.eas.organization.model.Organization;
import ru.bellintegrator.eas.organization.model.address.Address;
import ru.bellintegrator.eas.organization.model.address.dependent.*;
import ru.bellintegrator.eas.organization.model.phone.Phone;
import ru.bellintegrator.eas.organization.model.requisite.Requisite;
import ru.bellintegrator.eas.organization.service.OrganizationService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrganizationControllerUnitTest {
    private MockMvc mockMvc;

    @Mock
    private OrganizationService organizationService;

    @InjectMocks
    private OrganizationController organizationController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(organizationController)
                .build();
    }

    @Test
    public void filterOrganizations() throws Exception {
        List<Organization> organizationList = Arrays.asList(new Organization(3, "Abradoks", "",
                new Requisite(), new Address(), new Phone(), true));
        Organization organization = new Organization(0, "Abradoks", "", new Requisite(),
                new Address(), new Phone(), true);

        when(organizationService.filterOrganizations(organization)).thenReturn(organizationList);

        mockMvc.perform(post("/api/organization/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(organization)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].name", is("Abradoks")))
                .andExpect(jsonPath("$[0].isActive", is(true)));

        verify(organizationService, times(1)).filterOrganizations(organization);
        verifyNoMoreInteractions(organizationService);
    }

    @Test
    public void getOrganizationById() throws Exception {
        Requisite requisite = new Requisite(0, "7203253271", "720301001");
        Country cauntry = new Country(0, "Russia", 0);
        Region region = new Region(0, "Voronezhskaya oblast");
        City city = new City(0, "Voronezh");
        Street street = new Street(0, "Zagorodnaya");
        House house = new House(0, "124/7M", 7, "24");
        Address address = new Address(0, cauntry, region, city, street, house);
        Phone phone = new Phone(0, "8(222)333-33-33");
        Organization organization = new Organization(0, "Milena", "OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU MILENA",
                requisite, address, phone,true);

        when(organizationService.getOrganizationById(3)).thenReturn(organization);

        mockMvc.perform(get("/api/organization/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is("Milena")))
                .andExpect(jsonPath("$.fullName", is("OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU MILENA")));

        verify(organizationService, times(1)).getOrganizationById(3);
        verifyNoMoreInteractions(organizationService);
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

        doNothing().when(organizationService).updateOrganization(organization);

        mockMvc.perform(
                post("/api/organization/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(organization)))
                .andExpect(status().isOk());

        verify(organizationService, times(1)).updateOrganization(organization);
        verifyNoMoreInteractions(organizationService);
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

        doNothing().when(organizationService).saveOrganization(organization);

        mockMvc.perform(
                post("/api/organization/save")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(organization)))
                .andExpect(status().isCreated());

        verify(organizationService, times(1)).saveOrganization(organization);
        verifyNoMoreInteractions(organizationService);
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

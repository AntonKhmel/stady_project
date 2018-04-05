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
import ru.bellintegrator.eas.organization.model.view.OrganizationView;
import ru.bellintegrator.eas.organization.model.view.address.AddressView;
import ru.bellintegrator.eas.organization.model.view.address.dependent.*;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;
import ru.bellintegrator.eas.organization.model.view.requisite.RequisiteView;
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
        List<OrganizationView> organizationViewList = Arrays.asList(new OrganizationView(1, "Abradoks", "",
                new RequisiteView(), new AddressView(), new PhoneView(), true));
        OrganizationView organizationView = new OrganizationView(0, "Abradoks", "", new RequisiteView(),
                new AddressView(), new PhoneView(), true);

        when(organizationService.filterOrganizations(organizationView)).thenReturn(organizationViewList);

        mockMvc.perform(post("/api/organization/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(organizationView)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Abradoks")))
                .andExpect(jsonPath("$[0].isActive", is(true)));

        verify(organizationService, times(1)).filterOrganizations(organizationView);
        verifyNoMoreInteractions(organizationService);
    }

    @Test
    public void getOrganizationById() throws Exception {
        RequisiteView requisiteView = new RequisiteView(0, "7203253271", "720301001");
        CountryView cauntryView = new CountryView(0, "Russia", 0);
        RegionView regionView = new RegionView(0, "Voronezhskaya oblast");
        CityView cityView = new CityView(0, "Voronezh");
        StreetView streetView = new StreetView(0, "Zagorodnaya");
        HouseView houseView = new HouseView(0, "124/7M", 7, "24");
        AddressView addressView = new AddressView(0, cauntryView, regionView, cityView, streetView, houseView);
        PhoneView phoneView = new PhoneView(0, "8(222)333-33-33");
        OrganizationView organizationView = new OrganizationView(3, "Milena", "OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU MILENA",
                requisiteView, addressView, phoneView,true);

        when(organizationService.getOrganizationById(3)).thenReturn(organizationView);

        mockMvc.perform(get("/api/organization/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Milena")))
                .andExpect(jsonPath("$.fullName", is("OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU MILENA")));

        verify(organizationService, times(1)).getOrganizationById(3);
        verifyNoMoreInteractions(organizationService);
    }

    @Test
    public void updateOrganization() throws Exception {
        RequisiteView requisiteView = new RequisiteView(0, "6464646464", "464646464");
        CountryView cauntryView = new CountryView(0, "newCountry", 0);
        RegionView regionView = new RegionView(0, "newRegion");
        CityView cityView = new CityView(0, "newCity");
        StreetView streetView = new StreetView(0, "newStreet");
        HouseView houseView = new HouseView(0, "17A", 32, "4M");
        AddressView addressView = new AddressView(0, cauntryView, regionView, cityView, streetView, houseView);
        PhoneView phoneView = new PhoneView(0, "89172222222");
        OrganizationView organizationView = new OrganizationView(3, "newName", "newFullName",
                requisiteView, addressView, phoneView, true);

        doNothing().when(organizationService).updateOrganization(organizationView);

        mockMvc.perform(
                post("/api/organization/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(organizationView)))
                .andExpect(status().isOk());

        verify(organizationService, times(1)).updateOrganization(organizationView);
        verifyNoMoreInteractions(organizationService);
    }

    @Test
    public void saveOrganization() throws Exception {
        RequisiteView requisiteView = new RequisiteView(0, "6464646464", "464646464");
        CountryView cauntryView = new CountryView(0, "newCountry", 0);
        RegionView regionView = new RegionView(0, "newRegion");
        CityView cityView = new CityView(0, "newCity");
        StreetView streetView = new StreetView(0, "newStreet");
        HouseView houseView = new HouseView(0, "18B", 32, "25T");
        AddressView addressView = new AddressView(0, cauntryView, regionView, cityView, streetView, houseView);
        PhoneView phoneView = new PhoneView(0, "89173333333");
        OrganizationView organizationView = new OrganizationView(0, "OOO Test", "OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU PRIMER",
                requisiteView, addressView, phoneView, true);

        doNothing().when(organizationService).saveOrganization(organizationView);

        mockMvc.perform(
                post("/api/organization/save")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(organizationView)))
                .andExpect(status().isOk());

        verify(organizationService, times(1)).saveOrganization(organizationView);
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

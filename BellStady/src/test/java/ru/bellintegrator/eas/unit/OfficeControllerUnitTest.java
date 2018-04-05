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
import ru.bellintegrator.eas.office.controller.OfficeController;
import ru.bellintegrator.eas.office.model.view.OfficeView;
import ru.bellintegrator.eas.office.service.OfficeService;
import ru.bellintegrator.eas.organization.model.view.OrganizationView;
import ru.bellintegrator.eas.organization.model.view.address.AddressView;
import ru.bellintegrator.eas.organization.model.view.address.dependent.*;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;
import ru.bellintegrator.eas.organization.model.view.requisite.RequisiteView;

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
public class OfficeControllerUnitTest {
    private MockMvc mockMvc;

    @Mock
    private OfficeService officeService;

    @InjectMocks
    private OfficeController officeController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(officeController)
                .build();
    }

    @Test
    public void filterOffices() throws Exception {
        OrganizationView organizationView = new OrganizationView(1, "", "", new RequisiteView(), new AddressView(), new PhoneView(), true);
                List<OfficeView> officeViewList = Arrays.asList(new OfficeView(3, "Milex", new AddressView(),
                        new OrganizationView(), new PhoneView(), true));
        OfficeView officeView = new OfficeView(1, "Milex", new AddressView(), organizationView, new PhoneView(), false);

        when(officeService.filterOffices(officeView)).thenReturn(officeViewList);

        mockMvc.perform(post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(officeView)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].name", is("Milex")))
                .andExpect(jsonPath("$[0].isActive", is(true)));

        verify(officeService, times(1)).filterOffices(officeView);
        verifyNoMoreInteractions(officeService);
    }

    @Test
    public void deleteOfficeById() throws Exception {
        boolean delete = true;

        when(officeService.deleteOfficeById(3)).thenReturn(delete);

        mockMvc.perform(get("/api/office/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(officeService, times(1)).deleteOfficeById(3);
        verifyNoMoreInteractions(officeService);
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
        OfficeView officeView = new OfficeView(3, "newNameOffice", addressView, new OrganizationView(), phoneView, true);

        doNothing().when(officeService).updateOffice(officeView);

        mockMvc.perform(
                post("/api/office/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(officeView)))
                .andExpect(status().isOk());

        verify(officeService, times(1)).updateOffice(officeView);
        verifyNoMoreInteractions(officeService);
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
        OfficeView officeView = new OfficeView(0, "nameOffice", addressView, new OrganizationView(), phoneView, true);

        doNothing().when(officeService).saveOffice(officeView);

        mockMvc.perform(
                post("/api/office/save")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(officeView)))
                .andExpect(status().isOk());

        verify(officeService, times(1)).saveOffice(officeView);
        verifyNoMoreInteractions(officeService);
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

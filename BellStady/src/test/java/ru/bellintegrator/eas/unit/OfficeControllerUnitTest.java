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
import ru.bellintegrator.eas.office.model.Office;
import ru.bellintegrator.eas.office.service.OfficeService;
import ru.bellintegrator.eas.organization.model.Organization;
import ru.bellintegrator.eas.organization.model.address.Address;
import ru.bellintegrator.eas.organization.model.address.dependent.*;
import ru.bellintegrator.eas.organization.model.phone.Phone;
import ru.bellintegrator.eas.organization.model.requisite.Requisite;

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
        Organization organization = new Organization(1, "", "", new Requisite(), new Address(), new Phone(), true);
                List<Office> officeList = Arrays.asList(new Office(3, "Milex", new Address(),
                        new Organization(), new Phone(), true));
        Office office = new Office(1, "Milex", new Address(), organization, new Phone(), false);

        when(officeService.filterOffices(office)).thenReturn(officeList);

        mockMvc.perform(post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(office)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].name", is("Milex")))
                .andExpect(jsonPath("$[0].isActive", is(true)));

        verify(officeService, times(1)).filterOffices(office);
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
        Country cauntry = new Country(0, "newCountry", 0);
        Region region = new Region(0, "newRegion");
        City city = new City(0, "newCity");
        Street street = new Street(0, "newStreet");
        House house = new House(0, "21C", 32, "4A");
        Address address = new Address(0, cauntry, region, city, street, house);
        Phone phone = new Phone(0, "89174444444");
        Office office = new Office(3, "newNameOffice", address, new Organization(), phone, true);

        doNothing().when(officeService).updateOffice(office);

        mockMvc.perform(
                post("/api/office/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(office)))
                .andExpect(status().isOk());

        verify(officeService, times(1)).updateOffice(office);
        verifyNoMoreInteractions(officeService);
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

        doNothing().when(officeService).saveOffice(office);

        mockMvc.perform(
                post("/api/office/save")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(office)))
                .andExpect(status().isCreated());

        verify(officeService, times(1)).saveOffice(office);
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

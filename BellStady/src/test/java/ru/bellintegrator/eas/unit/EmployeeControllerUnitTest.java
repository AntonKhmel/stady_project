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
import ru.bellintegrator.eas.employee.controller.EmployeeController;
import ru.bellintegrator.eas.employee.model.view.EmployeeView;
import ru.bellintegrator.eas.employee.model.view.dependent.CitizenshipView;
import ru.bellintegrator.eas.employee.model.view.dependent.DocTypeView;
import ru.bellintegrator.eas.employee.model.view.dependent.PositionView;
import ru.bellintegrator.eas.employee.service.EmployeeService;
import ru.bellintegrator.eas.office.model.view.OfficeView;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;

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
public class EmployeeControllerUnitTest {
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeController)
                .build();
    }

    @Test
    public void filterEmployees() throws Exception {
        PositionView positionView = new PositionView(0, "programmer");
        List<EmployeeView> employeeViewList = Arrays.asList(new EmployeeView(0, "Ivan", "Ivanov",
                "Ivanovich", positionView, new DocTypeView(), new CitizenshipView(), new PhoneView(), new OfficeView(), true));
        EmployeeView employeeView = new EmployeeView(0, "Ivan", "", "", new PositionView(),
                new DocTypeView(), new CitizenshipView(), new PhoneView(), new OfficeView(), true);

        when(employeeService.filterEmployees(employeeView)).thenReturn(employeeViewList);

        mockMvc.perform(post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(employeeView)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("Ivan")))
                .andExpect(jsonPath("$[0].secondName", is("Ivanov")))
                .andExpect(jsonPath("$[0].middleName", is("Ivanovich")));

        verify(employeeService, times(1)).filterEmployees(employeeView);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void deleteEmployeeById() throws Exception {
        boolean delete = true;

        when(employeeService.deleteEmployeeById(3)).thenReturn(true);

        mockMvc.perform(get("/api/user/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(employeeService, times(1)).deleteEmployeeById(3);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void updateEmployee() throws Exception {
        PositionView positionView = new PositionView(0, "newPosition");
        DocTypeView docTypeView = new DocTypeView(0, 432, "newDocName", 21, "02.03.2018");
        CitizenshipView citizenshipView = new CitizenshipView(0, "newCitizenshipName", 232);
        PhoneView phoneView = new PhoneView(0, "89179999999");
        EmployeeView employeeView = new EmployeeView(1, "newFirstName", "newSecondName",
                "newMiddleName", positionView, docTypeView, citizenshipView, phoneView, new OfficeView(), true);

        doNothing().when(employeeService).updateEmployee(employeeView);

        mockMvc.perform(
                post("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(employeeView)))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).updateEmployee(employeeView);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void saveEmployee() throws Exception {
        PositionView positionView = new PositionView(0, "newPosition");
        DocTypeView docTypeView = new DocTypeView(0, 432, "newDocName", 21, "02.03.2018");
        CitizenshipView citizenshipView = new CitizenshipView(0, "newCitizenshipName", 232);
        PhoneView phoneView = new PhoneView(0, "89179999999");
        EmployeeView employeeView = new EmployeeView(0, "newFirstName", "newSecondName",
                "newMiddleName", positionView, docTypeView, citizenshipView, phoneView, new OfficeView(), true);

        doNothing().when(employeeService).saveEmployee(employeeView);

        mockMvc.perform(
                post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(employeeView)))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).saveEmployee(employeeView);
        verifyNoMoreInteractions(employeeService);
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

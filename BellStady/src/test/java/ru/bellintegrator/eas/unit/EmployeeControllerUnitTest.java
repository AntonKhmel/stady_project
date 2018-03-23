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
import ru.bellintegrator.eas.employee.model.Employee;
import ru.bellintegrator.eas.employee.model.dependent.Citizenship;
import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.employee.model.dependent.Position;
import ru.bellintegrator.eas.employee.service.EmployeeService;
import ru.bellintegrator.eas.organization.model.phone.Phone;

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
        Position position = new Position(0, "programmer");
        List<Employee> employeeList = Arrays.asList(new Employee(0, "Ivan", "Ivanov",
                "Ivanovich", position, new DocType(), new Citizenship(), new Phone(), true));
        Employee employee = new Employee(0, "Ivan", "", "", new Position(),
                new DocType(), new Citizenship(), new Phone(), true);
        String result = "[Office:{id: 3; name: Milex; is active: true;}]";

        when(employeeService.filterEmployees(employee)).thenReturn(employeeList);

        mockMvc.perform(post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("Ivan")))
                .andExpect(jsonPath("$[0].secondName", is("Ivanov")))
                .andExpect(jsonPath("$[0].middleName", is("Ivanovich")));

        verify(employeeService, times(1)).filterEmployees(employee);
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
        Position position = new Position(0, "newPosition");
        DocType docType = new DocType(0, 432, "newDocName", 21, "02.03.2018");
        Citizenship citizenship = new Citizenship(0, "newCitizenshipName", 232);
        Phone phone = new Phone(0, "89179999999");
        Employee employee = new Employee(1, "newFirstName", "newSecondName",
                "newMiddleName", position, docType, citizenship, phone,true);

        doNothing().when(employeeService).updateEmployee(employee);

        mockMvc.perform(
                post("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(employee)))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).updateEmployee(employee);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void saveEmployee() throws Exception {
        Position position = new Position(0, "newPosition");
        DocType docType = new DocType(0, 432, "newDocName", 21, "02.03.2018");
        Citizenship citizenship = new Citizenship(0, "newCitizenshipName", 232);
        Phone phone = new Phone(0, "89179999999");
        Employee employee = new Employee(0, "newFirstName", "newSecondName",
                "newMiddleName", position, docType, citizenship, phone,true);

        doNothing().when(employeeService).saveEmployee(employee);

        mockMvc.perform(
                post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(asJsonString(employee)))
                .andExpect(status().isCreated());

        verify(employeeService, times(1)).saveEmployee(employee);
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

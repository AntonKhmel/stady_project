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
import ru.bellintegrator.eas.employee.model.Employee;
import ru.bellintegrator.eas.employee.model.dependent.Citizenship;
import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.employee.model.dependent.Position;
import ru.bellintegrator.eas.organization.model.phone.Phone;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
public class EmployeeControllerIntegrationTest {
    private static final String BASE_URI = "http://localhost:7030/BellStady_war_exploded/api";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void filterEmployees() throws Exception {
        Employee employee = new Employee(0, "Ivan", "", "", new Position(),
                new DocType(), new Citizenship(), new Phone(), true);

        try {
            List<Employee> employeeList = Arrays.asList(restTemplate.postForObject(BASE_URI + "/user/list", employee, Employee[].class));
            assertNotNull(employeeList);
            assertThat(employeeList.get(0).getId(), is(2));
            assertThat(employeeList.get(0).getFirstName(), is("Ivan"));
            assertThat(employeeList.get(0).getSecondName(), is("Ivanov"));
            assertThat(employeeList.get(0).getMiddleName(), is("Ivanovich"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        };
    }

    @Test
    public void deleteEmployeeById() throws Exception {
        try {
            HttpStatus hs = restTemplate.getForObject(BASE_URI + "/user/1", HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void updateEmployee() throws Exception {
        Position position = new Position(0, "newPosition");
        DocType docType = new DocType(0, 432, "newDocName", 21, "02.03.2018");
        Citizenship citizenship = new Citizenship(0, "newCitizenshipName", 232);
        Phone phone = new Phone(0, "89179999999");
        Employee employee = new Employee(1, "newFirstName", "newSecondName",
                "newMiddleName", position, docType, citizenship, phone,true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/user/update", employee, HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        };
    }

    @Test
    public void saveEmployee() throws Exception {
        Position position = new Position(0, "newPosition");
        DocType docType = new DocType(0, 432, "newDocName", 21, "02.03.2018");
        Citizenship citizenship = new Citizenship(0, "newCitizenshipName", 232);
        Phone phone = new Phone(0, "89179999999");
        Employee employee = new Employee(0, "newFirstName", "newSecondName",
                "newMiddleName", position, docType, citizenship, phone,true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/user/save", employee, HttpStatus.class);
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

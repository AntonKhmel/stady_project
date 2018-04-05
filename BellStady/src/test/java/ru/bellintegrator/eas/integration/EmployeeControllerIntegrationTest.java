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
import ru.bellintegrator.eas.employee.model.view.EmployeeView;
import ru.bellintegrator.eas.employee.model.view.dependent.CitizenshipView;
import ru.bellintegrator.eas.employee.model.view.dependent.DocTypeView;
import ru.bellintegrator.eas.employee.model.view.dependent.PositionView;
import ru.bellintegrator.eas.office.model.view.OfficeView;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
public class EmployeeControllerIntegrationTest {
    private final Logger LOG = LoggerFactory.getLogger(EmployeeControllerIntegrationTest.class);

    private static final String BASE_URI = "http://localhost:7030/BellStady_war_exploded/api";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void addEmployees() {
        EmployeeView employeeView1 = new EmployeeView(0, "Ivan", "Ivanov",
                "Ivanovich", new PositionView(0, "Ingener"), new DocTypeView(0, 432, "Nakladnaya",
                21, "02.03.2018"), null, null, null, true);
        EmployeeView employeeView2 = new EmployeeView(0, "Ivan", "Ivanov",
                "Ivanovich", new PositionView(0, "Pekar"), new DocTypeView(0, 117, "Doverennost",
                24, "02.03.2018"), null, null, null, true);
        EmployeeView employeeView3 = new EmployeeView(0, "Petr", "Petrov",
                "Petrovich", new PositionView(0, "Pekar"), new DocTypeView(0, 117, "Doverennost",
                24, "02.03.2018"), null, null, null, true);
        try {
            HttpStatus hs1 = restTemplate.postForObject(BASE_URI + "/user/save", employeeView1, HttpStatus.class);
            HttpStatus hs2 = restTemplate.postForObject(BASE_URI + "/user/save", employeeView2, HttpStatus.class);
            HttpStatus hs3 = restTemplate.postForObject(BASE_URI + "/user/save", employeeView3, HttpStatus.class);
        } catch (HttpClientErrorException e){
            LOG.info("Create employees error: {}", e);
        }
    }

    @Test
    public void filterEmployees() throws Exception {
        EmployeeView employeeView = new EmployeeView(0, "Ivan", "", "", new PositionView(),
                new DocTypeView(), new CitizenshipView(), new PhoneView(), new OfficeView(), true);

        try {
            List<EmployeeView> employeeViewList = Arrays.asList(restTemplate.postForObject(BASE_URI + "/user/list", employeeView, EmployeeView[].class));
            assertNotNull(employeeViewList);
            assertThat(employeeViewList.get(0).getId(), is(1));
            assertThat(employeeViewList.get(0).getFirstName(), is("Ivan"));
            assertThat(employeeViewList.get(0).getSecondName(), is("Ivanov"));
            assertThat(employeeViewList.get(0).getMiddleName(), is("Ivanovich"));
            assertThat(employeeViewList.get(0).getPositionView().getName(), is("Ingener"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void deleteEmployeeById() throws Exception {
        try {
            HttpStatus hs = restTemplate.getForObject(BASE_URI + "/user/2", HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void updateEmployee() throws Exception {
        PositionView positionView = new PositionView(0, "newPosition");
        DocTypeView docTypeView = new DocTypeView(0, 432, "newDocName", 21, "02.03.2018");
        CitizenshipView citizenshipView = new CitizenshipView(0, "newCitizenshipName", 232);
        PhoneView phoneView = new PhoneView(0, "89179999999");
        EmployeeView employeeView = new EmployeeView(3, "newFirstName", "newSecondName",
                "newMiddleName", positionView, docTypeView, citizenshipView, phoneView, null, true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/user/update", employeeView, HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void saveEmployee() throws Exception {
        PositionView positionView = new PositionView(0, "newPosition");
        DocTypeView docTypeView = new DocTypeView(0, 432, "newDocName", 21, "02.03.2018");
        CitizenshipView citizenshipView = new CitizenshipView(0, "newCitizenshipName", 232);
        PhoneView phoneView = new PhoneView(0, "89179999999");
        EmployeeView employeeView = new EmployeeView(0, "newFirstName", "newSecondName",
                "newMiddleName", positionView, docTypeView, citizenshipView, phoneView, null, true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/user/save", employeeView, HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @After
    public void deleteEmployees() {
        try {
            HttpStatus hs1 = restTemplate.getForObject(BASE_URI + "/user/1", HttpStatus.class);
            HttpStatus hs2 = restTemplate.getForObject(BASE_URI + "/user/2", HttpStatus.class);
            HttpStatus hs3 = restTemplate.getForObject(BASE_URI + "/user/3", HttpStatus.class);
            HttpStatus hs4 = restTemplate.getForObject(BASE_URI + "/user/4", HttpStatus.class);
        } catch (HttpClientErrorException e){
            LOG.info("Delete employees error: {}", e);
        }
    }
}

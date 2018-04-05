package ru.bellintegrator.eas.integration;

import org.junit.Before;
import org.junit.Test;
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
import ru.bellintegrator.eas.organization.model.view.OrganizationView;
import ru.bellintegrator.eas.organization.model.view.address.AddressView;
import ru.bellintegrator.eas.organization.model.view.address.dependent.*;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;
import ru.bellintegrator.eas.organization.model.view.requisite.RequisiteView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml", "classpath:applicationContext.xml"})
public class OrganizationControllerIntegrationTest {
    private final Logger LOG = LoggerFactory.getLogger(OrganizationControllerIntegrationTest.class);

    private static final String BASE_URI = "http://localhost:7030/BellStady_war_exploded/api";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void addOrganization() {
        OrganizationView organizationView1 = new OrganizationView(0, "Abradoks",
                "OOO 'ABRADOKS'", null, new AddressView(0,
                        new CountryView(0, "Japan", 135), null,
                        null, null,null),
                new PhoneView(0, "8(111)222-22-22"), true);
        OrganizationView organizationView2 = new OrganizationView(0, "Rivera", "OOO 'RIVERA'",
                new RequisiteView(0, "6464646464", "464646464"), new AddressView(0,
                new CountryView(0, "Russia", 34), new RegionView(0, "Saratovskay oblast"),
                new CityView(0, "Saratov"), new StreetView(0, "Tarhova"),
                new HouseView(0, "17A", 32, "4M")),
                new PhoneView(0, "89172222222"), true);
        OrganizationView organizationView3 = new OrganizationView(0, "Prime",
                "OOO 'PRIME'", null,
                null, null, true);
        try {
            HttpStatus hs1 = restTemplate.postForObject(BASE_URI + "/organization/save", organizationView1, HttpStatus.class);
            HttpStatus hs2 = restTemplate.postForObject(BASE_URI + "/organization/save", organizationView2, HttpStatus.class);
        } catch (HttpClientErrorException e){
            LOG.info("Create organizations error: {}", e);
        }
    }

    @Test
    public void filterOrganizations() throws Exception {
        OrganizationView organizationView = new OrganizationView(0, "Abradoks", "", null,
                null, null, true);

        try {
            List<OrganizationView> organizationViewList = Arrays.asList(restTemplate.postForObject(
                    BASE_URI + "/organization/list", organizationView, OrganizationView[].class));
            assertNotNull(organizationViewList);
            assertThat(organizationViewList.get(0).getId(), is(1));
            assertThat(organizationViewList.get(0).getName(), is("Abradoks"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void getOrganizationById() throws Exception {
        try {
            OrganizationView organizationView = restTemplate.getForObject(BASE_URI + "/organization/2", OrganizationView.class);
            assertNotNull(organizationView);
            assertThat(organizationView.getId(), is(2));
            assertThat(organizationView.getName(), is("Rivera"));
            assertThat(organizationView.getFullName(), is("OOO 'RIVERA'"));
            assertThat(organizationView.getRequisiteView().getInn(), is("6464646464"));
            assertThat(organizationView.getRequisiteView().getCpp(), is("464646464"));
            assertThat(organizationView.getAddressView().getCountryView().getName(), is("Russia"));
            assertThat(organizationView.getAddressView().getRegionView().getName(), is("Saratovskay oblast"));
            assertThat(organizationView.getAddressView().getCityView().getName(), is("Saratov"));
            assertThat(organizationView.getAddressView().getStreetView().getName(), is("Tarhova"));
            assertThat(organizationView.getAddressView().getHouseView().getNumberHouse(), is("17A"));
            assertThat(organizationView.getAddressView().getHouseView().getNumberFlat(), is(32));
            assertThat(organizationView.getAddressView().getHouseView().getNumberOffice(), is("4M"));
            assertThat(organizationView.getPhoneView().getNumber(), is("89172222222"));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
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

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/organization/update", organizationView, HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    @Test
    public void saveOrganization() throws Exception {
        RequisiteView requisiteView = new RequisiteView(0, "6464646464", "464646464");
        CountryView cauntryView = new CountryView(0, "Japan", 135);
        RegionView regionView = new RegionView(0, "newRegion");
        CityView cityView = new CityView(0, "newCity");
        StreetView streetView = new StreetView(0, "newStreet");
        HouseView houseView = new HouseView(0, "18B", 32, "25T");
        AddressView addressView = new AddressView(0, cauntryView, regionView, cityView, streetView, houseView);
        PhoneView phoneView = new PhoneView(0, "89173333333");
        OrganizationView organizationView = new OrganizationView(0, "OOO Test", "OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU PRIMER",
                requisiteView, addressView, phoneView, true);

        try {
            HttpStatus hs = restTemplate.postForObject(BASE_URI + "/organization/save", organizationView, HttpStatus.class);
            assertThat(hs, is(HttpStatus.OK));
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }
}

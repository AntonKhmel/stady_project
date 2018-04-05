package ru.bellintegrator.eas.organization.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.dao.OrganizationDAO;
import ru.bellintegrator.eas.organization.model.Organization;
import ru.bellintegrator.eas.organization.model.address.Address;
import ru.bellintegrator.eas.organization.model.address.dependent.*;
import ru.bellintegrator.eas.organization.model.phone.Phone;
import ru.bellintegrator.eas.organization.model.requisite.Requisite;
import ru.bellintegrator.eas.organization.model.view.OrganizationView;
import ru.bellintegrator.eas.organization.model.view.address.AddressView;
import ru.bellintegrator.eas.organization.model.view.address.dependent.*;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;
import ru.bellintegrator.eas.organization.model.view.requisite.RequisiteView;
import ru.bellintegrator.eas.organization.service.OrganizationService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Хмель А.В.
 * class Service for object the OrganizationView by work with business logic
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final Logger LOG = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    OrganizationDAO organizationDAO;

    // calls the DAO object method on filters name, inn, isActive
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationView> filterOrganizations(OrganizationView organizationView) throws DataAccessError {
        LOG.info("call method DAO for getting list organizations by filter");

        List<Organization> organizationList = organizationDAO.filterOrganizations(organizationView);

        Function<Organization, OrganizationView> mapOrganization = p -> {
            OrganizationView view = new OrganizationView();
            view.setId(p.getId());
            view.setName(p.getName());
            view.setIsActive(p.getIsActive());

            return view;
        };

        return organizationList.stream()
                .map(mapOrganization)
                .collect(Collectors.toList());
    }

    // calls the DAO object method for get organization by id
    @Transactional(readOnly = true)
    @Override
    public OrganizationView getOrganizationById(int id) throws DataAccessError {
        LOG.info("call method DAO for get the organization by id");

        Organization organization = organizationDAO.getOrganizationById(id);

        OrganizationView view = new OrganizationView();
        view.setId(organization.getId());
        view.setName(organization.getName());
        view.setFullName(organization.getFullName());
        view.setRequisiteView(organization.getRequisite() != null ? new RequisiteView(organization.getRequisite().getId(),
                organization.getRequisite().getInn(), organization.getRequisite().getCpp()) : null);
        view.getRequisiteView().setCpp(organization.getRequisite().getCpp());
        view.setAddressView(organization.getAddress() != null ? new AddressView(0, organization.getAddress()
                .getCountry() != null ? new CountryView(0, organization.getAddress().getCountry()
                .getName(), organization.getAddress().getCountry().getCode()) : null, organization
                .getAddress().getRegion() != null ? new RegionView(0, organization.getAddress()
                .getRegion().getName()) : null, organization.getAddress().getCity() != null ?
                new CityView(0, organization.getAddress().getCity().getName()) : null, organization
                .getAddress().getStreet() != null ? new StreetView(0, organization.getAddress()
                .getStreet().getName()) : null, organization.getAddress().getHouse() != null ?
                new HouseView(0, organization.getAddress().getHouse().getNumberHouse(), organization
                        .getAddress().getHouse().getNumberFlat(), organization.getAddress().getHouse()
                        .getNumberOffice()) : null) : null);
        view.setPhoneView(organization.getPhone() != null ? new PhoneView(organization.getPhone().getId(),
                organization.getPhone().getNumber()) : null);
        view.getPhoneView().setNumber(organization.getPhone().getNumber());
        view.setIsActive(organization.getIsActive());

        return view;
    }

    // calls the DAO object method for update organization
    @Transactional()
    @Override
    public void updateOrganization(OrganizationView organizationView) throws DataAccessError {
        LOG.info("call method DAO for update the organization");

        organizationDAO.updateOrganization(organizationView);
    }

    // calls the DAO object method for save organization
    @Transactional()
    @Override
    public void saveOrganization(OrganizationView organizationView) throws DataAccessError {
        LOG.info("call method DAO for save the organization");

        Organization organization = new Organization();
        organization.setName(organizationView.getName());
        organization.setFullName(organizationView.getFullName());
        organization.setRequisite(organizationView.getRequisiteView() != null ? new Requisite(0,
                organizationView.getRequisiteView().getInn(), organizationView.getRequisiteView().getCpp()) : null);
        organization.setAddress(organizationView.getAddressView() != null ? new Address(0, organizationView.getAddressView()
                .getCountryView() != null ? new Country(0, organizationView.getAddressView().getCountryView()
                .getName(), organizationView.getAddressView().getCountryView().getCode()) : null, organizationView
                .getAddressView().getRegionView() != null ? new Region(0, organizationView.getAddressView()
                .getRegionView().getName()) : null, organizationView.getAddressView().getCityView() != null ?
                new City(0, organizationView.getAddressView().getCityView().getName()) : null, organizationView
                .getAddressView().getStreetView() != null ? new Street(0, organizationView.getAddressView()
                .getStreetView().getName()) : null, organizationView.getAddressView().getHouseView() != null ?
                new House(0, organizationView.getAddressView().getHouseView().getNumberHouse(), organizationView
                        .getAddressView().getHouseView().getNumberFlat(), organizationView.getAddressView().getHouseView()
                        .getNumberOffice()) : null) : null);
        organization.setPhone(organizationView.getPhoneView() != null ? new Phone(0,
                organizationView.getPhoneView().getNumber()) : null);
        organization.setIsActive(organizationView.getIsActive());

        organizationDAO.saveOrganization(organization);
    }
}

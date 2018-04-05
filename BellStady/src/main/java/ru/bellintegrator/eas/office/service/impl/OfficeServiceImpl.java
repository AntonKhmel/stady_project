package ru.bellintegrator.eas.office.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.office.dao.OfficeDAO;
import ru.bellintegrator.eas.office.model.Office;
import ru.bellintegrator.eas.office.model.view.OfficeView;
import ru.bellintegrator.eas.office.service.OfficeService;
import ru.bellintegrator.eas.organization.model.address.Address;
import ru.bellintegrator.eas.organization.model.address.dependent.*;
import ru.bellintegrator.eas.organization.model.phone.Phone;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Хмель А.В.
 * class Service for object the OfficeView by work with business logic
 */
@Service
public class OfficeServiceImpl implements OfficeService {
    private final Logger LOG = LoggerFactory.getLogger(OfficeServiceImpl.class);

    @Autowired
    private OfficeDAO officeDAO;

    // calls the DAO object method on filters orgId, name, phone, isActive
    @Transactional(readOnly = true)
    @Override
    public List<OfficeView> filterOffices(OfficeView officeView) throws DataAccessError {
        LOG.info("call method DAO for getting list officces by filter");

        List<Office> officeList = officeDAO.filterOffices(officeView);

        Function<Office, OfficeView> mapOffice = p -> {
            OfficeView view = new OfficeView();
            view.setId(p.getId());
            view.setName(p.getName());
            view.setIsActive(p.getIsActive());

            return view;
        };

        return officeList.stream()
                .map(mapOffice)
                .collect(Collectors.toList());
    }

    // calls the DAO object method for delete office
    @Transactional()
    @Override
    public boolean deleteOfficeById(int id) throws DataAccessError {
        LOG.info("call method DAO for delete the office by id");

        return officeDAO.deleteOfficeById(id);
    }

    // calls the DAO object method for update office
    @Transactional()
    @Override
    public void updateOffice(OfficeView officeView) throws DataAccessError {
        LOG.info("call method DAO for update the office");

        officeDAO.updateOffice(officeView);
    }

    // calls the DAO object method for save office
    @Transactional()
    @Override
    public void saveOffice(OfficeView officeView) throws DataAccessError {
        LOG.info("call method DAO for save the office");
        if (officeView != null) {
            Office office = new Office();
            office.setName(officeView.getName());
            office.setAddress(officeView.getAddressView() != null ? new Address(0, officeView.getAddressView()
                    .getCountryView() != null ? new Country(0, officeView.getAddressView().getCountryView()
                    .getName(), officeView.getAddressView().getCountryView().getCode()) : null, officeView
                    .getAddressView().getRegionView() != null ? new Region(0, officeView.getAddressView()
                    .getRegionView().getName()) : null, officeView.getAddressView().getCityView() != null ?
                    new City(0, officeView.getAddressView().getCityView().getName()) : null, officeView
                    .getAddressView().getStreetView() != null ? new Street(0, officeView.getAddressView()
                    .getStreetView().getName()) : null, officeView.getAddressView().getHouseView() != null ?
                    new House(0, officeView.getAddressView().getHouseView().getNumberHouse(), officeView
                    .getAddressView().getHouseView().getNumberFlat(), officeView.getAddressView().getHouseView()
                    .getNumberOffice()) : null) : null);
            office.setPhone(officeView.getPhoneView() != null ? new Phone(0,
                    officeView.getPhoneView().getNumber()) : null);
            office.setIsActive(officeView.getIsActive());

            officeDAO.saveOffice(office);
        } else {
            throw new DataAccessError("Value object the OfficeView equals null");
        }
    }
}

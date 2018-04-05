package ru.bellintegrator.eas.employee.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.employee.dao.EmployeeDAO;
import ru.bellintegrator.eas.employee.model.Employee;
import ru.bellintegrator.eas.employee.model.dependent.Citizenship;
import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.employee.model.dependent.Position;
import ru.bellintegrator.eas.employee.model.view.EmployeeView;
import ru.bellintegrator.eas.employee.service.EmployeeService;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.organization.model.phone.Phone;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Хмель А.В.
 * class Service for object the EmployeeView by work with business logic
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeDAO employeeDAO;

    // calls the DAO object method on filters officeId, firstName, secondName, middleName,
    // position, docCode, citizenshipCode
    @Transactional(readOnly = true)
    @Override
    public List<EmployeeView> filterEmployees(EmployeeView employeeView) throws DataAccessError {
        LOG.info("call method DAO for getting list employees by filter");

        List<Employee> employeeList = employeeDAO.filterEmployees(employeeView);

        Function<Employee, EmployeeView> mapEmployee = p -> {
            EmployeeView view = new EmployeeView();
            view.setId(p.getId());
            view.setFirstName(p.getFirstName());
            view.setSecondName(p.getSecondName());
            view.setMiddleName(p.getMiddleName());
            view.getPositionView().setName(p.getPosition().getName());
            view.getDocTypeView().setDocCode(p.getDocType().getDocCode());
            view.getCitizenshipView().setCode(p.getCitizenship().getCode());

            return view;
        };

        return employeeList.stream()
                .map(mapEmployee)
                .collect(Collectors.toList());
    }

    // calls the DAO object method for delete employee
    @Transactional()
    @Override
    public boolean deleteEmployeeById(int id) throws DataAccessError {
        LOG.info("call method DAO for delete the employee by id");

        return employeeDAO.deleteEmployeeById(id);
    }

    // calls the DAO object method for update employee
    @Transactional()
    @Override
    public void updateEmployee(EmployeeView employeeView) throws DataAccessError {
        LOG.info("call method DAO for update the employee");

        employeeDAO.updateEmployee(employeeView);
    }

    // calls the DAO object method for save employee
    @Transactional()
    @Override
    public void saveEmployee(EmployeeView employeeView) throws DataAccessError {
        LOG.info("call method DAO for save the employee");

        if (employeeView != null) {
            Employee employee = new Employee();
            employee.setFirstName(employeeView.getFirstName());
            employee.setSecondName(employeeView.getSecondName());
            employee.setMiddleName(employeeView.getMiddleName());
            employee.setPosition(employeeView.getPositionView() != null ? new Position(0,
                    employeeView.getPositionView().getName()) : null);
            employee.setPhone(employeeView.getPhoneView() != null ? new Phone(0,
                    employeeView.getPhoneView().getNumber()) : null);
            employee.setDocType(employeeView.getDocTypeView() != null ? new DocType(0,
                    employeeView.getDocTypeView().getDocCode(), employeeView.getDocTypeView().getDocName(),
                    employeeView.getDocTypeView().getDocNumber(), employeeView.getDocTypeView().getDocDate()) : null);
            employee.setCitizenship(employeeView.getCitizenshipView() != null ? new Citizenship(0,
                    employeeView.getCitizenshipView().getName(), employeeView.getCitizenshipView().getCode()) : null);
            employee.setIsIdentified(employeeView.getIsIdentified());

            employeeDAO.saveEmployee(employee);
        } else {
            throw new DataAccessError("Value object the EmployeeView equals null");
        }
    }
}

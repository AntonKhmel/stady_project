package ru.bellintegrator.eas.employee.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.employee.dao.EmployeeDAO;
import ru.bellintegrator.eas.employee.model.Employee;
import ru.bellintegrator.eas.employee.model.view.EmployeeView;
import ru.bellintegrator.eas.exception.DataAccessError;
import ru.bellintegrator.eas.office.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * @author Хмель А.В.
 * class DAO for work with employees
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    private final Logger LOG = LoggerFactory.getLogger(EmployeeDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    // get a list of employees by filters officeId, firstName, secondName, middleName, position, docCode, citizenshipCode
    @Override
    public List<Employee> filterEmployees(EmployeeView employeeView) throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);

            Root<Employee> root = criteria.from(Employee.class);
            criteria.where(builder.equal(root.get("firstName"), "Ivan"));
            if (employeeView != null) {
                if (employeeView.getOfficeView() != null && employeeView.getOfficeView().getId() != 0) {
                    criteria.where(builder.equal(root.get("office").get("id"), employeeView.getOfficeView().getId()));
                    if (employeeView.getFirstName() != null && !employeeView.getFirstName().isEmpty()) {
                        criteria.where(builder.equal(root.get("firstName"), employeeView.getFirstName()));
                        if (employeeView.getSecondName() != null && !employeeView.getSecondName().isEmpty()) {
                            criteria.where(builder.equal(root.get("secondName"), employeeView.getSecondName()));
                            if (employeeView.getMiddleName() != null && !employeeView.getMiddleName().isEmpty()) {
                                criteria.where(builder.equal(root.get("middleName"), employeeView.getMiddleName()));
                                if (employeeView.getPositionView() != null && !employeeView.getPositionView().getName()
                                        .isEmpty()) {
                                    criteria.where(builder.equal(root.get("position").get("name"),
                                            employeeView.getPositionView().getName()));
                                    if (employeeView.getDocTypeView().getDocCode() != 0) {
                                        criteria.where(builder.equal(root.get("docType").get("docCode"),
                                                employeeView.getDocTypeView().getDocCode()));
                                        if (employeeView.getCitizenshipView().getCode() != 0) {
                                            criteria.where(builder.equal(root.get("citizenship").get("code"),
                                                    employeeView.getCitizenshipView().getCode()));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            TypedQuery<Employee> query = entityManager.createQuery(criteria);
            List<Employee> employeeList = query.getResultList();

            LOG.info("getting list employees by filter");

            return employeeList != null ? employeeList : Collections.emptyList();
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "filterEmployees object the EmployeeDAOImpl");
        }
    }

     // delete the employee
    @Override
    public boolean deleteEmployeeById(int id) throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Office> criteria = builder.createCriteriaDelete(Office.class);
            Root<Office> root = criteria.from(Office.class);
            criteria.where(builder.equal(root.get("id"), id));
            int result = entityManager.createQuery(criteria).executeUpdate();

            LOG.info("delete the employee by id");

            return result > 0 ? true : false;
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "deleteEmployeeById object the EmployeeDAOImpl");
        }
    }

    // update the employee
    @Override
    public void updateEmployee(EmployeeView employeeView) throws DataAccessError {
        try {
            Employee employee = entityManager.find(Employee.class, employeeView.getId());
            employee.setFirstName(employeeView.getFirstName());
            employee.setSecondName(employeeView.getSecondName());
            employee.setMiddleName(employeeView.getMiddleName());
            employee.getPosition().setName(employeeView.getPositionView().getName());
            employee.getPhone().setNumber(employeeView.getPhoneView().getNumber());
            employee.getDocType().setDocName(employeeView.getDocTypeView().getDocName());
            employee.getDocType().setDocNumber(employeeView.getDocTypeView().getDocNumber());
            employee.getDocType().setDocDate(employeeView.getDocTypeView().getDocDate());
            employee.getCitizenship().setName(employeeView.getCitizenshipView().getName());
            employee.getCitizenship().setCode(employeeView.getCitizenshipView().getCode());
            employee.setIsIdentified(employeeView.getIsIdentified());
            entityManager.merge(employee);

            LOG.info("update the employee");
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "updateEmployee object the EmployeeDAOImpl");
        }
    }

    // save the employee
    @Override
    public void saveEmployee(Employee employee) throws DataAccessError {
        try {
            entityManager.persist(employee);

            LOG.info("save the employee");
        } catch (Exception ex) {
            throw new DataAccessError("Data access error in the method of the " +
                    "saveEmployee object the EmployeeDAOImpl");
        }
    }
}

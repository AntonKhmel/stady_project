package ru.bellintegrator.eas.employee.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.employee.dao.EmployeeDAO;
import ru.bellintegrator.eas.employee.model.Employee;
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
    public List<Employee> filterEmployees(Employee employee) throws DataAccessError {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);

            Root<Employee> root = criteria.from(Employee.class);
            criteria.where(builder.equal(root.get("firstName"), "Ivan"));
            if (employee != null) {
                if (employee.getOffice() != null && employee.getOffice().getId() != 0) {
                    criteria.where(builder.equal(root.get("office").get("id"), employee.getOffice().getId()));
                    if (employee.getFirstName() != null && !employee.getFirstName().isEmpty()) {
                        criteria.where(builder.equal(root.get("firstName"), employee.getFirstName()));
                        if (employee.getSecondName() != null && !employee.getSecondName().isEmpty()) {
                            criteria.where(builder.equal(root.get("secondName"), employee.getSecondName()));
                            if (employee.getMiddleName() != null && !employee.getMiddleName().isEmpty()) {
                                criteria.where(builder.equal(root.get("middleName"), employee.getMiddleName()));
                                if (employee.getPosition() != null && !employee.getPosition().getName().isEmpty()) {
                                    criteria.where(builder.equal(root.get("position").get("name"),
                                            employee.getPosition().getName()));
                                    if (employee.getDocType().getDocCode() != 0) {
                                        criteria.where(builder.equal(root.get("docType").get("docCode"),
                                                employee.getDocType().getDocCode()));
                                        if (employee.getCitizenship().getCode() != 0) {
                                            criteria.where(builder.equal(root.get("citizenship").get("code"),
                                                    employee.getCitizenship().getCode()));
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
    public void updateEmployee(Employee employee) throws DataAccessError {
        try {
            Employee getEmployee = entityManager.find(Employee.class, employee.getId());
            getEmployee.setFirstName(employee.getFirstName());
            getEmployee.setSecondName(employee.getSecondName());
            getEmployee.setMiddleName(employee.getMiddleName());
            getEmployee.getPosition().setName(employee.getPosition().getName());
            getEmployee.getPhone().setNumber(employee.getPhone().getNumber());
            getEmployee.getDocType().setDocCode(employee.getDocType().getDocCode());
            getEmployee.getDocType().setDocName(employee.getDocType().getDocName());
            getEmployee.getDocType().setDocNumber(employee.getDocType().getDocNumber());
            getEmployee.getDocType().setDocDate(employee.getDocType().getDocDate());
            getEmployee.getCitizenship().setName(employee.getCitizenship().getName());
            getEmployee.getCitizenship().setCode(employee.getCitizenship().getCode());
            getEmployee.setIsIdentified(employee.getIsIdentified());
            entityManager.merge(getEmployee);

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

package ru.bellintegrator.eas.employee.model.view;

import ru.bellintegrator.eas.employee.model.view.dependent.CitizenshipView;
import ru.bellintegrator.eas.employee.model.view.dependent.DocTypeView;
import ru.bellintegrator.eas.employee.model.view.dependent.PositionView;
import ru.bellintegrator.eas.office.model.view.OfficeView;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;

import java.util.Objects;

/**
 * @author Хмель А.В.
 * entity EmployeeView
 */
public class EmployeeView {
    private int id;
    private String firstName;
    private String secondName;
    private String middleName;
    private PositionView positionView;
    private DocTypeView docTypeView;
    private CitizenshipView citizenshipView;
    private PhoneView phoneView;
    private OfficeView officeView;
    private boolean isIdentified;

    public EmployeeView(int id, String firstName, String secondName, String middleName, PositionView positionView,
                    DocTypeView docTypeView, CitizenshipView citizenshipView, PhoneView phoneView, OfficeView officeView,
                    boolean isIdentified) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.positionView = positionView;
        this.docTypeView = docTypeView;
        this.citizenshipView = citizenshipView;
        this.phoneView = phoneView;
        this.officeView = officeView;
        this.isIdentified = isIdentified;
    }

    /**
     * constructor for jackson
     */
    public EmployeeView() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public PositionView getPositionView() {
        return positionView;
    }

    public void setPositionView(PositionView positionView) {
        this.positionView = positionView;
    }

    public DocTypeView getDocTypeView() {
        return docTypeView;
    }

    public void setDocTypeView(DocTypeView docTypeView) {
        this.docTypeView = docTypeView;
    }

    public CitizenshipView getCitizenshipView() {
        return citizenshipView;
    }

    public void setCitizenshipView(CitizenshipView citizenshipView) {
        this.citizenshipView = citizenshipView;
    }

    public PhoneView getPhoneView() {
        return phoneView;
    }

    public void setPhoneView(PhoneView phoneView) {
        this.phoneView = phoneView;
    }

    public OfficeView getOfficeView() {
        return officeView;
    }

    public void setOfficeView(OfficeView officeView) {
        this.officeView = officeView;
    }

    public boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(boolean isIdentified) {
        isIdentified = isIdentified;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeView employeeView = (EmployeeView) o;
        if (id != this.id) return false;
        if (firstName != null ? !firstName.equals(this.firstName) : this.firstName != null) return false;
        if (secondName != null ? !secondName.equals(this.secondName) : this.secondName != null) return false;
        if (middleName != null ? !middleName.equals(this.middleName) : this.middleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);

        return result;
    }

    /**
     * @return the string representation of the object EmployeeView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EmployeeView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; first name: ");
        builder.append(getFirstName());
        builder.append("; second name: ");
        builder.append(getSecondName());
        builder.append("; middle name: ");
        builder.append(getMiddleName());
        builder.append("; ");
        if (positionView != null) {
            builder.append(getPositionView());
        }
        if (docTypeView != null) {
            builder.append(getDocTypeView());
        }
        if (phoneView != null) {
            builder.append(getPhoneView());
        }
        if (officeView != null) {
            builder.append(getOfficeView());
        }
        if (citizenshipView != null) {
            builder.append(getCitizenshipView());
        }
        builder.append("; is identified: ");
        builder.append(getIsIdentified());
        builder.append("}");

        return builder.toString();
    }
}

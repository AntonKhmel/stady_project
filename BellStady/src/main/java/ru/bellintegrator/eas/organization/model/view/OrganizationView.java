package ru.bellintegrator.eas.organization.model.view;

import ru.bellintegrator.eas.organization.model.view.address.AddressView;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;
import ru.bellintegrator.eas.organization.model.view.requisite.RequisiteView;

/**
 * @author Хмель А.В.
 * entity OrganizationView
 */
public class OrganizationView {
    private int id;
    private String name;
    private String fullName;
    private RequisiteView requisiteView;
    private AddressView addressView;
    private PhoneView phoneView;
    private boolean isActive;

    public OrganizationView(int id, String name, String fullName, RequisiteView requisiteView,
                            AddressView addressView, PhoneView phoneView, boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.requisiteView = requisiteView;
        this.addressView = addressView;
        this.phoneView = phoneView;
        this.isActive = isActive;
    }

    /**
     * constructor for jackson
     */
    public OrganizationView() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public RequisiteView getRequisiteView() {
        return requisiteView;
    }

    public void setRequisiteView(RequisiteView requisiteView) {
        this.requisiteView = requisiteView;
    }

    public AddressView getAddressView() {
        return addressView;
    }

    public void setAddressView(AddressView addressView) {
        this.addressView = addressView;
    }

    public PhoneView getPhoneView() {
        return phoneView;
    }

    public void setPhoneView(PhoneView phoneView) {
        this.phoneView = phoneView;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        isActive = isActive;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationView organizationView = (OrganizationView) o;
        if (id != this.id) return false;
        if (name != null ? !name.equals(this.name) : this.name != null) return false;
        if (fullName != null ? !fullName.equals(this.fullName) : this.fullName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);

        return result;
    }

    /**
     * @return the string representation of the object OrganizationView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrganizationView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; name: ");
        builder.append(getName());
        builder.append(";full name: ");
        builder.append(getFullName());
        if (requisiteView != null) {
            builder.append(getRequisiteView());
        }
        if (addressView != null) {
            builder.append(getAddressView());
        }
        if (phoneView != null) {
            builder.append(getPhoneView());
        }
        builder.append(" is active: ");
        builder.append(getIsActive());
        builder.append("}");

        return builder.toString();
    }
}

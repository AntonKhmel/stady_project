package ru.bellintegrator.eas.office.model.view;

import ru.bellintegrator.eas.organization.model.view.OrganizationView;
import ru.bellintegrator.eas.organization.model.view.address.AddressView;
import ru.bellintegrator.eas.organization.model.view.phone.PhoneView;

/**
 * @author Хмель А.В.
 * class OfficeView
 */
public class OfficeView {
    private int id;
    private String name;
    private AddressView addressView;
    private OrganizationView organizationView;
    private PhoneView phoneView;
    private boolean isActive;

    public OfficeView(int id, String name, AddressView addressView, OrganizationView organizationView,
                      PhoneView phoneView, boolean isActive) {
        this.id = id;
        this.name = name;
        this.addressView = addressView;
        this.organizationView = organizationView;
        this.phoneView = phoneView;
        this.isActive = isActive;
    }

    /**
     * constructor for jackson
     */
    public OfficeView() {
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

    public AddressView getAddressView() {
        return addressView;
    }

    public void setAddressView(AddressView addressView) {
        this.addressView = addressView;
    }

    public OrganizationView getOrganizationView() {
        return organizationView;
    }

    public void setOrganizationView(OrganizationView organizationView) {
        this.organizationView = organizationView;
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
        OfficeView officeView = (OfficeView) o;
        if (id != this.id) return false;
        if (name != null ? !name.equals(this.name) : this.name != null) return false;
        if (addressView != null ? !addressView.equals(this.addressView) : this.addressView != null) return false;
        if (organizationView != null ? !organizationView.equals(this.organizationView) : this.organizationView != null) return false;
        if (phoneView != null ? !phoneView.equals(this.phoneView) : this.phoneView != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (addressView != null ? addressView.hashCode() : 0);
        result = 31 * result + (organizationView != null ? organizationView.hashCode() : 0);
        result = 31 * result + (phoneView != null ? phoneView.hashCode() : 0);

        return result;
    }

    /**
     * @return the string representation of the object OfficeView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OfficeView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; name: ");
        builder.append(getName());
        builder.append("; ");
        if (addressView != null) {
            builder.append(getAddressView());
        }
        if (organizationView != null) {
            builder.append(getOrganizationView());
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

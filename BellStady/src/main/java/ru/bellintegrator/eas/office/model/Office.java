package ru.bellintegrator.eas.office.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.bellintegrator.eas.organization.model.Organization;
import ru.bellintegrator.eas.organization.model.address.Address;
import ru.bellintegrator.eas.organization.model.phone.Phone;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Хмель А.В.
 * entity Office
 */
@Entity
@Table(name = "Office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // the service field of the hibernate for version control
    @Version
    private Integer version;

    @Basic(optional = false)
    @Size(min = 5, max = 50)
    @Column(name = "name")
    private String name;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @Basic(optional = false)
    @Column(name="is_active")
    private boolean isActive;

    public Office(int id, String name, Address address, Organization organization, Phone phone, boolean isActive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.organization = organization;
        this.phone = phone;
        this.isActive = isActive;
    }

    /**
     * constructor for the hibernate
     */
    public Office() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
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
        Office office = (Office) o;
        if (id != this.id) return false;
        if (version != this.version) return false;
        if (name != null ? !name.equals(this.name) : this.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + version;

        return result;
    }

    /**
     * @return the string representation of the object Office
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Office:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; name: ");
        builder.append(getName());
        builder.append("; ");
        if (address != null) {
            builder.append(address);
        }
        if (phone != null) {
            builder.append(phone);
        }
        builder.append(" is active: ");
        builder.append(getIsActive());
        builder.append("}");

        return builder.toString();
    }
}

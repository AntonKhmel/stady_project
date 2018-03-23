package ru.bellintegrator.eas.organization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.bellintegrator.eas.organization.model.address.Address;
import ru.bellintegrator.eas.organization.model.phone.Phone;
import ru.bellintegrator.eas.organization.model.requisite.Requisite;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Хмель А.В.
 * entity Organization
 */
@Entity
@Table(name = "Organization")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // the service field of the hibernate for version control
    @Version
    private Integer version;

    @Basic(optional = false)
    @Size(min = 5, max = 50)
    @Column(name = "short_name")
    private String name;

    @Basic(optional = false)
    @Size(min = 5, max = 150)
    @Column(name = "full_name")
    private String fullName;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "recvisit_id")
    private Requisite requisite;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @Basic(optional = false)
    @Column(name="is_active")
    private boolean isActive;

    public Organization(int id, String name, String fullName, Requisite requisite,
                        Address address, Phone phone, boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.requisite = requisite;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    /**
     * constructor for the hibernate
     */
    public Organization() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Requisite getRequisite() {
        return requisite;
    }

    public void setRequisite(Requisite requisite) {
        this.requisite = requisite;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        Organization organization = (Organization) o;
        if (id != this.id) return false;
        if (version != this.version) return false;
        if (name != null ? !name.equals(this.name) : this.name != null) return false;
        if (fullName != null ? !fullName.equals(this.fullName) : this.fullName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + version;

        return result;
    }

    /**
     * @return the string representation of the object Organization
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Organization:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; name: ");
        builder.append(getName());
        builder.append(";full name: ");
        builder.append(getFullName());
        if (requisite != null) {
            builder.append(requisite);
        }
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


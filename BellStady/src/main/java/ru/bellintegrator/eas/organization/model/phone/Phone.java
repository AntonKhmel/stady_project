package ru.bellintegrator.eas.organization.model.phone;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Хмель А.В.
 * entity Phone
 */
@Entity
@Table(name = "Phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // the service field of the hibernate for version control
    @Version
    private Integer version;

    @Basic(optional = false)
    @Size(min = 5, max = 15)
    @Column(name = "number_phone")
    private String number;

    public Phone(int id, String number) {
        this.id = id;
        this.number = number;
    }

    /**
     * constructor for the hibernate
     */
    public Phone() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

// equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        if (id != this.id) return false;
        if (version != this.version) return false;
        if (number != null ? !number.equals(this.number) : this.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + version;

        return result;
    }

    /**
     * @return the string representation of the object Phone
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("phone:");
        builder.append("{number: ");
        builder.append(getNumber());
        builder.append("; ");
        builder.append("}");

        return builder.toString();
    }
}

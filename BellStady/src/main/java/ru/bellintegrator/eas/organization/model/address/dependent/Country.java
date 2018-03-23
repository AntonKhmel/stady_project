package ru.bellintegrator.eas.organization.model.address.dependent;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Хмель А.В.
 * entity Country
 */
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // the service field of the hibernate for version control
    @Version
    private Integer version;

    @Basic(optional = false)
    @Size(min = 3, max = 20)
    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private int code;

    public Country(int id, String name, int code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    /**
     * constructor for the hibernate
     */
    public Country() {
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
// equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        if (id != this.id) return false;
        if (version != this.version) return false;
        if (name != null ? !name.equals(this.name) : this.name != null) return false;
        if (code != 0 ? !(code == (this.code)) : this.code != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != 0 ? code : 0);
        result = 31 * result + version;

        return result;
    }

    /**
     * @return the string representation of the object Country
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("country:");
        builder.append("{name: ");
        builder.append(getName());
        builder.append("; code: ");
        builder.append(getCode());
        builder.append(";");
        builder.append("}");

        return builder.toString();
    }

    public String shortToString() {
        StringBuilder builder = new StringBuilder();
        builder.append("country:");
        builder.append("{name: ");
        builder.append(getName());
        builder.append("; ");
        builder.append("}");

        return builder.toString();
    }
}

package ru.bellintegrator.eas.employee.model.dependent;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Хмель А.В.
 * entity Citizenship
 */
@Entity
@Table(name = "Citizenship")
public class Citizenship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // the service field of the hibernate for version control
    @Version
    private Integer version;

    @Basic(optional = false)
    @Size(min = 2, max = 70)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "code")
    private int code;

    public Citizenship(int id, String name, int code) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    /**
     * constructor for the hibernate
     */
    public Citizenship() {
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
        Citizenship citizenship = (Citizenship) o;
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
     * @return the string representation of the object Citizenship
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("citizenship:");
        builder.append("{name: ");
        builder.append(getName());
        builder.append("; code: ");
        builder.append(getCode());
        builder.append("}; ");

        return builder.toString();
    }
}

package ru.bellintegrator.eas.organization.model.requisite;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Хмель А.В.
 * entity Requisite
 */
@Entity
@Table(name = "Requisite")
public class Requisite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // the service field of the hibernate for version control
    @Version
    private Integer version;

    @Basic(optional = false)
    @Size(min = 6, max = 15)
    @Column(name = "inn")
    private String inn;

    @Basic(optional = false)
    @Size(min = 6, max = 15)
    @Column(name = "cpp")
    private String cpp;

    public Requisite(int id, String inn, String cpp) {
        this.id =id;
        this.inn = inn;
        this.cpp = cpp;
    }

    /**
     * constructor for the hibernate
     */
    public Requisite() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getCpp() {
        return cpp;
    }

    public void setCpp(String cpp) {
        this.cpp = cpp;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requisite recvisit = (Requisite) o;
        if (id != this.id) return false;
        if (version != this.version) return false;
        if (inn != null ? !inn.equals(this.inn) : this.inn != null) return false;
        if (cpp != null ? !cpp.equals(this.cpp) : this.cpp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (inn != null ? inn.hashCode() : 0);
        result = 31 * result + (cpp != null ? cpp.hashCode() : 0);
        result = 31 * result + version;

        return result;
    }

    /**
     * @return the string representation of the object Recvisit
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("recvisit:");
        builder.append("{inn: ");
        builder.append(getInn());
        builder.append("; cpp: ");
        builder.append(getCpp());
        builder.append("; ");
        builder.append("}");

        return builder.toString();
    }
}


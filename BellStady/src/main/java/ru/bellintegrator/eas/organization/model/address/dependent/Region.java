package ru.bellintegrator.eas.organization.model.address.dependent;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Хмель А.В.
 * entity Region
 */
@Entity
@Table(name = "Region")
public class Region {
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

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * constructor for the hibernate
     */
    public Region() {
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

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
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
     * @return the string representation of the object Region
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("region:");
        builder.append("{name: ");
        builder.append(getName());
        builder.append("; ");
        builder.append("}");

        return builder.toString();
    }
}

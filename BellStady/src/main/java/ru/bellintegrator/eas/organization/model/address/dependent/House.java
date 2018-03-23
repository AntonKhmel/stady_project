package ru.bellintegrator.eas.organization.model.address.dependent;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Хмель А.В.
 * entity House
 */
@Entity
@Table(name = "House")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // the service field of the hibernate for version control
    @Version
    private Integer version;

    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @Column(name = "number_house")
    private String numberHouse;

    @Column(name = "number_flat")
    private int numberFlat;

    @Size(min = 1, max = 10)
    @Column(name = "number_office")
    private String numberOffice;

    public House(int id, String numberHouse, int numberFlat, String numberOffice) {
        this.id = id;
        this.numberHouse = numberHouse;
        this.numberFlat = numberFlat;
        this.numberOffice = numberOffice;
    }

    /**
     * constructor for the hibernate
     */
    public House() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    public int getNumberFlat() {
        return numberFlat;
    }

    public void setNumberFlat(int numberFlat) {
        this.numberFlat = numberFlat;
    }

    public String getNumberOffice() {
        return numberOffice;
    }

    public void setNumberOffice(String numberOffice) {
        this.numberOffice = numberOffice;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        if (id != this.id) return false;
        if (version != this.version) return false;
        if (numberHouse != null ? !numberHouse.equals(this.numberHouse) : this.numberHouse != null) return false;
        if (numberFlat != 0 ? !(numberFlat == (this.numberFlat)) : this.numberFlat != 0) return false;
        if (numberOffice != null ? !numberOffice.equals(this.numberOffice) : this.numberOffice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (numberHouse != null ? numberHouse.hashCode() : 0);
        result = 31 * result + (numberFlat != 0 ? numberFlat : 0);
        result = 31 * result + (numberOffice != null ? numberOffice.hashCode() : 0);
        result = 31 * result + version;

        return result;
    }

    /**
     * @return the string representation of the object House
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("house:");
        builder.append("{number house: ");
        builder.append(getNumberHouse());
        builder.append("; number flat: ");
        builder.append(getNumberFlat());
        builder.append("; number office: ");
        builder.append(getNumberOffice());
        builder.append("; ");
        builder.append("}");

        return builder.toString();
    }
}

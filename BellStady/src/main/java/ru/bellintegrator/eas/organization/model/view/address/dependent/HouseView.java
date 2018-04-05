package ru.bellintegrator.eas.organization.model.view.address.dependent;

import ru.bellintegrator.eas.organization.model.address.dependent.House;

/**
 * @author Хмель А.В.
 * class HouseView
 */
public class HouseView {
    private int id;
    private String numberHouse;
    private int numberFlat;
    private String numberOffice;

    public HouseView(int id, String numberHouse, int numberFlat, String numberOffice) {
        this.id = id;
        this.numberHouse = numberHouse;
        this.numberFlat = numberFlat;
        this.numberOffice = numberOffice;
    }

    /**
     * constructor for jackson
     */
    public HouseView() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        HouseView houseView = (HouseView) o;
        if (id != this.id) return false;
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

        return result;
    }

    /**
     * @return the string representation of the object HouseView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HouseView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; number house: ");
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

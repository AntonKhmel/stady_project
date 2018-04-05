package ru.bellintegrator.eas.organization.model.view.address.dependent;

import ru.bellintegrator.eas.organization.model.address.dependent.City;

/**
 * @author Хмель А.В.
 * class CityView
 */
public class CityView {
    private int id;
    private String name;

    public CityView(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * constructor for jackson
     */
    public CityView() {
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

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityView cityView = (CityView) o;
        if (id != this.id) return false;
        if (name != null ? !name.equals(this.name) : this.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

    /**
     * @return the string representation of the object CityView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CityView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; name: ");
        builder.append(getName());
        builder.append("; ");
        builder.append("}");

        return builder.toString();
    }
}

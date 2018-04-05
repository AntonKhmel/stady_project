package ru.bellintegrator.eas.organization.model.view.address.dependent;

/**
 * @author Хмель А.В.
 * class StreetView
 */
public class StreetView {
    private int id;
    private String name;

    public StreetView(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * constructor for jackson
     */
    public StreetView() {
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
        StreetView streetView = (StreetView) o;
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
     * @return the string representation of the object StreetView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StreetView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; name: ");
        builder.append(getName());
        builder.append("; ");
        builder.append("}");

        return builder.toString();
    }
}

package ru.bellintegrator.eas.organization.model.view.address.dependent;

/**
 * @author Хмель А.В.
 * class CountryView
 */
public class CountryView {
    private int id;
    private String name;
    private int code;

    public CountryView(int id, String name, int code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    /**
     * constructor for jackson
     */
    public CountryView() {
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
        CountryView countryView = (CountryView) o;
        if (id != this.id) return false;
        if (name != null ? !name.equals(this.name) : this.name != null) return false;
        if (code != 0 ? !(code == (this.code)) : this.code != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != 0 ? code : 0);

        return result;
    }

    /**
     * @return the string representation of the object CountryView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CountryView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; name: ");
        builder.append(getName());
        builder.append("; code: ");
        builder.append(getCode());
        builder.append(";");
        builder.append("}");

        return builder.toString();
    }
}

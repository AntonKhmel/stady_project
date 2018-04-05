package ru.bellintegrator.eas.employee.model.view.dependent;

/**
 * @author Хмель А.В.
 * entity CitizenshipView
 */
public class CitizenshipView {
    private int id;
    private String name;
    private int code;

    public CitizenshipView(int id, String name, int code) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    /**
     * constructor for jackson
     */
    public CitizenshipView() {
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
        CitizenshipView citizenshipView = (CitizenshipView) o;
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
     * @return the string representation of the object CitizenshipView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("citizenship:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; name: ");
        builder.append(getName());
        builder.append("; code: ");
        builder.append(getCode());
        builder.append("}; ");

        return builder.toString();
    }
}

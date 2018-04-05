package ru.bellintegrator.eas.organization.model.view.phone;

/**
 * @author Хмель А.В.
 * class PhoneView
 */
public class PhoneView {
    private int id;
    private String number;

    public PhoneView(int id, String number) {
        this.id = id;
        this.number = number;
    }

    /**
     * constructor for jackson
     */
    public PhoneView() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneView phoneView = (PhoneView) o;
        if (id != this.id) return false;
        if (number != null ? !number.equals(this.number) : this.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);

        return result;
    }

    /**
     * @return the string representation of the object PhoneView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PhoneView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; number: ");
        builder.append(getNumber());
        builder.append("; ");
        builder.append("}");

        return builder.toString();
    }
}

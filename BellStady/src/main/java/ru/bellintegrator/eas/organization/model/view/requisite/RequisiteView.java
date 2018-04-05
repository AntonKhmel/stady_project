package ru.bellintegrator.eas.organization.model.view.requisite;

/**
 * @author Хмель А.В.
 * class RequisiteView
 */
public class RequisiteView {
    private int id;
    private String inn;
    private String cpp;

    public RequisiteView(int id, String inn, String cpp) {
        this.id =id;
        this.inn = inn;
        this.cpp = cpp;
    }

    /**
     * constructor for jackson
     */
    public RequisiteView() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        RequisiteView recvisitView = (RequisiteView) o;
        if (id != this.id) return false;
        if (inn != null ? !inn.equals(this.inn) : this.inn != null) return false;
        if (cpp != null ? !cpp.equals(this.cpp) : this.cpp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (inn != null ? inn.hashCode() : 0);
        result = 31 * result + (cpp != null ? cpp.hashCode() : 0);

        return result;
    }

    /**
     * @return the string representation of the object RequisiteView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RequisiteView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; inn: ");
        builder.append(getInn());
        builder.append("; cpp: ");
        builder.append(getCpp());
        builder.append("; ");
        builder.append("}");

        return builder.toString();
    }
}

package ru.bellintegrator.eas.authentication.model.view;

import ru.bellintegrator.eas.employee.model.view.EmployeeView;

/**
 * @author Хмель А.В.
 * entity AuthenticationView
 */
public class AuthenticationView {
    private int id;
    private String login;
    private String password;
    private String name;
    private EmployeeView employeeView;

    public AuthenticationView(int id, String login, String password, String name) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
    }

    /**
     * constructor for jackson
     */
    public AuthenticationView() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeView getEmployeeView() {
        return employeeView;
    }

    public void setEmployeeView(EmployeeView employeeView) {
        this.employeeView = employeeView;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationView authenticationView = (AuthenticationView) o;
        if (id != this.id) return false;
        if (login != null ? !login.equals(this.login) : this.login != null) return false;
        if (password != null ? !password.equals(this.password) : this.password != null) return false;
        if (name != null ? !name.equals(this.name) : this.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

    /**
     * @return the string representation of the object AuthenticationView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Authentication:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; login: ");
        builder.append(getLogin());
        builder.append("; password: ");
        builder.append(getPassword());
        builder.append("; name: ");
        builder.append(getName());
        if (employeeView != null) {
            builder.append(getEmployeeView());
        }
        builder.append("}");

        return builder.toString();
    }
}

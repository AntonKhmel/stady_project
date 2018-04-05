package ru.bellintegrator.eas.authentication.model;

import ru.bellintegrator.eas.employee.model.Employee;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Хмель А.В.
 * entity Authentication
 */
@Entity
@Table(name = "Authentication")
public class Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // the service field of the hibernate for version control
    @Version
    private Integer version;

    @Basic(optional = false)
    @Size(min = 4, max = 20)
    @Column(name = "login")
    private String login;

    @Basic(optional = false)
    @Size(min = 5, max = 25)
    @Column(name = "pass")
    private String password;

    @Basic(optional = false)
    @Size(min = 4, max = 15)
    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Authentication(int id, String login, String password, String name) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
    }

    /**
     * constructor jackson
     */
    public Authentication() {
    }

    // getters and setters methods

    public int getId() {
        return id;
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

    public Employee getEmployee() {
        return employee;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authentication authentication = (Authentication) o;
        if (id != this.id) return false;
        if (version != this.version) return false;
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
        result = 31 * result + version;

        return result;
    }

    /**
     * @return the string representation of the object Authentication
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
        builder.append("}");

        return builder.toString();
    }
}

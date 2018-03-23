package ru.bellintegrator.eas.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.bellintegrator.eas.employee.model.dependent.Citizenship;
import ru.bellintegrator.eas.employee.model.dependent.DocType;
import ru.bellintegrator.eas.employee.model.dependent.Position;
import ru.bellintegrator.eas.office.model.Office;
import ru.bellintegrator.eas.organization.model.phone.Phone;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Хмель А.В.
 * entity Employee
 */
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // the service field of the hibernate for version control
    @Version
    private Integer version;

    @Basic(optional = false)
    @Size(min = 2, max = 25)
    @Column(name = "first_name")
    private String firstName;

    @Basic(optional = false)
    @Size(min = 2, max = 25)
    @Column(name = "second_name")
    private String secondName;

    @Size(min = 2, max = 25)
    @Column(name = "middle_name")
    private String middleName;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private DocType docType;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "citizenship_id")
    private Citizenship citizenship;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "office_id")
    private Office office;

    @Basic(optional = false)
    @Column(name="is_identified")
    private boolean isIdentified;

    public Employee(int id, String firstName, String secondName, String middleName, Position position,
                    DocType docType, Citizenship citizenship, Phone phone, boolean isIdentified) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.docType = docType;
        this.citizenship = citizenship;
        this.phone = phone;
        this.isIdentified = isIdentified;
    }

    /**
     * constructor for the hibernate
     */
    public Employee() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }


    public boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(boolean isIdentified) {
        isIdentified = isIdentified;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        if (id != this.id) return false;
        if (version != this.version) return false;
        if (firstName != null ? !firstName.equals(this.firstName) : this.firstName != null) return false;
        if (secondName != null ? !secondName.equals(this.secondName) : this.secondName != null) return false;
        if (middleName != null ? !middleName.equals(this.middleName) : this.middleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + version;

        return result;
    }

    /**
     * @return the string representation of the object Employee
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Employee:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; first name: ");
        builder.append(getFirstName());
        builder.append("; second name: ");
        builder.append(getSecondName());
        builder.append("; middle name: ");
        builder.append(getMiddleName());
        builder.append("; ");
        if (position != null) {
            builder.append(position);
        }
        if (phone != null) {
            builder.append(phone);
        }
        if (docType != null) {
            builder.append(docType);
        }
        if (citizenship != null) {
            builder.append(citizenship);
        }
        builder.append("; is identified: ");
        builder.append(getIsIdentified());
        builder.append("}");

        return builder.toString();
    }
}

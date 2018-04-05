package ru.bellintegrator.eas.employee.model.view.dependent;

import ru.bellintegrator.eas.employee.model.dependent.DocType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Хмель А.В.
 * entity DocTypeView
 */
public class DocTypeView {
    private int id;
    private int docCode;
    private String docName;
    private int docNumber;
    private Date docDate;

    public DocTypeView(int id, int docCode, String docName, int docNumber, String docDate) {
        this.id = id;
        this.docCode = docCode;
        this.docName = docName;
        this.docNumber = docNumber;
        if (docDate != null) {
            try {
                this.docDate = new SimpleDateFormat("dd.MM.yyyy").parse(docDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * constructor for jackson
     */
    public DocTypeView() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocCode() {
        return docCode;
    }

    public void setDocCode(int docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public int getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(int docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocDate() {
        if (this.docDate != null) {
            String date = new SimpleDateFormat("dd.MM.yyyy").format(this.docDate);

            return date;
        }

        return null;
    }

    public void setDocDate(String docDate) {
        if (docDate != null) {
            try {
                this.docDate = new SimpleDateFormat("dd.MM.yyyy").parse(docDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocTypeView docTypeView = (DocTypeView) o;
        if (id != this.id) return false;
        if (docCode != 0 ? !(docCode == (this.docCode)) : this.docCode != 0) return false;
        if (docName != null ? !docName.equals(this.docName) : this.docName != null) return false;
        if (docNumber != 0 ? !(docNumber == (this.docNumber)) : this.docNumber != 0) return false;
        if (docDate != null ? !docDate.equals(this.docDate) : this.docDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (docCode != 0 ? docCode : 0);
        result = 31 * result + (docName != null ? docName.hashCode() : 0);
        result = 31 * result + (docNumber != 0 ? docNumber : 0);
        result = 31 * result + (docDate != null ? docDate.hashCode() : 0);

        return result;
    }

    /**
     * @return the string representation of the object DocTypeView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DocTypeView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; name: ");
        builder.append(getDocName());
        builder.append("; code: ");
        builder.append(getDocCode());
        builder.append("; number: ");
        builder.append(getDocNumber());
        builder.append("; date: ");
        builder.append(getDocDate());
        builder.append("}; ");

        return builder.toString();
    }
}

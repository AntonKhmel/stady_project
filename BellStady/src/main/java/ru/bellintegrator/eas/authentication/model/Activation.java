package ru.bellintegrator.eas.authentication.model;

import java.util.UUID;

/**
 * @author Хмель А.В.
 * class Activation
 */
public class Activation {
    private final String value;

    public Activation() {
        this.value = UUID.randomUUID().toString();
    }

    // getter method

    public String getValue() {
        return value;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activation activation = (Activation) o;
        if (value != null ? !value.equals(this.value) : this.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 + (value != null ? value.hashCode() : 0);

        return result;
    }

    /**
     * @return the string representation of the object Activation
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Authentication:");
        builder.append("{value: ");
        builder.append(getValue());
        builder.append("}");

        return builder.toString();
    }
}

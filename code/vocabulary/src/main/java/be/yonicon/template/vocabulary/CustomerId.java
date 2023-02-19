package be.yonicon.template.vocabulary;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class CustomerId {
    private final String uuid;

    protected CustomerId(String value) {
        this.uuid = UUID.fromString(requireNonNull(value)).toString();
    }

    public static CustomerId newId() {
        return new CustomerId(UUID.randomUUID().toString());
    }

    public static CustomerId from(String value) {
        return new CustomerId(value);
    }

    public String toString() {
        return uuid;
    }

    public String getId() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerId that = (CustomerId) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}

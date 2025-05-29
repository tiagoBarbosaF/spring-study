package tiago.module2apis.model;

import java.time.Instant;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private final String name;
    private final String email;
    private final Integer age;
    private final Instant createdAt;

    private Customer(Builder builder) {
        id = builder.id;
        name = builder.name;
        email = builder.email;
        age = builder.age;
        createdAt = builder.createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private String email;
        private Integer age;
        private Instant createdAt;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder age(Integer val) {
            age = val;
            return this;
        }

        public Builder createdAt(Instant val) {
            createdAt = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}

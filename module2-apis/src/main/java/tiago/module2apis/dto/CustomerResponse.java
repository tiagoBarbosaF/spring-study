package tiago.module2apis.dto;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String name,
        String email,
        Integer age,
        Instant createdAt
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private String email;
        private Integer age;
        private Instant createdAt;

        public Builder() {
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

        public CustomerResponse build() {
            return new CustomerResponse(
                    this.id,
                    this.name,
                    this.email,
                    this.age,
                    this.createdAt
            );
        }
    }
}

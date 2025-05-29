package tiago.module2apis.dto;

import jakarta.validation.constraints.*;

public record CustomerRequest(
        @NotNull @Size(min = 3, max = 50) String name,
        @NotNull @Email String email,
        @Positive @Min(value = 18, message = "Customer needs more 18 years old.")
        Integer age
) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String email;
        private Integer age;

        public Builder() {
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

        public CustomerRequest build() {
            return new CustomerRequest(
                    this.name,
                    this.email,
                    this.age
            );
        }
    }
}

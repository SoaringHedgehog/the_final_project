package entity;

public class User {
    private String name;
    private String password;
    private String email;

    private User(Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String password;
        private String email;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            if (name == null || name.isEmpty()) {
                throw new IllegalStateException("Имя не может быть пустым");
            }
            if (password == null || password.isEmpty()) {
                throw new IllegalStateException("Пароль не может быть пустым");
            }
            if (email == null || email.isEmpty()) {
                throw new IllegalStateException("Email не может быть пустым");
            }
            // можно добавить валидацию email через regex
            return new User(this);
        }
    }
}

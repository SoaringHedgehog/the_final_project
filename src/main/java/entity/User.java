package entity;

public class User implements Comparable<User> {
    private final String name;
    private final String password;
    private final String email;

    private User(Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    public String getName() { return name; }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    @Override
    public int compareTo(User other) {
        int result = this.name.compareTo(other.name);
        if (result != 0) return result;

        result = this.password.compareTo(other.password);
        if (result != 0) return result;

        return this.email.compareTo(other.email);
    }

    public static class Builder {
        private String name;
        private String password;
        private String email;

        public Builder setName(String name) {
            if (name == null || name.isEmpty())
                throw new IllegalArgumentException("Введите имя");
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            if (password == null || password.isEmpty())
                throw new IllegalArgumentException("Введите пароль");
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            if (email == null || email.isEmpty())
                throw new IllegalArgumentException("Введите почту");
            this.email = email;
            return this;
        }

        public User build() {
            if (name == null || password == null || email == null) {
                throw new IllegalStateException("Необходимо указать имя, пароль и почту");
            }
            return new User(this);
        }
    }
}


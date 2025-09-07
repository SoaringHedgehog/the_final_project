package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void builderCreatesUserCorrectly() {
        User user = new User.Builder()
                .setName("Olesya")
                .setPassword("pass123")
                .setEmail("olesya@gmail.com")
                .build();

        assertEquals("Olesya", user.getName());
        assertEquals("pass123", user.getPassword());
        assertEquals("olesya@gmail.com", user.getEmail());
    }

    @Test
    void builderThrowsExceptionOnNullName() {
        User.Builder builder = new User.Builder()
                .setPassword("pass123")
                .setEmail("olesya@gmail.com");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setName(null);
        });
        assertEquals("Введите имя", exception.getMessage());
    }

    @Test
    void builderThrowsExceptionOnEmptyEmail() {
        User.Builder builder = new User.Builder()
                .setName("Olesya")
                .setPassword("pass123");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setEmail("");
        });
        assertEquals("Введите почту", exception.getMessage());
    }

    @Test
    void compareToWorksCorrectly() {
        User u1 = new User.Builder().setName("Olesya").setPassword("123").setEmail("q@gmail.com").build();
        User u2 = new User.Builder().setName("Olesya").setPassword("123").setEmail("w@gmail.com").build();
        User u3 = new User.Builder().setName("Tim").setPassword("123").setEmail("q@gmail.com").build();

        assertTrue(u1.compareTo(u2) < 0);  // email сравнивается
        assertTrue(u2.compareTo(u1) > 0);
        assertTrue(u1.compareTo(u3) < 0);  // name сравнивается
    }

}

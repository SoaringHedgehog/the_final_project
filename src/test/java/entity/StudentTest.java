package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void builderCreatesStudentCorrectly() {
        Student student = new Student.Builder()
                .setGroupNumber(101)
                .setAverageScore(4.5)
                .setGradeBookNumber(12345)
                .build();

        assertEquals(101, student.getGroupNumber());
        assertEquals(4.5, student.getAverageScore());
        assertEquals(12345, student.getGradeBookNumber());
    }

    @Test
    void builderThrowsExceptionOnNegativeGroupNumber() {
        Student.Builder builder = new Student.Builder()
                .setAverageScore(4.0)
                .setGradeBookNumber(123);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setGroupNumber(0);
        });
        assertEquals("Номер группы должен быть положительным", exception.getMessage());
    }

    @Test
    void builderThrowsExceptionOnInvalidAverageScore() {
        Student.Builder builder = new Student.Builder()
                .setGroupNumber(101)
                .setGradeBookNumber(123);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setAverageScore(6.0);
        });
        assertEquals("Средний балл должен быть в диапазоне от 0 до 5", exception.getMessage());
    }

    @Test
    void builderThrowsExceptionOnNegativeGradeBookNumber() {
        Student.Builder builder = new Student.Builder()
                .setGroupNumber(101)
                .setAverageScore(4.0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setGradeBookNumber(0);
        });
        assertEquals("Номер зачетной книжки должен быть положительным", exception.getMessage());
    }

    @Test
    void compareToWorksCorrectly() {
        Student s1 = new Student.Builder()
                .setGroupNumber(101)
                .setAverageScore(4.0)
                .setGradeBookNumber(123)
                .build();

        Student s2 = new Student.Builder()
                .setGroupNumber(101)
                .setAverageScore(4.5)
                .setGradeBookNumber(123)
                .build();

        Student s3 = new Student.Builder()
                .setGroupNumber(102)
                .setAverageScore(3.5)
                .setGradeBookNumber(124)
                .build();

        assertTrue(s1.compareTo(s2) < 0);  // сравнивается averageScore
        assertTrue(s2.compareTo(s1) > 0);
        assertTrue(s1.compareTo(s3) < 0);  // сравнивается groupNumber
    }
}

package input;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import entity.Bus;
import entity.User;
import entity.Student;

public class RandomInput {
    private static Random random = new Random();

    public static List<Bus> generateBuses(int n) {
        List<Bus> buses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Bus bus = new Bus.Builder()
                .setNumber("BUS" + (100 + i))
                .setModel("Model" + (char)('A' + random.nextInt(5)))
                .setMileage(10000 + random.nextInt(100000))
                .build();
            buses.add(bus);
        }
        return buses;
    }

    public static List<User> generateUsers(int n) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            User user = new User.Builder()
                .setName("User" + i)
                .setPassword("Pass" + random.nextInt(10000))
                .setEmail("user" + i + "@mail.com")
                .build();
            users.add(user);
        }
        return users;
    }

    public static List<Student> generateStudents(int n) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Student student = new Student.Builder()
                .setGroupNumber(100 + i)
                .setAverageScore(Math.round((2.0 + random.nextDouble() * 3) * 100.0) / 100.0)
                .setRecordBookNumber("RB" + (1000 + i))
                .build();
            students.add(student);
        }
        return students;
    }
}

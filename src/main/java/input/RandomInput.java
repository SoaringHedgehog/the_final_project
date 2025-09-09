package input;

import java.util.ArrayList;
import java.util.Random;

import entity.Bus;
import entity.User;
import entity.Student;

public class RandomInput {
    private static Random random = new Random();

    public static ArrayList<Comparable> generateBuses(int n) {
        ArrayList<Comparable> buses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Bus bus = new Bus.Builder()
                .setNumber(random.nextInt(100))
                .setModel("Model" + (char)('A' + random.nextInt(5)))
                .setMileage(10000 + random.nextInt(100000))
                .build();
            buses.add(bus);
        }
        return buses;
    }

    public static ArrayList<Comparable> generateUsers(int n) {
        ArrayList<Comparable> users = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            User user = new User.Builder()
                .setName("User" + random.nextInt())
                .setPassword("Pass" + random.nextInt(10000))
                .setEmail("user" + i + "@mail.com")
                .build();
            users.add(user);
        }
        return users;
    }

    public static ArrayList<Comparable> generateStudents(int n) {
        ArrayList<Comparable> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Student student = new Student.Builder()
                .setGroupNumber(100 + random.nextInt(100))
                .setAverageScore(Math.round((2.0 + random.nextDouble() * 3) * 100.0) / 100.0)
                .setGradeBookNumber(random.nextInt(1000))
                .build();
            students.add(student);
        }
        return students;
    }
}

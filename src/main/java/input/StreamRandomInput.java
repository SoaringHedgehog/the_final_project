package input;

import entity.Bus;
import entity.Student;
import entity.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamRandomInput {
    private static Random random = new Random();

    public static ArrayList<Comparable> generateBuses(int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> generateBus())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Comparable> generateUsers(int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> generateUser(i))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Comparable> generateStudents(int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> generateStudent())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Bus generateBus() {
        return new Bus.Builder()
                .setNumber(random.nextInt(100))
                .setModel("Model" + (char)('A' + random.nextInt(5)))
                .setMileage(10000 + random.nextInt(100000))
                .build();
    }

    private static User generateUser(int index) {
        return new User.Builder()
                .setName("User" + random.nextInt(1000))
                .setPassword("Pass" + random.nextInt(10000))
                .setEmail("user" + index + "@mail.com")
                .build();
    }

    private static Student generateStudent() {
        return new Student.Builder()
                .setGroupNumber(100 + random.nextInt(100))
                .setAverageScore(Math.round((2.0 + random.nextDouble() * 3) * 100.0) / 100.0)
                .setGradeBookNumber(1000 + random.nextInt(9000))
                .build();
    }
}

package input;

import entity.Bus;
import entity.Student;
import entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamInputFromFile {

    public static ArrayList<Comparable> readBusesFromInputStream(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            return Stream.generate(() -> {
                        try {
                            return reader.readLine();
                        } catch (IOException e) {
                            return null;
                        }
                    })
                    .takeWhile(line -> line != null)
                    .map(StreamInputFromFile::parseBusFromLine)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // возвращаем пустой ArrayList
        }
    }

    public static ArrayList<Comparable> readUsersFromInputStream(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            return Stream.generate(() -> {
                        try {
                            return reader.readLine();
                        } catch (IOException e) {
                            return null;
                        }
                    })
                    .takeWhile(line -> line != null)
                    .map(StreamInputFromFile::parseUserFromLine)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static ArrayList<Comparable> readStudentsFromInputStream(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            return Stream.generate(() -> {
                        try {
                            return reader.readLine();
                        } catch (IOException e) {
                            return null;
                        }
                    })
                    .takeWhile(line -> line != null)
                    .map(StreamInputFromFile::parseStudentFromLine)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static Bus parseBusFromLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(",");
        if (parts.length != 3) {
            return null;
        }
        try {
            int number = Integer.parseInt(parts[0].trim());
            String model = parts[1].trim();
            int mileage = Integer.parseInt(parts[2].trim());
            return new Bus.Builder()
                    .setNumber(number)
                    .setModel(model)
                    .setMileage(mileage)
                    .build();
        } catch (Exception e) {
            return null; // пропускаем ошибочные строки
        }
    }

    private static User parseUserFromLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(",");
        if (parts.length != 3) {
            return null;
        }

        try {
            String name = parts[0].trim();
            String password = parts[1].trim();
            String email = parts[2].trim();

            return new User.Builder()
                    .setName(name)
                    .setPassword(password)
                    .setEmail(email)
                    .build();
        } catch (Exception e) {
            return null; // Пропускаем строки с ошибками валидации
        }
    }

    private static Student parseStudentFromLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(",");
        if (parts.length != 3) {
            return null;
        }

        try {
            int groupNumber = Integer.parseInt(parts[0].trim());
            double averageScore = Double.parseDouble(parts[1].trim());
            int gradeBookNumber = Integer.parseInt(parts[2].trim());

            return new Student.Builder()
                    .setGroupNumber(groupNumber)
                    .setAverageScore(averageScore)
                    .setGradeBookNumber(gradeBookNumber)
                    .build();
        } catch (Exception e) {
            return null; // пропускаем ошибочные строки
        }
    }
}

package input;

import entity.Bus;
import entity.User;
import entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InputFromFile {

    public static ArrayList<Comparable> readBusesFromInputStream(InputStream is) {
        ArrayList<Comparable> buses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) continue;
                try {
                    int number = Integer.parseInt(parts[0].trim());
                    String model = parts[1].trim();
                    int mileage = Integer.parseInt(parts[2].trim());
                    Bus bus = new Bus.Builder()
                            .setNumber(number)
                            .setModel(model)
                            .setMileage(mileage)
                            .build();
                    buses.add(bus);
                } catch (Exception e) {
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buses;
    }

    public static ArrayList<Comparable> readUsersFromInputStream(InputStream is) {
        ArrayList<Comparable> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) continue;
                try {
                    String name = parts[0].trim();
                    String password = parts[1].trim();
                    String email = parts[2].trim();
                    User user = new User.Builder()
                            .setName(name)
                            .setPassword(password)
                            .setEmail(email)
                            .build();
                    users.add(user);
                } catch (Exception e) {
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<Comparable> readStudentsFromInputStream(InputStream is) {
        ArrayList<Comparable> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) continue;
                try {
                    int groupNumber = Integer.parseInt(parts[0].trim());
                    double averageScore = Double.parseDouble(parts[1].trim());
                    int gradeBookNumber = Integer.parseInt(parts[2].trim()); 
                    Student student = new Student.Builder()
                            .setGroupNumber(groupNumber)
                            .setAverageScore(averageScore)
                            .setGradeBookNumber(gradeBookNumber)
                            .build();
                    students.add(student);
                } catch (Exception e) {
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}

package output;

import entity.Bus;
import entity.User;
import entity.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterUtil {

    public static void appendBusesToFile(List buses, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Object obj : buses) {
                Bus bus = (Bus) obj;
                writer.write(bus.getNumber() + "," + bus.getModel() + "," + bus.getMileage());
                writer.newLine();
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи автобусов в файл: " + e.getMessage());
        }
    }

    public static void appendUsersToFile(List users, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Object obj : users) {
                User user = (User) obj;
                writer.write(user.getName() + "," + user.getPassword() + "," + user.getEmail());
                writer.newLine();
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи пользователей в файл: " + e.getMessage());
        }
    }

    public static void appendStudentsToFile(List students, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Object obj : students) {
                Student student = (Student) obj;
                writer.write(student.getGroupNumber() + "," + student.getAverageScore() + "," + student.getGradeBookNumber());
                writer.newLine();
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи студентов в файл: " + e.getMessage());
        }
    }

    public static void appendNumberToFile(int number, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(Integer.toString(number));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи числа в файл: " + e.getMessage());
        }
    }

    public static void clearFile(String fileName) {
        try (FileWriter fw = new FileWriter(fileName, false)) { 

        } catch (IOException e) {
            System.err.println("Ошибка очистки файла: " + e.getMessage());
        }
    }
}

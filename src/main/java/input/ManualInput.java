package input;

import entity.Bus;
import entity.Student;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualInput {

    public static List<Bus> inputBuses(int n, Scanner scanner) {
        List<Bus> buses = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String number, model;
            int mileage;

            System.out.print("Введите номер автобуса: ");
            number = scanner.next();

            System.out.print("Введите модель: ");
            model = scanner.next();

            while (true) {
                System.out.print("Введите пробег (целое число): ");
                if (scanner.hasNextInt()) {
                    mileage = scanner.nextInt();
                    scanner.nextLine(); // очистка буфера
                    break;
                } else {
                    System.out.println("Ошибка! Введите целое число.");
                    scanner.nextLine();
                }
            }

            if (InputFromFile.validateBus(number, model, String.valueOf(mileage))) {
                buses.add(new Bus.Builder().setNumber(number).setModel(model).setMileage(mileage).build());
            } else {
                System.out.println("Неверные данные, попробуйте снова.");
                i--;
            }
        }
        return buses;
    }

    public static List<User> inputUsers(int n, Scanner scanner) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name, email, password;

            System.out.print("Введите имя пользователя: ");
            name = scanner.next();

            System.out.print("Введите email: ");
            email = scanner.next();

            System.out.print("Введите пароль: ");
            password = scanner.next();

            if (InputFromFile.validateUser(name, email, password)) {
                users.add(new User.Builder()
                    .setName(name)
                    .setEmail(email)
                    .setPassword(password)
                    .build());
            } else {
                System.out.println("Неверные данные пользователя, попробуйте снова.");
                i--;
            }
        }
        return users;
    }

    public static List<Student> inputStudents(int n, Scanner scanner) {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String groupNumber;
            double averageScore;
            String recordBookNumber;

            System.out.print("Введите номер группы: ");
            groupNumber = scanner.next();

            while (true) {
                System.out.print("Введите средний балл (число от 0 до 5): ");
                if (scanner.hasNextDouble()) {
                    averageScore = scanner.nextDouble();
                    scanner.nextLine(); // очистка буфера
                    break;
                } else {
                    System.out.println("Ошибка! Введите число.");
                    scanner.nextLine();
                }
            }

            System.out.print("Введите номер зачетной книжки: ");
            recordBookNumber = scanner.next();

            if (InputFromFile.validateStudent(groupNumber, averageScore, recordBookNumber)) {
                students.add(new Student.Builder()
                        .setGroupNumber(groupNumber)
                        .setAverageScore(averageScore)
                        .setRecordBookNumber(recordBookNumber)
                        .build());
            } else {
                System.out.println("Неверные данные студента, попробуйте снова.");
                i--;
            }
        }
        return students;
    }
}

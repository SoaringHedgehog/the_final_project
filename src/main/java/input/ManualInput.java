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
        for (int i = 0; i < n; ) {
            try {
                System.out.print("Введите номер автобуса: ");
                String number = scanner.nextLine().trim();

                System.out.print("Введите модель: ");
                String model = scanner.nextLine().trim();

                System.out.print("Введите пробег (целое число): ");
                int mileage;
                if (scanner.hasNextInt()) {
                    mileage = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Ошибка! Пробег должен быть целым числом.");
                    scanner.nextLine();
                    continue;
                }

                Bus bus = new Bus.Builder()
                        .setNumber(number)
                        .setModel(model)
                        .setMileage(mileage)
                        .build();
                buses.add(bus);
                i++;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Неверные данные: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
        return buses;
    }

    public static List<User> inputUsers(int n, Scanner scanner) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < n; ) {
            try {
                System.out.print("Введите имя пользователя: ");
                String name = scanner.nextLine().trim();

                System.out.print("Введите email: ");
                String email = scanner.nextLine().trim();

                System.out.print("Введите пароль: ");
                String password = scanner.nextLine().trim();

                User user = new User.Builder()
                        .setName(name)
                        .setEmail(email)
                        .setPassword(password)
                        .build();
                users.add(user);
                i++;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Неверные данные пользователя: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
        return users;
    }

    public static List<Student> inputStudents(int n, Scanner scanner) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; ) {
            try {
                System.out.print("Введите номер группы: ");
                int groupNumber;
                if (scanner.hasNextInt()) {
                    groupNumber = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Ошибка! Введите целое число.");
                    scanner.nextLine();
                    continue;
                }

                System.out.print("Введите средний балл (число от 0 до 5): ");
                double averageScore;
                if (scanner.hasNextDouble()) {
                    averageScore = scanner.nextDouble();
                    scanner.nextLine();
                } else {
                    System.out.println("Ошибка! Введите число.");
                    scanner.nextLine();
                    continue;
                }

                System.out.print("Введите номер зачетной книжки: ");
                String recordBookNumber = scanner.nextLine().trim();

                Student student = new Student.Builder()
                        .setGroupNumber(groupNumber)
                        .setAverageScore(averageScore)
                        .setRecordBookNumber(recordBookNumber)
                        .build();
                students.add(student);
                i++;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Неверные данные студента: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
        return students;
    }
        public static int readIntWithPrompt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Ошибка! Введите целое число.");
                scanner.nextLine();
            }
        }
        return value;
    }
}

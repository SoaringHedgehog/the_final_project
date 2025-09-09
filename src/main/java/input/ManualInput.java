package input;

import entity.Bus;
import entity.User;
import entity.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class ManualInput {

    public static ArrayList<Comparable> inputBuses(int n, Scanner scanner) {
        ArrayList<Comparable> buses = new ArrayList<>();
        for (int i = 0; i < n; ) {
            try {
                System.out.print("Введите номер автобуса: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Ошибка! Номер автобуса должен быть целым числом.");
                    scanner.nextLine();
                    continue;
                }
                int number = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Введите модель: ");
                String model = scanner.nextLine().trim();

                System.out.print("Введите пробег (целое число): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Ошибка! Пробег должен быть целым числом.");
                    scanner.nextLine();
                    continue;
                }
                int mileage = scanner.nextInt();
                scanner.nextLine();

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

    public static ArrayList<Comparable> inputUsers(int n, Scanner scanner) {
        ArrayList<Comparable> users = new ArrayList<>();
        for (int i = 0; i < n; ) {
            try {
                System.out.print("Введите имя пользователя: ");
                String name = scanner.nextLine().trim();

                System.out.print("Введите пароль: ");
                String password = scanner.nextLine().trim();

                System.out.print("Введите email: ");
                String email = scanner.nextLine().trim();

                User user = new User.Builder()
                        .setName(name)
                        .setPassword(password)
                        .setEmail(email)
                        .build();
                users.add(user);
                i++;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Неверные данные пользователя: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
        return users;
    }

    public static ArrayList<Comparable> inputStudents(int n, Scanner scanner) {
        ArrayList<Comparable> students = new ArrayList<>();
        for (int i = 0; i < n; ) {
            try {
                System.out.print("Введите номер группы: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Ошибка! Введите целое число.");
                    scanner.nextLine();
                    continue;
                }
                int groupNumber = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Введите средний балл (число от 0 до 5): ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Ошибка! Введите число.");
                    scanner.nextLine();
                    continue;
                }
                double averageScore = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Введите номер зачетной книжки: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Ошибка! Номер зачетной книжки должен быть целым числом.");
                    scanner.nextLine();
                    continue;
                }
                int gradeBookNumber = scanner.nextInt();
                scanner.nextLine();

                Student student = new Student.Builder()
                        .setGroupNumber(groupNumber)
                        .setAverageScore(averageScore)
                        .setGradeBookNumber(gradeBookNumber)
                        .build();
                students.add(student);
                i++;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Неверные данные студента: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
        return students;
    }
}

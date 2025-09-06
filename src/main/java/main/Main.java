package main;

import input.ManualInput;
import input.RandomInput;
import input.InputFromFile;  // импорт методов загрузки из файлов
import entity.Bus;
import entity.Student;
import entity.User;
import java.util.List;
import java.util.Scanner;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Выберите тип данных для работы:");
            System.out.println("1 - Автобусы");
            System.out.println("2 - Пользователи");
            System.out.println("3 - Студенты");
            System.out.println("0 - Выход");

            int type = ManualInput.readIntWithPrompt(scanner, "Введите выбор: ");

            if (type == 0) {
                exit = true;
                System.out.println("Выход из программы.");
                break;
            }

            System.out.println("Выберите способ заполнения:");
            System.out.println("1 - Ручной ввод");
            System.out.println("2 - Генерация данных");
            System.out.println("3 - Загрузка из файла ресурсов");

            int inputMethod = ManualInput.readIntWithPrompt(scanner, "Введите выбор способа: ");

            switch (type) {
                case 1 -> {
                    List<Bus> buses;
                    int n = ManualInput.readIntWithPrompt(scanner, "Введите количество элементов: ");
                    if (inputMethod == 1) {
                        buses = ManualInput.inputBuses(n, scanner);
                    } else if (inputMethod == 2) {
                        buses = RandomInput.generateBuses(n);
                    } else if (inputMethod == 3) {
                        // Загрузка из файла
                        InputStream is = Main.class.getClassLoader().getResourceAsStream("buses.txt");
                        if (is == null) {
                            System.out.println("Файл не найден в resources.");
                            continue;
                        }
                        buses = InputFromFile.readBusesFromInputStream(is);
                    } else {
                        System.out.println("Неверный выбор способа заполнения.");
                        continue;
                    }
                    System.out.println("Результат:");
                    buses.forEach(bus -> System.out.printf("Номер: %s, Модель: %s, Пробег: %d%n",
                            bus.getNumber(), bus.getModel(), bus.getMileage()));
                }
                case 2 -> {
                    List<User> users;
                    int n = ManualInput.readIntWithPrompt(scanner, "Введите количество элементов: ");
                    if (inputMethod == 1) {
                        users = ManualInput.inputUsers(n, scanner);
                    } else if (inputMethod == 2) {
                        users = RandomInput.generateUsers(n);
                    } else if (inputMethod == 3) {
                        InputStream is = Main.class.getClassLoader().getResourceAsStream("users.txt");
                        if (is == null) {
                            System.out.println("Файл не найден в resources.");
                            continue;
                        }
                        users = InputFromFile.readUsersFromInputStream(is);
                    } else {
                        System.out.println("Неверный выбор способа заполнения.");
                        continue;
                    }
                    System.out.println("Результат:");
                    users.forEach(user -> System.out.printf("Имя: %s, Email: %s, Пароль: %s%n",
                            user.getName(), user.getEmail(), user.getPassword()));
                }
                case 3 -> {
                    List<Student> students;
                    int n = ManualInput.readIntWithPrompt(scanner, "Введите количество элементов: ");
                    if (inputMethod == 1) {
                        students = ManualInput.inputStudents(n, scanner);
                    } else if (inputMethod == 2) {
                        students = RandomInput.generateStudents(n);
                    } else if (inputMethod == 3) {
                        InputStream is = Main.class.getClassLoader().getResourceAsStream("students.txt");
                        if (is == null) {
                            System.out.println("Файл не найден в resources.");
                            continue;
                        }
                        students = InputFromFile.readStudentsFromInputStream(is);
                    } else {
                        System.out.println("Неверный выбор способа заполнения.");
                        continue;
                    }
                    System.out.println("Результат:");
                    students.forEach(student -> System.out.printf("Группа: %d, Средний балл: %.2f, Зачетка: %s%n",
                            student.getGroupNumber(), student.getAverageScore(), student.getGradeBookNumber()));
                }
                default -> System.out.println("Неверный выбор типа данных.");
            }
            System.out.println();
        }    
        scanner.close();
    }
}

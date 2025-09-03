package main;

import input.ManualInput;
import input.RandomInput;
import entity.Bus;
import entity.Student;
import entity.User;
import java.util.List;
import java.util.Scanner;

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
            int type = scanner.nextInt();
            scanner.nextLine();

            if (type == 0) {
                exit = true;
                System.out.println("Выход из программы.");
                break;
            }

            System.out.print("Введите количество элементов: ");
            int n = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Выберите способ заполнения:");
            System.out.println("1 - Ручной ввод");
            System.out.println("2 - Генерация данных");
            int inputMethod = scanner.nextInt();
            scanner.nextLine();

            switch (type) {
                case 1 -> { // Автобусы
                    List<Bus> buses = (inputMethod == 1)
                            ? ManualInput.inputBuses(n, scanner)
                            : RandomInput.generateBuses(n);
                    System.out.println("Результат:");
                    buses.forEach(bus -> System.out.printf("Номер: %s, Модель: %s, Пробег: %d%n",
                            bus.getNumber(), bus.getModel(), bus.getMileage()));
                }
                case 2 -> { // Пользователи
                    List<User> users = (inputMethod == 1)
                            ? ManualInput.inputUsers(n, scanner)
                            : RandomInput.generateUsers(n);
                    System.out.println("Результат:");
                    users.forEach(user -> System.out.printf("Имя: %s, Email: %s, Пароль: %s%n",
                            user.getName(), user.getEmail(), user.getPassword()));
                }
                case 3 -> { // Студенты
                    List<Student> students = (inputMethod == 1)
                            ? ManualInput.inputStudents(n, scanner)
                            : RandomInput.generateStudents(n);
                    System.out.println("Результат:");
                    students.forEach(student -> System.out.printf("Группа: %s, Средний балл: %.2f, Зачетка: %s%n",
                            student.getGroupNumber(), student.getAverageScore(), student.getRecordBookNumber()));
                }
                default -> System.out.println("Неверный выбор типа данных.");
            }
            System.out.println(); // пустая строка перед новым циклом
        }

        scanner.close();
    }
}

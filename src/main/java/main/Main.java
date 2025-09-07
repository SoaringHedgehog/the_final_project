package main;

import entity.Bus;
import entity.Student;
import entity.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Выберите тип объектов:\n" +
                    "0) Закончить работу с программой\n" +
                    "1) Автобус(класс Bus)\n" +
                    "2) Студент(класс Student)\n" +
                    "3) Пользователь(класс User)\n");
            String answer = scanner.nextLine();
            switch (answer){
                case "0": System.exit(1);
                case "1":
                    workWithObject(new Bus()); break;
                case "2":
                    workWithObject(new Student()); break;
                case "3":
                    workWithObject(new User()); break;
                default:
                    System.out.println("Введен неверный тип"); break;
            }
        }
    }
    static void workWithObject(Object object) throws Exception {
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Работа с классом: " + object.getClass().getSimpleName());
            System.out.println("Выберите способ заполнения списка объектов:\n" +
                    "0) Вернуться к выбору класса\n" +
                    "1) Из файла\n" +
                    "2) Вручную\n" +
                    "3) Заполнить случайными значениями");
            String answer = scanner.nextLine();
            ArrayList<Object> objects = new ArrayList<>();
            switch (answer){
                case "0": return;
                case "1":
                    System.out.println("Заполнение из файла:");
                    //Затычка для метода ввода из файла
                    break;
                case "2":
                    System.out.println("Заполнение вручную:");
                    //Затычка для метода ввода вручную
                    break;
                case "3":
                    System.out.println("Заполнение случайными значениями:");
                    //Затычка для метода рандомного ввода
                    break;
                default:
                    System.out.println("Введен неверный ответ"); break;
            }
            System.out.println("Готовый список объектов (не сортированный):");
            Field[] fields = object.getClass().getDeclaredFields();
            for(Field field : fields){
                System.out.printf("%-30s", field.getName());
            }
            System.out.println();
            viewArrayObject(objects);
        }
    }

    private static void viewArrayObject(ArrayList<Object> objects) throws Exception {
        switch (objects.get(0).getClass().getSimpleName()){
            case "Bus": viewArrayBus(objects); break;
            case "Student": viewArrayStudent(objects); break;
            case "User": viewArrayUser(objects); break;
            default: throw new Exception("Получен неподдерживаемый тип файлов");
        }
    }

    private static void viewArrayBus(ArrayList<Object> objects) {
        System.out.println("*вывод массива автобусов*");
    }

    private static void viewArrayStudent(ArrayList<Object> objects) {
        System.out.println("*вывод массива студентов*");
    }

    private static void viewArrayUser(ArrayList<Object> objects) {
        System.out.println("*вывод массива пользователей*");
    }
}
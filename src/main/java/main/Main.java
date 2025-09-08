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
                    workWithObject("Bus"); break;
                case "2":
                    workWithObject("Student"); break;
                case "3":
                    workWithObject("User"); break;
                default:
                    System.out.println("Введен неверный тип"); break;
            }
        }
    }
    static void workWithObject(String className) throws Exception {
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Работа с классом: " + className);
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
                    switch (className){
                        case "Bus": /*Затычка для метода ввода из файла*/ break;
                        case "Student": /*Затычка для метода ввода из файла*/ break;
                        case "User": /*Затычка для метода ввода из файла*/ break;
                    }
                    break;
                case "2":
                    System.out.println("Заполнение вручную:");
                    switch (className){
                        case "Bus": /*Затычка для метода ввода вручную*/ break;
                        case "Student": /*Затычка для метода ввода вручную*/ break;
                        case "User": /*Затычка для метода ввода вручную*/ break;
                    }
                    break;
                case "3":
                    System.out.println("Заполнение случайными значениями:");
                    switch (className){
                        case "Bus": /*Затычка для метода рандомного ввода*/ break;
                        case "Student": /*Затычка для метода рандомного ввода*/ break;
                        case "User": /*Затычка для метода рандомного ввода*/ break;
                    }
                    break;
                default:
                    System.out.println("Введен неверный ответ"); break;
            }
            System.out.println("Готовый список объектов (не сортированный):");
            Field[] fields = objects.getFirst().getClass().getDeclaredFields();
            for(Field field : fields){
                System.out.printf("%-30s", field.getName());
            }
            System.out.println();
            viewArrayObject(objects);
            viewBinarySearch(objects);
        }
    }

    private static void viewBinarySearch(ArrayList<Object> objects) {
        System.out.println("Для поиска индекса объекта требуются значения его полей");
        Field[] fields = objects.get(0).getClass().getDeclaredFields();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение поля " + fields[0]);
        Object firstfield = scanner.nextLine();
        System.out.println("Введите значение поля " + fields[1]);
        Object secondfield = scanner.nextLine();
        System.out.println("Введите значение поля " + fields[2]);
        Object thirdfield = scanner.nextLine();
        // Затычка для создания объекта-примера
        int indexOfRequiredObject = 1;
        // Затычка для бинарного поиска (бинарный поиск в другой ветке, Заапдейчу после слияния)

        if(indexOfRequiredObject < 0){System.out.println("Подобный объект не найден");}
            else {System.out.println("Искомый объект находится на " +  indexOfRequiredObject + 1 + " месте в отсортированном списке");}

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

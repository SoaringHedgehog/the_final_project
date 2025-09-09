package main;

import algorithm.BinarySearch;
import algorithm.CustomSort;
import algorithm.SelectionSort;
import algorithm.ThreadCounter;
import entity.Bus;
import entity.Student;
import entity.User;
import input.*;

import input.StreamRandomInput;
import output.FileWriterUtil;
import thread.ParallelSorter;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
            ArrayList<Comparable> objects = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Работа с классом: " + className);
            System.out.println("Выберите способ заполнения списка объектов:\n" +
                    "0) Вернуться к выбору класса\n" +
                    "1) Из файла\n" +
                    "2) Вручную\n" +
                    "3) Заполнить случайными значениями");
            String answer = scanner.nextLine();
            switch (answer){
                case "0": return;
                case "1":
                    System.out.println("Заполнение из файла:");
                    try {
                        switch (className){
                            case "Bus": objects = StreamInputFromFile.readBusesFromInputStream(new FileInputStream("src/main/resources/buses.txt")); break;
                            case "Student": objects = StreamInputFromFile.readStudentsFromInputStream(new FileInputStream("src/main/resources/students.txt")); break;
                            case "User": objects = StreamInputFromFile.readUsersFromInputStream(new FileInputStream("src/main/resources/users.txt")); break;
                        }
                    }
                    catch (IOException e){
                        System.out.println("Неправильный путь файла");
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Заполнение вручную:\n" +
                            "Введите количество объектов: ");
                    int countManualObjects = Integer.parseInt(scanner.nextLine());
                    switch (className){
                        case "Bus": objects = StreamManualInput.inputBuses(countManualObjects, scanner); break;
                        case "Student": objects = StreamManualInput.inputStudents(countManualObjects, scanner); break;
                        case "User": objects = StreamManualInput.inputUsers(countManualObjects, scanner); break;
                    }
                    break;
                case "3":
                    System.out.println("Заполнение случайными значениями:\n" +
                            "Введите количество объектов: ");
                    int countRandObjects = Integer.parseInt(scanner.nextLine());
                    switch (className){
                        case "Bus": objects = StreamRandomInput.generateBuses(countRandObjects); break;
                        case "Student": objects = StreamRandomInput.generateStudents(countRandObjects); break;
                        case "User": objects = StreamRandomInput.generateUsers(countRandObjects); break;
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
            ArrayList<Comparable> sortedObjects = null;
            switch (objects.getFirst().getClass().getSimpleName()){
                case "Bus":
                    ParallelSorter<Bus> busSort = new ParallelSorter<>(4);
                    busSort.sort(objects);
                    sortedObjects = new ArrayList<>(objects);
                    break;
                case "Student":
                    ParallelSorter<Student> studentSort = new ParallelSorter<>(4);
                    studentSort.sort(objects);
                    sortedObjects = new ArrayList<>(objects);
                    break;
                case "User":
                    ParallelSorter<User> userSort = new ParallelSorter<>(4);
                    userSort.sort(objects);
                    sortedObjects = new ArrayList<>(objects);
                    break;
            }
            System.out.println("Готовый список объектов (сортированный):");
            for(Field field : fields){
                System.out.printf("%-30s", field.getName());
            }
            System.out.println();
            viewArrayObject(sortedObjects);
            Comparable reqObject = BinarySearch.createReqObject(scanner, objects);
            System.out.println("Выберите пункт меню:\n" +
                    "0) Выход\n" +
                    "1) Записать полученный список в файл\n" +
                    "2) Бинарный поиск\n" +
                    "3) Кастомная сортировка\n" +
                    "4) Подсчёт количества вхождеий элемента\n");
            String menu = scanner.nextLine();
            switch (menu){
                case "0": return;
                case "1":
                    switch (className){
                        case "Bus": FileWriterUtil.appendBusesToFile(sortedObjects);
                            break;
                        case "Student": FileWriterUtil.appendStudentsToFile(sortedObjects);
                        case "User": FileWriterUtil.appendUsersToFile(sortedObjects);
                    }
                    break;
                case "2":
                    viewBinarySearch(sortedObjects, reqObject);
                    break;
                case "3":
                    ArrayList<Comparable> customSortedObjects = viewCustomSort(sortedObjects, reqObject, scanner);
                    viewArrayObject(customSortedObjects);
                    break;
                case "4":
                    System.out.println("Сколько потоков использовать?");
                    int numThreads = scanner.nextInt();
                    System.out.println("Количество вхождений элемента " + reqObject.toString() + "в коллекции: " + ThreadCounter.countOccurrences(sortedObjects, reqObject, numThreads));
                    break;
                default:
                    System.out.println("Такого варианта нет\n");
            }
        }
    }

    private static ArrayList<Comparable> viewCustomSort(ArrayList<Comparable> sortedObjects, Comparable reqObject, Scanner scanner) {
        ArrayList<Comparable> customSortObjects = new ArrayList<>();
        String answer;
        switch (sortedObjects.getFirst().getClass().getSimpleName()){
            case "Bus":
                ArrayList<Bus> buses = (ArrayList<Bus>) (ArrayList<?>) sortedObjects;
                System.out.println("Выберите поле для сортировки: " +
                        "1)number " +
                        "2)mileage");
                answer = scanner.nextLine();
                switch (answer){
                    case "1": CustomSort.sort(buses, Bus::getNumber); break;
                    case "2": CustomSort.sort(buses, Bus::getMileage);break;
                    default: System.out.println("Неверный вариант"); break;
                }
                customSortObjects = new ArrayList<>(buses);
                break;
            case "Student":
                ArrayList<Student> students = (ArrayList<Student>) (ArrayList<?>) sortedObjects;
                System.out.println("Выберите поле для сортировки: " +
                        "1)groupNumber " +
                        "2)gradeBookNumber");
                answer = scanner.nextLine();
                switch (answer){
                    case "1": CustomSort.sort(students, Student::getGroupNumber); break;
                    case "2": CustomSort.sort(students, Student::getGradeBookNumber);break;
                    default: System.out.println("Неверный вариант"); break;
                }
                customSortObjects = new ArrayList<>(students);
                break;
            case "User":
                System.out.println("Данный экземпляр не имеет числовыз полей. Кастомная сортировка невозможна");
                break;
        }
        return customSortObjects;
    }

    private static void viewBinarySearch(ArrayList<Comparable> objects, Comparable reqObject) {
        Scanner scanner = new Scanner(System.in);
        int indexOfRequiredObject = BinarySearch.indexByObject(objects, reqObject) + 1;
        if(indexOfRequiredObject == 0){System.out.println("Подобный объект не найден");}
        else {
            System.out.println("Искомый объект находится на " +  indexOfRequiredObject + " месте в отсортированном списке");
            System.out.println("Выберите пункт меню:\n" +
                    "0) Выход\n" +
                    "1) Записать значение в файл\n" +
                    "2) Очистить файл и записать в него значение\n");
            String menu = scanner.nextLine();
            switch (menu){
                case "0": return;
                case "1":
                    FileWriterUtil.appendNumberToFile(indexOfRequiredObject);
                    break;
                case "2":
                    FileWriterUtil.clearFile();
                    FileWriterUtil.appendNumberToFile(indexOfRequiredObject);
                    break;
                default:
                    System.out.println("Такого пункта нет\n");
            }
        }
    }

    private static void viewArrayObject(ArrayList<Comparable> objects) throws Exception {
        switch (objects.get(0).getClass().getSimpleName()){
            case "Bus": viewArrayBus(objects); break;
            case "Student": viewArrayStudent(objects); break;
            case "User": viewArrayUser(objects); break;
            default: throw new Exception("Получен неподдерживаемый тип файлов");
        }
    }

    private static void viewArrayBus(ArrayList<Comparable> objects) {
        @SuppressWarnings("unchecked")
        ArrayList<Bus> buses = (ArrayList<Bus>) (ArrayList<?>) objects;
        for(Bus bus : buses){
            System.out.printf("%-30s", bus.getNumber());
            System.out.printf("%-30s", bus.getModel());
            System.out.printf("%-30s", bus.getMileage());
            System.out.println();
        }
    }

    private static void viewArrayStudent(ArrayList<Comparable> objects) {
        @SuppressWarnings("unchecked")
        ArrayList<Student> students = (ArrayList<Student>) (ArrayList<?>) objects;
        for(Student student : students){
            System.out.printf("%-30s", student.getGroupNumber());
            System.out.printf("%-30s", student.getAverageScore());
            System.out.printf("%-30s", student.getGradeBookNumber());
            System.out.println();
        }
    }

    private static void viewArrayUser(ArrayList<Comparable> objects) {
        @SuppressWarnings("unchecked")
        ArrayList<User> users = (ArrayList<User>) (ArrayList<?>) objects;
        for(User user : users){
            System.out.printf("%-30s", user.getName());
            System.out.printf("%-30s", user.getPassword());
            System.out.printf("%-30s", user.getEmail());
            System.out.println();
        }
    }
}

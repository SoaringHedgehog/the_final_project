package main;

import algorithm.BinarySearch;
import algorithm.SelectionSort;
import entity.Bus;
import entity.Student;
import entity.User;
import input.InputFromFile;
import input.ManualInput;
import input.RandomInput;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
                    System.out.println("Заполнение из файла:\n" +
                            "Введите путь к файлу: ");
                    String fileName = scanner.nextLine();
                    FileInputStream fis = new FileInputStream(fileName);
                    switch (className){
                        case "Bus": objects = InputFromFile.readBusesFromInputStream(fis); break;
                        case "Student": objects = InputFromFile.readStudentsFromInputStream(fis); break;
                        case "User": objects = InputFromFile.readUsersFromInputStream(fis); break;
                    }
                    break;
                case "2":
                    System.out.println("Заполнение вручную:\n" +
                            "Введите количество объектов: ");
                    int countManualObjects = Integer.parseInt(scanner.nextLine());
                    switch (className){
                        case "Bus": objects = ManualInput.inputBuses(countManualObjects, scanner); break;
                        case "Student": objects = ManualInput.inputStudents(countManualObjects, scanner); break;
                        case "User": objects = ManualInput.inputUsers(countManualObjects, scanner); break;
                    }
                    break;
                case "3":
                    System.out.println("Заполнение случайными значениями:\n" +
                            "Введите количество объектов: ");
                    int countRandObjects = Integer.parseInt(scanner.nextLine());
                    switch (className){
                        case "Bus": objects = RandomInput.generateBuses(countRandObjects); break;
                        case "Student": objects = RandomInput.generateStudents(countRandObjects); break;
                        case "User": objects = RandomInput.generateUsers(countRandObjects); break;
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
                    SelectionSort<Bus> busSort = new SelectionSort<>();
                    @SuppressWarnings("unchecked")
                    ArrayList<Bus> buses = (ArrayList<Bus>) (ArrayList<?>) objects;
                    busSort.sort(buses);
                    sortedObjects = new ArrayList<>(buses);
                    break;
                case "Student":
                    SelectionSort<Student> studentSort = new SelectionSort<>();
                    @SuppressWarnings("unchecked")
                    ArrayList<Student> students = (ArrayList<Student>) (ArrayList<?>) objects;
                    studentSort.sort(students);
                    sortedObjects = new ArrayList<>(students);
                    break;
                case "User":
                    SelectionSort<User> userSort = new SelectionSort<>();
                    @SuppressWarnings("unchecked")
                    ArrayList<User> users = (ArrayList<User>) (ArrayList<?>) objects;
                    userSort.sort(users);
                    sortedObjects = new ArrayList<>(users);
                    break;
            }
            System.out.println("Готовый список объектов (сортированный):");
            for(Field field : fields){
                System.out.printf("%-30s", field.getName());
            }
            System.out.println();
            viewArrayObject(sortedObjects);
            viewBinarySearch(sortedObjects);
        }
    }

    private static void viewBinarySearch(ArrayList<Comparable> objects) {
        System.out.println("Для поиска индекса объекта требуются значения его полей");
        Field[] fields = objects.getFirst().getClass().getDeclaredFields();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение поля " + fields[0].getName());
        String firstfield = scanner.nextLine();
        System.out.println("Введите значение поля " + fields[1].getName());
        String secondfield = scanner.nextLine();
        System.out.println("Введите значение поля " + fields[2].getName());
        String thirdfield = scanner.nextLine();
        Comparable reqObject = null;
        switch (objects.getFirst().getClass().getSimpleName()){
            case "Bus":
                reqObject = new Bus.Builder()
                        .setNumber(Integer.parseInt(firstfield))
                        .setModel(secondfield)
                        .setMileage(Integer.parseInt(thirdfield))
                        .build();
                break;
            case "Student":
                reqObject = new Student.Builder()
                        .setGroupNumber(Integer.parseInt(firstfield))
                        .setAverageScore(Double.parseDouble(secondfield))
                        .setGradeBookNumber(Integer.parseInt(thirdfield))
                        .build();
                break;
            case "User":
                reqObject = new User.Builder()
                        .setName(firstfield)
                        .setPassword(secondfield)
                        .setEmail(thirdfield)
                        .build();
                break;
        }
        int indexOfRequiredObject = BinarySearch.indexByObject(objects, reqObject) + 1;
        if(indexOfRequiredObject == 0){System.out.println("Подобный объект не найден");}
            else {System.out.println("Искомый объект находится на " +  indexOfRequiredObject + " месте в отсортированном списке");}
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

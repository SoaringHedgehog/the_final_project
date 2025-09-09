package algorithm;

import entity.Bus;
import entity.Student;
import entity.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearch {

    public static Integer indexByObject(ArrayList<Comparable> sortedArray, Comparable requiredObject){
        int leftIndex = 0;
        int rightIndex = sortedArray.size() - 1 ;
        while(leftIndex <= rightIndex){
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if(sortedArray.get(midIndex).compareTo(requiredObject) == 0){
                return midIndex;
            } else if (sortedArray.get(midIndex).compareTo(requiredObject) < 0) {
                leftIndex = midIndex + 1;
            } else {
                rightIndex = midIndex - 1;
            }
        }
        return -1;
    }
    public static Comparable createReqObject(Scanner scanner, ArrayList<Comparable> objects){
        System.out.println("Для поиска индекса объекта требуются значения его полей");
        Field[] fields = objects.getFirst().getClass().getDeclaredFields();
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
        return reqObject;
    }
}

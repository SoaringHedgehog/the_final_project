package input;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class StreamManualInput {
    public static ArrayList<Comparable> inputBuses(int n, Scanner scanner) {
        return IntStream.range(0, n)
                .mapToObj(i -> {
                    ArrayList<Comparable> singleBus = ManualInput.inputBuses(1, scanner);
                    return singleBus.isEmpty() ? null : singleBus.get(0);
                })
                .filter(obj -> obj != null)
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Comparable> inputUsers(int n, Scanner scanner) {
        return IntStream.range(0, n)
                .mapToObj(i -> {
                    ArrayList<Comparable> singleUser = ManualInput.inputUsers(1, scanner);
                    return singleUser.isEmpty() ? null : singleUser.get(0);
                })
                .filter(obj -> obj != null)
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Comparable> inputStudents(int n, Scanner scanner) {
        return IntStream.range(0, n)
                .mapToObj(i -> {
                    ArrayList<Comparable> singleStudent = ManualInput.inputStudents(1, scanner);
                    return singleStudent.isEmpty() ? null : singleStudent.get(0);
                })
                .filter(obj -> obj != null)
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }
}

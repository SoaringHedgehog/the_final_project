package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;

public class CustomSort<T> implements Sort<T>{
    private final Comparator<? super T> comparator;

    public CustomSort(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public static <T, U extends Comparable<? super U>> void sort(ArrayList<T> array, Function<? super T, ? extends U> keyExtractor) {
        Comparator<T> comparator = Comparator.comparing(keyExtractor);
        new CustomSort<>(comparator).sort(array);
    }

    @Override
    public void sort(ArrayList<T> arr) {
        for (int i = 0; i < arr.size() - 1; i += 2) { // Идем по четным индексам массива
            int minIndex = i;
            for (int j = i + 2; j < arr.size(); j += 2) { // Идем по четным индексам массива
                if (comparator.compare(arr.get(j), arr.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = arr.get(i);
                arr.set(i, arr.get(minIndex));
                arr.set(minIndex, temp);
            }
        }
    }
}

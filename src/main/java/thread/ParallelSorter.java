package thread;

import algorithm.SelectionSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelSorter<T extends Comparable<T>> {
    private final int threadCount;

    public ParallelSorter(int threadCount) {
        this.threadCount = threadCount;
    }

    public void sort(ArrayList<Comparable> list) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        int size = list.size();
        int partSize = size / threadCount;

        List<ArrayList<Comparable>> parts = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            int start = i * partSize;
            int end = (i == threadCount - 1) ? size : start + partSize;
            parts.add(new ArrayList<>(list.subList(start, end)));
        }

        // Сортируем каждую часть в отдельном потоке
        for (ArrayList<Comparable> part : parts) {
            executor.submit(() -> new SelectionSort().sort(part));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Ожидание завершения всех потоков
        }

        // Объединяем отсортированные части
        ArrayList<Comparable> sortedList = new ArrayList<>();
        for (ArrayList<Comparable> part : parts) {
            sortedList.addAll(part);
        }

        // Сортируем объединенный список, чтобы гарантировать правильный порядок
        new SelectionSort().sort(sortedList);

        // Копируем отсортированный список обратно в исходный
        for (int i = 0; i < sortedList.size(); i++) {
            list.set(i, sortedList.get(i));
        }
    }
}
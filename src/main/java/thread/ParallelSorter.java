package thread;

import algorithm.SelectionSort;

import java.util.ArrayList;
import java.util.List;

public class ParallelSorter<T extends Comparable<T>> {
    public void sort(ArrayList<Comparable> list) {
        int n = list.size();
        int chunkSize = n / 2; // делим на 2 части для параллельной сортировки
        List<Thread> threads = new ArrayList<>();

        // Сортируем каждую половину в отдельном потоке
        for (int i = 0; i < 2; i++) {
            final int start = i * chunkSize;
            final int end = (i == 1) ? n : start + chunkSize;

            Runnable task = () -> {
                SelectionSort selectionSort = new SelectionSort();
                selectionSort.sort(new ArrayList<>(list.subList(start, end)));
            };

            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        // Ждем завершения всех потоков
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread was interrupted: " + e.getMessage());
            }
        }

        // Объединение отсортированных частей
        mergeSortedParts(list);
    }

    private void mergeSortedParts(ArrayList<Comparable> list) {
        // Для простоты примера просто вызовем сортировку на всем списке
        // Это не оптимально, но достаточно для демонстрации
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(list);
    }
}
package thread;

import algorithm.SelectionSort;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelSorter<T extends Comparable<T>> {
    private final int numThreads;

    public ParallelSorter(int numThreads) {
        this.numThreads = numThreads;
    }

    public ArrayList<T> parallelSort(ArrayList<T> list) throws Exception {
        int chunkSize = (int) Math.ceil((double) list.size() / numThreads);
        Future<?>[] futures = new Future[numThreads];
        ArrayList<T> sortedList;
        try (ExecutorService executor = Executors.newFixedThreadPool(numThreads)) {
            SelectionSort<T> selectionSort = new SelectionSort<>();

            ArrayList<ArrayList<T>> chunks = new ArrayList<>();
            for (int i = 0; i < numThreads; i++) {
                final int start = i * chunkSize;
                final int end = Math.min(start + chunkSize, list.size());
                if (start < list.size()) {
                    ArrayList<T> chunk = new ArrayList<>(list.subList(start, end));
                    chunks.add(chunk);
                    futures[i] = executor.submit(() -> selectionSort.sort(chunk));
                }
            }

            for (Future<?> future : futures) {
                if (future != null) {
                    future.get();
                }
            }

            sortedList = new ArrayList<>();
            for (ArrayList<T> chunk : chunks) {
                sortedList.addAll(chunk);
            }

            selectionSort.sort(sortedList);

            executor.shutdown();
        }
        return sortedList;
    }
}
package algorithm;
import java.util.*;


public class ThreadCounter {
    public static <T> int countOccurrences(List<T> list, T element, int numThreads) throws InterruptedException {
        int size = list.size();
        int elementsPerThread = (int) Math.ceil((double) size / numThreads);

        int[] results = new int[numThreads];

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * elementsPerThread;
            int end = Math.min(start + elementsPerThread, size);
            int threadIndex = i;

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int count = 0;
                    for (int j = start; j < end; j++) {
                        T current = list.get(j);
                        if (((Comparable<T>) current).compareTo(element) == 0) {
                            count++;
                        }
                    }
                    results[threadIndex] = count;
                }
            });

            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        int total = 0;

        for (int i = 0; i < results.length; i++) {
            total = total + results[i];
        }
        return total;
    }
}

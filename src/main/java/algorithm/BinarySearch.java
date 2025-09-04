package algorithm;

import java.util.ArrayList;

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
}

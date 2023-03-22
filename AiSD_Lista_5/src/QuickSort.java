import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuickSort {
    public static <T extends Number & Comparable<T>> ArrayList<T> sort(ArrayList<T> list) {
        quicksort(list, 0, list.size()-1);
        return list;
    }

    private static <T extends Number & Comparable<T>> void quicksort(ArrayList<T> list, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int partitionIndex = partition(list, startIndex, endIndex);
            quicksort(list, startIndex, partitionIndex-1);
            quicksort(list, partitionIndex+1, endIndex);
        }
    }

    private static <T extends Number & Comparable<T>> int partition (ArrayList<T> list, int startIndex, int endIndex) {
        T pivotValue = pick(list, startIndex, endIndex);
        int pivotIndex = list.indexOf(pivotValue);
        swap(list, startIndex, pivotIndex);
        int i = startIndex+1;
        int j = endIndex;
        do {
            while (i<=j && list.get(i).compareTo(pivotValue)<=0) {
                i++;
            }
            while (list.get(j).compareTo(pivotValue)>0) {
                j--;
            }
            if (i<j) {
                swap(list, i, j);
            }
        }
        while(i<j);
        swap(list, j, startIndex);
        return j;
    }

    private static <T extends Number & Comparable<T>> void swap (ArrayList<T> list, int left, int right) {
        T temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }

    private static <T extends Number & Comparable<T>> T pick(ArrayList<T> list, int startIndex, int endIndex) {
        if (list.size() < 6) {
            Collections.sort(list);
            return list.get((list.size()-1)/2);
        }
        ArrayList<ArrayList<T>> groups = new ArrayList<>();
        int groupCounter = 0;
        int groupIndex = -1;
        for (int i = startIndex; i<endIndex; i++) {
            if (groupCounter == 0) {
                groupIndex++;
                groups.add(new ArrayList<>());
            }
            groups.get(groupIndex).add(list.get(i));
            groupCounter++;
            if (groupCounter == 4) {
                groupCounter = 0;
            }
        }
        ArrayList<T> medians = new ArrayList<>();
        for (int i = 0; i<groups.size(); i++) {
            Collections.sort(groups.get(i));
            medians.add(groups.get(i).get((groups.get(i).size()-1)/2));
        }
        return pick(medians, 0, medians.size()-1);
    }

    private static <T extends Number & Comparable<T>> T median(ArrayList<T> list) {
        Collections.sort(list);
        if (list.size() == 2 || list.size() == 3)  {
            return list.get(1);
        }
        if (list.size() == 4 || list.size() == 5)  {
            return list.get(2);
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        ArrayList<Integer> tab = new ArrayList<>();
        tab.add(0);
        tab.add(2);
        tab.add(6);
        tab.add(5);
        tab.add(7);
        tab.add(9);
        tab.add(3);
        System.out.println(tab);
        sort(tab);
        System.out.println(tab);
    }
}

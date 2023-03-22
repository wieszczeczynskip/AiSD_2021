import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Timing {
    public static void main(String[] args) {
        int[] tabBucketSort = new int[1000];
        int[] tabArraySort = new int[1000];
        ArrayList<Integer> tabCollSort = new ArrayList<>();
        ArrayList<Integer> tabQuickSort = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i<tabBucketSort.length; i++) {
            int v = random.nextInt(100);
            tabArraySort[i] = v;
            tabBucketSort[i] = v;
            tabCollSort.add(v);
            tabQuickSort.add(v);
        }
        long time = System.nanoTime();
        tabBucketSort = BucketSort.bucketSort(tabBucketSort, 20);
        System.out.println("BucketSort: " + (System.nanoTime()-time));
        time = System.nanoTime();
        Arrays.sort(tabArraySort);
        System.out.println("ArraysSort: " + (System.nanoTime()-time));
        time = System.nanoTime();
        Collections.sort(tabCollSort);
        System.out.println("CollectionsSort: " + (System.nanoTime()-time));
        time = System.nanoTime();
        QuickSort.sort(tabQuickSort);
        System.out.println("QuickSort: " + (System.nanoTime()-time));
    }
}

import java.util.*;

public class BucketSort{

    public static int[] bucketSort(int[] tab, int n) {
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] > max) {
                max = tab[i];
            }
            if (tab[i] < min) {
                min = tab[i];
            }
        }
        double range = (double) (max - min) / (double) n;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < n; j++) {
                if (tab[i] >= min + (j * range) && tab[i] < min + range + (j * range)) {
                    buckets.get(j).add(tab[i]);
                    break;
                }
                if (tab[i] == max) {
                    buckets.get(n-1).add(tab[i]);
                    break;
                }
            }
        }
        ArrayList<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < buckets.size(); i++) {
            Collections.sort(buckets.get(i));
            sorted.addAll(buckets.get(i));
        }
        int[] sortedArray = new int[sorted.size()];
        for (int i = 0; i<sorted.size(); i++) {
            sortedArray[i] = sorted.get(i);
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] tab = new int[7];
        tab[0] = 0;
        tab[1] = 2;
        tab[2] = 6;
        tab[3] = 5;
        tab[4] = 7;
        tab[5] = 9;
        tab[6] = 3;
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + ", ");
        }
        System.out.println();
        tab = bucketSort(tab, 3);
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + ", ");
        }
    }
}

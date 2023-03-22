import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Random;

import static java.util.Collections.sort;

public class Zadanie1 {

    public static int selection(ArrayList<Integer> list, int k) {
        if (list.size() == 0) {
            throw new EmptyArrayException();
        }
        if (k > list.size() || k < 1) {
            throw new IndexOutOfBoundsException();
        }
        ArrayList<Integer> lower = new ArrayList<Integer>();
        ArrayList<Integer> equal = new ArrayList<Integer>();
        ArrayList<Integer> higher = new ArrayList<Integer>();
        Random random = new Random();
        int v = list.get(random.nextInt(list.size()));
        for (int i = 0; i<list.size(); i++) {
            if (list.get(i) == v) {
                equal.add(list.get(i));
            }
            else {
                if (list.get(i) > v) {
                    higher.add(list.get(i));
                }
                else {
                    lower.add(list.get(i));
                }
            }
        }
        /*System.out.println("Lower: " + lower);
        System.out.println("Equal: " + equal);
        System.out.println("Higher: " + higher);
        System.out.println("K: " + k);*/
        if (k <= lower.size()) {
            return selection(lower, k);
        }
        if (k <= lower.size() + equal.size()) {
            return v;
        }
        return selection(higher, k - lower.size() - equal.size());
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list.add(8);
        list.add(3);
        list.add(3);
        list.add(9);
        list.add(13);
        list.add(6);
        list.add(6);
        list.add(3);
        list.add(8);
        list.add(7);
        list.add(8);
        list.add(11);
        list.add(5);
        list.add(5);
        list.add(7);
        list.add(12);
        list.add(6);
        list.add(2);
        list.add(16);
        list.add(8);
        list.add(3);
        list.add(16);
        list.add(6);
        list.add(18);
        list.add(12);
        list.add(4);
        list.add(20);
        list.add(19);
        list.add(9);
        list.add(11);
        sort(list);
        System.out.println(list);
        System.out.println(selection(list, 1));
    }
}

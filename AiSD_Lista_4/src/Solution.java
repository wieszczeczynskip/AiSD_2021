// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Solution {
    public static int solution(int[] A) {
        int counter = 0;
        for (int i = 0; i<A.length; i++) {
            for (int j = i+1; j<A.length; j++) {
                if (A[i] + A[j] >= j-i) {
                    counter++;
                }
                if (counter > 10000000) {
                    return -1;
                }
            }
        }
        return counter;
    }

    public static void main (String[] args) {
        String[] arrayS = new String[0];
        try (Scanner input = new Scanner(new File("src/test-input.txt"))) {
            arrayS = input.nextLine().replaceAll("[^0-9]", "").split("");
        }
        catch (IOException e) {
            System.out.println("error");
        }
        int[] array = new int[arrayS.length];
        for (int i = 0; i<array.length; i++) {
            array[i] = Integer.parseInt(arrayS[i]);
        }
        System.out.println(solution(array));
    }
}

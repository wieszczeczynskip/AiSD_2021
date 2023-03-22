import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.sort;
import static org.junit.jupiter.api.Assertions.*;

class Zadanie1Test {

    ArrayList<Integer> list;
    int k;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        Random random = new Random();
        int counter = 0;
        while (counter < 50) {
            list.add(random.nextInt(100));
            counter++;
        }
        sort(list);
        k = random.nextInt(49)+1;
    }

    @AfterEach
    void tearDown() {
    }

    @RepeatedTest(100)
    void testSelection() {
        assertEquals(list.get(k-1), Zadanie1.selection(list, k));
    }

    @Test
    void testSelection2() {
        ArrayList<Integer> list2 = new ArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> {Zadanie1.selection(list, 0);});
        assertThrows(IndexOutOfBoundsException.class, () -> {Zadanie1.selection(list, 100);});
        assertThrows(EmptyArrayException.class, () -> {Zadanie1.selection(list2, 10);});
    }
}
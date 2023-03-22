import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    ArrayList<Integer> tab1 = new ArrayList<>();
    ArrayList<Integer> tab2 = new ArrayList<>();

    @BeforeEach
    void setUp() {
        tab1.add(78);
        tab1.add(84);
        tab1.add(16);
        tab1.add(76);
        tab1.add(53);
        tab1.add(52);
        tab1.add(77);
        tab1.add(53);
        tab1.add(79);
        tab1.add(82);
        tab1.add(41);
        tab1.add(21);
        tab1.add(32);
        tab1.add(93);
        tab1.add(21);
        tab1.add(77);
        tab1.add(24);
        tab2.add(78);
        tab2.add(84);
        tab2.add(16);
        tab2.add(76);
        tab2.add(53);
        tab2.add(52);
        tab2.add(77);
        tab2.add(53);
        tab2.add(79);
        tab2.add(82);
        tab2.add(41);
        tab2.add(21);
        tab2.add(32);
        tab2.add(93);
        tab2.add(21);
        tab2.add(77);
        tab2.add(24);
        Collections.sort(tab2);
    }

    @Test
    void sort() {
        QuickSort.sort(tab1);
        assertEquals(tab2, tab1);
    }
}
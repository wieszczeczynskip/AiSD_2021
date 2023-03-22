import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BucketSortTest {

    int[] tab = new int[5];
    int[] tab2 = new int[5];

    @BeforeEach
    void setUp() {
        tab[0] = 5;
        tab[1] = 83;
        tab[2] = 64;
        tab[3] = 99;
        tab[4] = 13;
        tab2[0] = 5;
        tab2[1] = 83;
        tab2[2] = 64;
        tab2[3] = 99;
        tab2[4] = 13;
        Arrays.sort(tab2);
        tab = BucketSort.bucketSort(tab, 3);
    }

    @Test
    void testBucketSort() {
        assertArrayEquals(tab2, tab);
    }
}
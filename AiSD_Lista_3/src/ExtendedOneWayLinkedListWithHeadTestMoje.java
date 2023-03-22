import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedOneWayLinkedListWithHeadTestMoje {

    @Test
    void clear() {
        ExtendedOneWayLinkedListWithHead<Integer> test = new ExtendedOneWayLinkedListWithHead<>();
        test.add(1);
        test.add(2);
        test.clear();
        assertNull(test.getElement(0));
    }

    @Test
    void isEmpty() {
        ExtendedOneWayLinkedListWithHead<Integer> test = new ExtendedOneWayLinkedListWithHead<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.remove(0);
        test.remove(0);
        test.remove(0);
        test.remove(0);
        assertTrue(test.isEmpty());
    }
}
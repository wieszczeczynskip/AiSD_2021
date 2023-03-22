import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    BST<Integer> bst = new BST();
    BST<Integer> bst2 = new BST();

    @BeforeEach
    void setUp() {
        bst.root = bst.new Node(7, null);
        bst.root.right = bst.new Node(8, bst.root);
        bst.root.left = bst.new Node(3, bst.root);
        bst.root.left.left = bst.new Node(2, bst.root.left);
        bst.root.left.left.left = bst.new Node(1, bst.root.left.left);
        bst.root.left.right = bst.new Node(5, bst.root.left);
        bst.root.left.right.right = bst.new Node(6, bst.root.left.right);

        bst2.root = bst.new Node(7, null);
        bst2.root.right = bst.new Node(8, null);
        bst2.root.left = bst.new Node(3, null);
        bst2.root.left.left = bst.new Node(2, null);
        bst2.root.left.left.left = bst.new Node(1, null);
        bst2.root.left.right = bst.new Node(5, null);
        bst2.root.left.right.right = bst.new Node(6, null);
    }

    @Test
    void insert() {
        bst.insert(4);
        assertEquals(bst.root.left.right.left.value, 4);
    }

    @Test
    void delete() {
        bst.insert(4);
        bst.delete(3);
        assertEquals(bst.root.left.value, 4);
        assertEquals(bst.root.left.right.value, 5);
        assertEquals(bst.root.left.left.value, 2);
    }

    @Test
    void upper() {
        assertEquals(bst.upper(4), 5);
    }

    @Test
    void lower() {
        assertEquals(bst.lower(3), 3);
    }
}
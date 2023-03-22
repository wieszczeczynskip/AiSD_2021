import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>>{

    class Node {
        T value;
        Node left;
        Node right;
        Node parent;
        Node (T obj, Node parentNode) {
            value = obj;
            parent = parentNode;
        }
        Node (T obj, Node leftNode, Node rightNode, Node parentNode) {
            value = obj;
            left = leftNode;
            right = rightNode;
            parent = parentNode;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    Node root;

    public BST() {
        root = null;
    }

    public void insert(T obj) {
        if (root == null) {
            root = new Node(obj, null);
        }
        else {
            Node cursor = root;
            while (true) {
                if (cursor.value == obj) {
                    System.out.println("Object " + obj + " is already in the tree");
                    break;
                }
                if (obj.compareTo(cursor.value) < 0) {
                    if (cursor.left != null) {
                        cursor = cursor.left;
                    }
                    else {
                        cursor.left = new Node(obj, cursor);
                        break;
                    }
                }
                else {
                    if (cursor.right != null) {
                        cursor = cursor.right;
                    }
                    else {
                        cursor.right = new Node(obj, cursor);
                        break;
                    }
                }
            }
        }
    }

    public void delete(T obj) {
        Node cursor = root;
        while (true) {
            if (obj == cursor.value) {
                break;
            }
            if (obj.compareTo(cursor.value) < 0) {
                if (cursor.left != null) {
                    cursor = cursor.left;
                }
                else {
                    throw new NoSuchElementException();
                }
            }
            if (obj.compareTo(cursor.value) > 0) {
                if (cursor.right != null) {
                    cursor = cursor.right;
                }
                else {
                    throw new NoSuchElementException();
                }
            }
        }
        if (cursor.left == null && cursor.right == null) {
            if (cursor.parent.left.value == obj) {
                cursor.parent.left = null;
                return;
            }
            if (cursor.parent.right.value == obj) {
                cursor.parent.right = null;
                return;
            }
        }
        if (cursor.left != null && cursor.right != null) {
            T objToMove = upper(obj);
            Node secondCursor = root;
            while (true) {
                if (secondCursor.value == objToMove) {
                    break;
                }
                if (objToMove.compareTo(secondCursor.value) < 0) {
                    secondCursor = secondCursor.left;
                }
                if (objToMove.compareTo(secondCursor.value) > 0) {
                    secondCursor = secondCursor.right;
                }
            }
            delete(objToMove);
            if (cursor.parent.left.value == obj) {
                cursor.parent.left = secondCursor;
            }
            if (cursor.parent.right.value == obj) {
                cursor.parent.right = secondCursor;
            }
            secondCursor.left = cursor.left;
            secondCursor.right = cursor.right;
            return;
        }
        if (cursor.left != null) {
            if (cursor.parent.left.value == obj) {
                cursor.parent.left = cursor.left;
                return;
            }
            if (cursor.parent.right.value == obj) {
                cursor.parent.right = cursor.left;
                return;
            }
        }
        if (cursor.right != null) {
            if (cursor.parent.left.value == obj) {
                cursor.parent.left = cursor.right;
                return;
            }
            if (cursor.parent.right.value == obj) {
                cursor.parent.right = cursor.right;
                return;
            }
        }
    }

    public T upper(T obj) {
        Node cursor = root;
        T upper = null;
        while (true) {
            if (obj.compareTo(cursor.value) < 0) {
                upper = cursor.value;
                if (cursor.left != null) {
                    cursor = cursor.left;
                }
                else {
                    break;
                }
            }
            if (obj.compareTo(cursor.value) >= 0) {
                if (cursor.right != null) {
                    cursor = cursor.right;
                }
                else {
                    break;
                }
            }
        }
        if (upper == null) {
            throw new NoSuchElementException();
        }
        return upper;
    }

    public T lower(T obj) {
        Node cursor = root;
        T lower = null;
        while (true) {
            if (obj.compareTo(cursor.value) <= 0) {
                if (cursor.left != null) {
                    cursor = cursor.left;
                }
                else {
                    break;
                }
            }
            if (obj.compareTo(cursor.value) > 0) {
                lower = cursor.value;
                if (cursor.right != null) {
                    cursor = cursor.right;
                }
                else {
                    break;
                }
            }
        }
        if (lower == null) {
            throw new NoSuchElementException();
        }
        return lower;
    }

    public void passage(int v) {
        if (v == 1) {
            preorder(root);
        }
        if (v == 2) {
            inorder(root);
        }
        if (v == 3) {
            postorder(root);
        }
    }

    public void preorder(Node root) {
        if (root != null) {
            System.out.println(root);
            preorder(root.left);
            preorder(root.right);
        }
    }

    public void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root);
            inorder(root.right);
        }
    }

    public void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.println(root);
        }
    }

    public static void main (String[] args) {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(7);
        bst.insert(3);
        bst.insert(5);
        bst.insert(4);
        bst.insert(2);
        bst.insert(1);
        bst.insert(8);
        bst.insert(6);
        GFGTreePrinter.print2D(bst.root);
        bst.delete(3);
        System.out.println("=========================");
        GFGTreePrinter.print2D(bst.root);
        bst.passage(3);
    }
}

public class BinomialHeap<T extends Comparable<T>> {

    Node<T> head;

    class Node <T extends Comparable<T>>{
        int degree;
        Node<T> parent;
        T key;
        Node<T> child;
        Node<T> sibling;

        public Node(T elem) {
            this.key = elem;
            this.degree = 0;
            this.parent = null;
            this.child = null;
            this.sibling = null;
        }

        public T getKey() {
            return key;
        }
    }

    public BinomialHeap() {
        head = null;
    }

    public void insert(T elem) {
        if (head == null) {
            head = null;
        }
    }

    public T minimum() {
        T min = head.getKey();
        Node cursor = head;
        while (cursor != null) {
            if (cursor.key.compareTo(min) < 0) {
                min = (T) cursor.getKey();
            }
            cursor = cursor.sibling;
        }
        return min;
    }


}

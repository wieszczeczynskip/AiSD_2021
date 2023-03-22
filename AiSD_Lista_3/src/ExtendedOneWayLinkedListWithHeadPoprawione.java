import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ExtendedOneWayLinkedListWithHeadPoprawione<E> extends AbstractList<E> implements Iterable<E> {
    private final OneWayLinkedListWithHead<E> innerList = new OneWayLinkedListWithHead<>();
    private int lenght = 0;

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<>() {
            @Override
            public boolean hasNext() {
                return cursor<lenght-1;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!(hasNext())) {
                    throw new NoSuchElementException();
                }
                cursor++;
                canBeRemovedOrSet = true;
                return innerList.get(cursor);
            }

            @Override
            public boolean hasPrevious() {
                return cursor >= 0;
            }

            @Override
            public E previous() throws NoSuchElementException{
                if (!(hasPrevious())) {
                    throw new NoSuchElementException();
                }
                cursor--;
                canBeRemovedOrSet = true;
                return innerList.get(cursor+1);
            }

            @Override
            public int nextIndex() {
                return cursor + 1;
            }

            @Override
            public int previousIndex() {
                return cursor;
            }

            @Override
            public void remove() throws IllegalStateException{
                if (canBeRemovedOrSet) {
                    innerList.remove(cursor);
                    canBeRemovedOrSet = false;
                    lenght--;
                }
                else {
                    throw new IllegalStateException();
                }
            }

            @Override
            public void set(E e) throws IllegalStateException{
                if (canBeRemovedOrSet) {
                    innerList.set(cursor, e);
                    canBeRemovedOrSet = false;
                }
                else {
                    throw new IllegalStateException();
                }
            }

            @Override
            public void add(E e) {
                if (isEmpty()) {
                    innerList.set(0,e);
                    cursor=0;
                }
                else {
                    innerList.add(cursor + 1, e);
                    cursor++;
                    canBeRemovedOrSet = false;
                }
                lenght++;
            }
            int cursor = -1;
            boolean canBeRemovedOrSet = false;
        };
    }

    @Override
    public E remove(int index) {
        lenght--;
        return innerList.remove(index);
    }

    @Override
    public boolean remove(Element<E> e) {
        lenght--;
        return innerList.remove(e);
    }

    @Override
    public int size() {
        return innerList.size();
    }

    @Override
    public boolean add(E value) {
        lenght++;
        return innerList.add(value);
    }

    @Override
    public boolean add(int index, E element) {
        lenght++;
        return innerList.add(index, element);
    }

    @Override
    public void clear() {
        lenght = 0;
        innerList.clear();
    }

    @Override
    public boolean contains(E element) {
        return innerList.contains(element);
    }

    @Override
    public E get(int index) {
        return innerList.get(index);
    }

    @Override
    public E set(int index, E element) {
        return innerList.set(index, element);
    }

    @Override
    public int indexOf(E element) {
        return innerList.indexOf(element);
    }

    @Override
    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return this.listIterator();
    }

    public Element<E> getElement(int index) {
        return innerList.getElement(index);
    }

    public static void main(String[] args) {
        ExtendedOneWayLinkedListWithHeadPoprawione<Integer> test = new ExtendedOneWayLinkedListWithHeadPoprawione<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        System.out.println(test.getElement(0).getNext().getValue());
        ListIterator<Integer> iterator = test.listIterator();
        iterator.add(5);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.previous());
        System.out.println(iterator.next());
        System.out.println(iterator.previous());
        System.out.println(iterator.previous());
        System.out.println(iterator.previous());
        System.out.println(iterator.hasNext());
        iterator.next();
        iterator.next();
        iterator.next();
        System.out.println(iterator.hasNext());
        iterator.next();
        System.out.println(iterator.hasNext());
        iterator.next();
        System.out.println(iterator.hasNext());
    }
}
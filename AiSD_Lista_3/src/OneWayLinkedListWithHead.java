import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedListWithHead<E> extends AbstractList<E> {

    Element<E> head = null;

    public OneWayLinkedListWithHead() {
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return head.getNext() != null;
            }

            @Override
            public E next() {
                head = head.getNext();
                return head.getValue();
            }
        };
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            private int cursor = -1;
            private boolean canBeSetOrRemoved = false;
            @Override
            public boolean hasNext() {
                return getElement(cursor).getNext() != null;
            }

            @Override
            public E next() {
                if(hasNext()) {
                    cursor++;
                    canBeSetOrRemoved = true;
                    return get(cursor);
                }
                throw new NoSuchElementException();
            }

            @Override
            public boolean hasPrevious() {
                return cursor>=0;
            }

            @Override
            public E previous() throws NoSuchElementException{
                if (hasPrevious()) {
                    cursor--;
                    if (cursor != -1) {
                        canBeSetOrRemoved = true;
                    }
                    return get(cursor+1);
                }
                throw new NoSuchElementException();
            }

            @Override
            public int nextIndex() throws NoSuchElementException{
                if (hasNext()) {
                    return cursor + 1;
                }
                throw new NoSuchElementException();
            }

            @Override
            public int previousIndex() throws NoSuchElementException{
                if (hasPrevious()) {
                    return cursor;
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                OneWayLinkedListWithHead.this.remove(cursor);
            }

            @Override
            public void set(E e) {
                OneWayLinkedListWithHead.this.set(indexOf(head.getNext().getValue()), e);
            }

            @Override
            public void add(E e) {
                OneWayLinkedListWithHead.this.add(indexOf(head.getNext().getValue()), e);
            }
        };
    }


    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if (head == null)
            return null;
        if (index == 0) {
            E retValue = head.getValue();
            head = head.getNext();
            return retValue;
        }
        Element<E> actElem = getElement(index - 1);
        if (actElem == null || actElem.getNext() == null)
            return null;
        E retValue = (E) actElem.getNext().getValue();
        actElem.setNext(actElem.getNext().getNext());
        return retValue;
    }

    @Override
    public int size() {
        int pos=0;
        Element<E> actElem=head;
        while(actElem!=null)
        {
            pos++;
            actElem=actElem.getNext();
        }
        return pos;}

    @Override
    public boolean remove(Element<E> e) {
        if (head == null)
            return false;
        if (head.getValue().equals(e)) {
            head = head.getNext();
            return true;
        }
        Element<E> actElem = head;
        while (actElem.getNext() != null && !actElem.getNext().getValue().equals(e))
            actElem = actElem.getNext();
        if (actElem.getNext() == null)
            return false;
        actElem.setNext(actElem.getNext().getNext());
        return true;
    }

    @Override
    public boolean add(E e) {
        Element<E> newElem = new Element<E>(e);
        if (head == null) {
            head = newElem;
            return true;
        }
        Element<E> tail = head;
        while (tail.getNext() != null)
            tail = tail.getNext();
        tail.setNext(newElem);
        return true;
    }

    public Element<E> getElement(int index) {
        Element<E> actElem = head;
        while (index > 0 && actElem != null) {
            index--;
            actElem = actElem.getNext();
        }
        return actElem;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(int index, E element) {
        if (index < 0) return false;
        Element<E> newElem = new Element<E>(element);
        if (index == 0) {
            newElem.setNext(head);
            head = newElem;
            return true;
        }
        Element<E> actElem = getElement(index - 1);
        if (actElem == null) {
            return false;
        }
        newElem.setNext(actElem.getNext());
        actElem.setNext(newElem);
        return true;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element)>=0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        Element<E> actElem = getElement(index);
        return actElem == null ? null : actElem.getValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        Element<E> actElem = (Element<E>) getElement(index);
        if (actElem == null)
            return null;
        E elemData = (E)actElem.getValue();
        actElem.setValue(element);
        return elemData;
    }

    @Override
    public int indexOf(E element) {
        int pos = 0;
        Element<E> actElem = head;
        while (actElem != null) {
            if (actElem.getValue().equals(element))
                return pos;
            pos++;
            actElem = actElem.getNext();
        }
        return -1;
    }
}
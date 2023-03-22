import java.util.Iterator;

public abstract class AbstractList<E> implements IList<E> {
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append('[');
        if (!isEmpty()) {
            for (E item : this)
                buffer.append(item).append(", ");
            buffer.setLength(buffer.length() - 2);
        }
        buffer.append(']');
        return buffer.toString();
    }

    // ^ - bitowa różnica symetryczna
    @Override
    public int hashCode() {
        int hashCode = 0;
        for (E item : this)
            hashCode ^= item.hashCode();
        return hashCode;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        return equals((IList<E>) object);
    }

    public boolean equals(IList<E> other) {
        if (other == null || size() != other.size())
            return false;
        else {
            Iterator<E> i = iterator();
            Iterator<E> j = other.iterator();
            for (; i.hasNext() && j.hasNext() && i.next().equals(j.next()); )
                ; // puste ciało pętli for
            return !i.hasNext() && !j.hasNext();
        }
    }
}

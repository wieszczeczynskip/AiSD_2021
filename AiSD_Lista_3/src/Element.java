class Element<E> {
    private E value;
    private Element<E> next;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Element<E> getNext() {
        return next;
    }

    public void setNext(Element<E> next) {
        this.next = next;
    }

    Element(E data) {
        this.value = data;
    }
}

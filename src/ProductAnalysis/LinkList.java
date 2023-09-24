package ProductAnalysis;

import java.util.Iterator;

public class LinkList<T extends Comparable<T>> implements Iterable<T> {

    // Inner node class
    public static class Node<T extends Comparable<T>> {
        public T Value;
        public Node<T> Link;

        public Node(T value) {
            Value = value;
            Link = null;
        }
    }

    // Fields
    private int Count;
    private Node<T> Head;
    private Node<T> Tail;

    // Constructors
    public LinkList() {
        Head = null;
        Tail = null;
        Count = 0;
    }

    // Getters and setters
    public int getCount() {
        return Count;
    }

    // Linked list methods
    public void Add(T value) {
        Node<T> current = new Node<>(value);

        if (Head == null)
            Head = Tail = current;
        else {
            Tail.Link = current;
            Tail = current;
        }
        Count++;
    }

    public boolean Remove(T value) {
        if (Head != null) {
            if (Head.Value.equals(value)) {
                Head = Head.Link;
                if (Head == null)
                    Tail = null;
                Count--;
                return true;
            }

            for (Node<T> current = Head; current != null; current = current.Link) {
                if (current.Link != null && current.Link.Value.equals(value)) {
                    current.Link = current.Link.Link;
                    if (current.Link == null)
                        Tail = current;
                    Count--;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean Contains(T value) {
        for (T item : this) {
            if (item.equals(value))
                return true;
        }
        return false;
    }

    public void Sort() {
        for (Node<T> iterator1 = Head; iterator1 != null; iterator1 = iterator1.Link) {
            Node<T> min = iterator1;
            for (Node<T> iterator2 = iterator1.Link; iterator2 != null; iterator2 = iterator2.Link) {
                if (iterator2.Value.compareTo(min.Value) < 0)
                    min = iterator2;
            }

            T temp = iterator1.Value;
            iterator1.Value = min.Value;
            min.Value = temp;
        }
    }

    // Iterator
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = Head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.Value;
                current = current.Link;
                return data;
            }
        };
    }
}

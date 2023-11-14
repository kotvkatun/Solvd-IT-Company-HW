package classes.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CoolLinkedList<T> implements Iterable<T> {
    private static final Logger LOGGER = LogManager.getLogger(CoolLinkedList.class);
    private int size;
    private Node<T> head, tail;

    static class Node<S> {
        S task;
        Node<S> next;

        Node(S task) {
            this.task = task;
            next = null;
        }
    }

    public CoolLinkedList() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T task) {
        if (head == null) {
            head = new Node<>(task);
            tail = head;
            size++;
            return;
        }
        tail.next = new Node<>(task);
        tail = tail.next;
        size++;
    }

    public T get(int index) {
        Node<T> pointer = head;
        int currentNode = 0;
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        while (currentNode != index) {
            pointer = pointer.next;
            currentNode++;
        }
        return pointer.task;
    }

    public void remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    public void clear() {
        tail = null;
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void printList() {
        Node<T> pointer = head;
        while (pointer != null) {
            LOGGER.info(pointer.task.toString());
            pointer = pointer.next;
        }
    }

    public void clearDupes() {
        Set<T> tSet = new HashSet<>(size);
        Node<T> pointer = head;
        while (pointer != null) {
            tSet.add(pointer.task);
            pointer = pointer.next;
        }
        this.clear();
        for (T task : tSet) {
            this.add(task);
        }
    }

    static class ListIterator<T> implements Iterator<T> {
        Node<T> current;

        public ListIterator(CoolLinkedList<T> list) {
            current = list.head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T task = current.task;
            current = current.next;
            return task;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(this);
    }
}

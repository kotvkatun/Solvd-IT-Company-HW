package classes.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TasksLinkedList<T> implements Iterable<T> {
    private static final Logger LOGGER = LogManager.getLogger(TasksLinkedList.class);
    private int size;
    private Node<T> head, last;

    static class Node<S> {
        int index;
        S task;
        Node<S> next;

        Node(S task) {
            this.task = task;
            next = null;
        }
    }

    public TasksLinkedList() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T task) {
        if (head == null) {
            head = new Node<>(task);
            last = head;
            size++;
            return;
        }
        last.next = new Node<>(task);
        last = last.next;
        size++;
    }

    public Task get(int index) {
        Node<T> pointer = head;
        int currentNode = 0;
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        while (currentNode != index) {
            pointer = pointer.next;
            currentNode++;
        }
        return (Task) pointer.task;
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
        last = null;
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

        public ListIterator(TasksLinkedList<T> list) {
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

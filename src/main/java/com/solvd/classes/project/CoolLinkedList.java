package com.solvd.classes.project;

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
        S data;
        Node<S> next;

        Node(S data) {
            this.data = data;
            next = null;
        }
    }

    public CoolLinkedList() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        if (head == null) {
            head = new Node<>(element);
            tail = head;
            size++;
            return;
        }
        tail.next = new Node<>(element);
        tail = tail.next;
        size++;
    }

    public void add(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> pointer = head;
            int i = 0;
            while (i != index - 1) {
                pointer = pointer.next;
                i++;
            }
            newNode.next = pointer.next;
            pointer.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        Node<T> pointer = head;
        int currentNode = 0;
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (currentNode != index) {
            pointer = pointer.next;
            currentNode++;
        }
        return pointer.data;
    }

    public void remove(int index) {
        if (index >= size || index < 0) {
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

    public void remove(T element) {
        Node<T> pointer1 = head;
        Node<T> pointer2 = head.next;
        if (pointer1.data.equals(element)) {
            head = head.next;
            size--;
            return;
        }
        while (pointer2 != null) {
            if (pointer2.data.equals(element)) {
                pointer1.next = pointer1.next.next;
                size--;
                return;
            }
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
    }

    public void set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> pointer = head;
        for (int i = 0; i != index; i++) {
            pointer = pointer.next;
        }
        pointer.data = element;
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
            LOGGER.info(pointer.data.toString());
            pointer = pointer.next;
        }
    }

    public void clearDupes() {
        Set<T> tSet = new HashSet<>(size);
        Node<T> pointer = head;
        while (pointer != null) {
            tSet.add(pointer.data);
            pointer = pointer.next;
        }
        this.clear();
        for (T element : tSet) {
            this.add(element);
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
            T element = current.data;
            current = current.next;
            return element;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(this);
    }
}

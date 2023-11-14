package classes.project;

import classes.interfaces.CreatableFromInput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TasksLinkedList<T extends CreatableFromInput> {
    private static final Logger LOGGER = LogManager.getLogger(TasksLinkedList.class);
    private int size;
    private Node<T> head;
    private Node<T> last;

    class Node<S extends CreatableFromInput> {
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
        Node<T> pointer = head;
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
}

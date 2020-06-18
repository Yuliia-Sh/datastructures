package list.impl;

import list.List;

import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public void add(T value) {
        Node<T> node = new Node<T>();
        node.value = value;
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
        size++;
    }

    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be in range [0, " + size + "]");
        }
        Node<T> node = new Node<T>();
        node.value = value;

        if (index == 0) {
            node.next = head;
            if (size > 0) {
                head.next.previous = node;
            }
            head = node;
        } else if (index == size) {
            tail.next = node;
            node.previous = tail;
            tail = node;
        } else {
            Node<T> nodeBefore = getNode(index - 1);
            Node<T> nodeAfter = nodeBefore.next;
            nodeBefore.next = node;
            nodeAfter.previous = node;
            node.next = nodeAfter;
            node.previous = nodeBefore;
        }
        size++;
    }

    public T remove(int index) {
        validateIndex(index);
        Node<T> nodeToRemove;
        if (index == 0) {
            nodeToRemove = head;
            head = head.next;
            head.previous = null;
        } else if (index == size - 1) {
            nodeToRemove = tail;
            tail = tail.previous;
            tail.next = null;
        } else {
            nodeToRemove = getNode(index);
            Node<T> previousNode = nodeToRemove.previous;
            Node<T> nextNode = nodeToRemove.next;
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
        }
        size--;
        return nodeToRemove.value;
    }

    public T get(int index) {
        validateIndex(index);
        Node<T> node = getNode(index);
        return node.value;
    }

    public T set(T value, int index) {
        validateIndex(index);
        Node<T> node = getNode(index);
        T oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    public void clear() {
        tail = null;
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean contains(T value) {
        return (indexOf(value) != -1);
    }

    public int indexOf(T value) {
        Node<T> node = head;
        int i = 0;
        while (node != null) {
            if (Objects.equals(value, node.value)) {
                return i;
            }
            node = node.next;
            i++;
        }
        return -1;
    }

    public int lastIndexOf(T value) {
        Node<T> node = tail;
        int i = size - 1;
        while (node != null) {
            if (Objects.equals(value, node.value)) {
                return i;
            }
            node = node.previous;
            i--;
        }
        return -1;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Node<T> node = head;
        while (node != null) {
            stringJoiner.add(node.value.toString());
            node = node.next;
        }
        return stringJoiner.toString();
    }

    private Node<T> getNode(int index) {
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be in range [0, " + (size - 1) + "]");
        }
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;
    }
}

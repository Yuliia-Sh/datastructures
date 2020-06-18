package list.impl;

import list.List;

import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList implements List {
    private Node head;
    private Node tail;
    private int size = 0;

    public void add(Object value) {
        Node node = new Node();
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

    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be in range [0, " + size + "]");
        }
        Node node = new Node();
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
            Node nodeBefore = getNode(index - 1);
            Node nodeAfter = nodeBefore.next;
            nodeBefore.next = node;
            nodeAfter.previous = node;
            node.next = nodeAfter;
            node.previous = nodeBefore;
        }
        size++;
    }

    public Object remove(int index) {
        validateIndex(index);
        Node nodeToRemove;
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
            Node previousNode = nodeToRemove.previous;
            Node nextNode = nodeToRemove.next;
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
        }
        size--;
        return nodeToRemove.value;
    }

    public Object get(int index) {
        validateIndex(index);
        Node node = getNode(index);
        return node.value;
    }

    public Object set(Object value, int index) {
        validateIndex(index);
        Node node = getNode(index);
        Object oldValue = node.value;
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

    public boolean contains(Object value) {
        return (indexOf(value) != -1);
    }

    public int indexOf(Object value) {
        Node node = head;
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

    public int lastIndexOf(Object value) {
        Node node = tail;
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
        Node node = head;
        while (node != null) {
            stringJoiner.add(node.value.toString());
            node = node.next;
        }
        return stringJoiner.toString();
    }

    private Node getNode(int index) {
        Node node = head;
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

    private static class Node {
        Object value;
        Node next;
        Node previous;
    }
}

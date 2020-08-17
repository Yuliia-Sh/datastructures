package com.study.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> extends AbstractList<T>  {
    private Node<T> head;
    private Node<T> tail;

    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be in range [0, " + size + "]");
        }
        Node<T> node = new Node<T>(value);

        if (size == 0) {
            head = node;
            tail = node;
        } else if (index == 0) {
            node.next = head;
            head.prev = node;
            head = node;
        } else if (index == size) {
            tail.next = node;
            node.prev = tail;
            tail = node;
        } else {
            Node<T> nodeBefore = getNode(index - 1);
            Node<T> nodeAfter = nodeBefore.next;
            nodeBefore.next = node;
            nodeAfter.prev = node;
            node.next = nodeAfter;
            node.prev = nodeBefore;
        }
        size++;
    }

    public T remove(int index) {
        validateIndex(index);
        Node<T> nodeToRemove;
        if (index == 0) {
            nodeToRemove = head;
        } else if (index == size - 1) {
            nodeToRemove = tail;
        } else {
            nodeToRemove = getNode(index);
        }
        return removeNode(nodeToRemove);
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
            node = node.prev;
            i--;
        }
        return -1;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Node<T> node = head;
        while (node != null) {
            stringJoiner.add(String.valueOf(node.value));
            node = node.next;
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private Node<T> getNode(int index) {
        Node<T> node = head;
        int middle = size / 2;
        if (index < middle) {
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private T removeNode(Node<T> nodeToRemove) {
        if (size == 1) {
            head = null;
            tail = null;
        } else if (nodeToRemove == head) {
            head = head.next;
            head.prev = null;
        } else if (nodeToRemove == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<T> previousNode = nodeToRemove.prev;
            Node<T> nextNode = nodeToRemove.next;
            previousNode.next = nextNode;
            nextNode.prev = previousNode;
        }

        nodeToRemove.next = null;
        nodeToRemove.prev = null;

        size--;
        return nodeToRemove.value;
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> currentNode = head;
        private boolean isFirstElement = true;
        private boolean isRemoved = false;

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            return isFirstElement || currentNode.next != null;
        }

        @Override
        public T next() {
            if (size == 0 || (!isFirstElement && currentNode.next == null)) {
                throw new NoSuchElementException();
            }
            if (isFirstElement) {
                isFirstElement = false;
            } else {
                currentNode = currentNode.next;
            }
            isRemoved = false;
            return currentNode.value;
        }

        @Override
        public void remove() {
            if (isFirstElement || isRemoved) {
                throw new IllegalStateException();
            }
            if (currentNode == head && size > 1) {
                isFirstElement = true;
            }
            Node<T> prevNode = currentNode.prev;
            LinkedList.this.removeNode(currentNode);
            if (isFirstElement) {
                currentNode = head;
            } else {
                currentNode = prevNode;
            }
            isRemoved = true;
        }
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        private Node(T value) {
            this.value = value;
        }
    }
}

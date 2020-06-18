package list.impl;

import list.List;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList implements List {
    private Object[] array;
    private int size = 0;
    private int capacity;

    public ArrayList(int n) {
        array = new Object[n];
        capacity = n;
    }

    public ArrayList() {
        array = new Object[10];
        capacity = 10;
    }

    public void add(Object value) {
        if (size == capacity) {
            extendArray();
        }
        array[size] = value;
        size++;
    }

    private void extendArray() {
        int newCapacity = (int) Math.round(capacity * 1.5);
        array = Arrays.copyOfRange(array, 0, newCapacity);
        capacity = newCapacity;
    }

    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be in range [0, " + size + "]");
        }
        if (index == size) {
            add(value);
        } else {
            Object[] newArray = new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = value;
            System.arraycopy(array, index, newArray, index + 1, size - index + 1);
            array = newArray;
            size++;
        }
    }

    public Object remove(int index) {
        validateIndex(index);
        Object removedObject = array[index];
        if (index == 0) {
            System.arraycopy(array, 1, array, 0, size - 1);
        } else if (index != size - 1) {
            Object[] newArray = new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, size - index);
            array = newArray;
        }
        size--;
        return removedObject;
    }

    public Object get(int index) {
        validateIndex(index);
        return array[index];
    }

    public Object set(Object value, int index) {
        validateIndex(index);
        Object oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    public void clear() {
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, array[i])) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(value, array[i])) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(array[i].toString());
        }
        return stringJoiner.toString();
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be in range [0, " + (size - 1) + "]");
        }
    }
}

package com.study.datastructures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> extends AbstractList<T> implements List<T> {
    private final static int DEFAULT_INITIAL_CAPACITY = 10;
    private T[] array;

    @SuppressWarnings("unchecked")
    public ArrayList(int initialCapacity) {
        if (initialCapacity == 0) {
            array = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
        } else {
            array = (T[]) new Object[initialCapacity];
        }
    }

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public void add(T value) {
        add(value, size);
    }

    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be in range [0, " + size + "]");
        }
        if (size == array.length) {
            extendArray();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    public T remove(int index) {
        validateIndex(index);
        T removedObject = array[index];
        if (index != size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return removedObject;
    }

    public T get(int index) {
        validateIndex(index);
        return array[index];
    }

    public T set(T value, int index) {
        validateIndex(index);
        T oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }


    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, array[i])) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(T value) {
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
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }


    private void extendArray() {
        int newCapacity = (int) Math.round(array.length * 1.5);
        array = Arrays.copyOfRange(array, 0, newCapacity);
    }

}

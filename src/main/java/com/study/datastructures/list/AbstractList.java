package com.study.datastructures.list;

public abstract class AbstractList<T> implements List<T> {
    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public void add(T value) {
        add(value, size);
    }

    protected void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be in range [0, " + (size - 1) + "]");
        }
    }

}

package com.study.datastructures.map;

import java.util.ArrayList;

public class HashMap implements Map {
    private static final int INITIAL_CAPACITY = 5;

    private ArrayList<Entry>[] buckets = new ArrayList[INITIAL_CAPACITY];
    private int size;

    @Override
    public Object put(Object key, Object value) {
        Object oldValue = null;
        boolean isUpdated = false;
        int numBucket = getNumBucket(key);

        if (buckets[numBucket] == null) {
            add(key, value, numBucket);
        } else {
            for (Entry entry : buckets[numBucket]) {
                if (entry.key.equals(key)) {
                    oldValue = entry.value;
                    entry.value = value;
                    isUpdated = true;
                }
            }

            if (!isUpdated) {
                buckets[numBucket].add(new Entry(key, value));
                size++;
            }
        }

        return oldValue;
    }


    @Override
    public Object get(Object key) {
        int numBucket = getNumBucket(key);
        if (buckets[numBucket] != null) {
            for (Entry entry : buckets[numBucket]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public Object remove(Object key) {
        int numBucket = getNumBucket(key);

        if (buckets[numBucket] != null) {
            for (int i = 0; i < buckets[numBucket].size(); i++) {
                Entry currentEntry = buckets[numBucket].get(i);
                if (currentEntry.key.equals(key)) {
                    Object removedValue = currentEntry.value;
                    buckets[numBucket].remove(i);
                    size--;
                    return removedValue;
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map map) {
        if (map.size() != 0) {

        }
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        int numBucket = getNumBucket(key);
        Object presentValue = null;
        boolean isAbsent = true;

        if (buckets[numBucket] != null) {
            for (int i = 0; (i < buckets[numBucket].size()) && isAbsent; i++) {
                Entry currentEntry = buckets[numBucket].get(i);
                if (currentEntry.key.equals(key)) {
                    presentValue = currentEntry.value;
                    isAbsent = false;
                }
            }
        }

        if (isAbsent) {
            add(key, value, numBucket);
        }

        return presentValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean containsKey(Object key) {
        return (get(key) != null);
    }

    private void add(Object key, Object value, int numBucket) {
        if (buckets[numBucket] == null) {
            buckets[numBucket] = new ArrayList<>();
        }
        buckets[numBucket].add(new Entry(key, value));
        size++;
    }

    private int getNumBucket(Object key) {
        return key.hashCode() % INITIAL_CAPACITY;
    }

    private static class Entry {
        private Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}

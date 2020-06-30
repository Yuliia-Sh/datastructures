package com.study.datastructures.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 5;

    @SuppressWarnings("unchecked")
    private final ArrayList<Entry<K, V>>[] buckets = new ArrayList[INITIAL_CAPACITY];
    private int size;

    @Override
    public V put(K key, V value) {
        V oldValue = null;

        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            oldValue = entry.getValue();
            entry.setValue(value);
        } else {
            add(key, value);
        }

        return oldValue;
    }


    @Override
    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int numBucket = getBucketIndex(key);

        if (buckets[numBucket] == null) {
            return null;
        }

        for (int i = 0; i < buckets[numBucket].size(); i++) {
            Entry<K, V> currentEntry = buckets[numBucket].get(i);
            if (Objects.equals(currentEntry.getKey(), key)) {
                V removedValue = currentEntry.getValue();
                buckets[numBucket].remove(i);
                size--;
                return removedValue;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<K, V> map) {
        Iterator<Entry<K, V>> iterator = map.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V putIfAbsent(K key, V value) {
        V presentValue = get(key);

        if (presentValue == null) {
            add(key, value);
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
    public boolean containsKey(K key) {
        return (get(key) != null);
    }


    private Entry<K, V> getEntry(K key) {
        int numBucket = getBucketIndex(key);
        if (buckets[numBucket] == null) {
            return null;
        }
        for (Entry<K, V> entry : buckets[numBucket]) {
            if (Objects.equals(entry.getKey(), key)) {
                return entry;
            }
        }
        return null;
    }

    private void add(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new ArrayList<>();
        }
        buckets[bucketIndex].add(new Entry<K, V>(key, value));
        size++;
    }

    private int getBucketIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode()) % INITIAL_CAPACITY;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Entry<K, V>> {
        private int num = 0;
        private int numBucket = 0;
        private int numInBucket = -1;

        @Override
        public boolean hasNext() {
            return num < size;
        }

        @Override
        public Entry<K, V> next() {
            if (num == size) {
                throw new NoSuchElementException();
            }
            num++;
            while (buckets[numBucket] == null || numInBucket == buckets[numBucket].size() - 1) {
                numBucket++;
                numInBucket = -1;
            }
            if (numInBucket < buckets[numBucket].size() - 1) {
                numInBucket++;
            }
            return buckets[numBucket].get(numInBucket);
        }

        @Override
        public void remove() {
            Entry<K, V> currentEntry = buckets[numBucket].get(numInBucket);
            HashMap.this.remove(currentEntry.getKey());
            numInBucket--;
            num--;
        }
    }

}

package com.study.datastructures.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 5;

    private final Entry[] buckets = new Entry[INITIAL_CAPACITY];
    private int size;

    @Override
    public V put(K key, V value) {
        V oldValue = null;

        Entry<K, V> entry = new Entry<>(key, value);
        Entry<K, V> oldEntry = findEntryByKey(entry);
        if (oldEntry != null) {
            oldValue = oldEntry.getValue();
            oldEntry.setValue(value);
        } else {
            add(entry);
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
        int bucketIndex = getBucketIndex(key);

        if (buckets[bucketIndex] == null) {
            return null;
        }

        Entry<K, V> currentEntry = buckets[bucketIndex];
        Entry<K, V> removedEntry = null;
        if (Objects.equals(currentEntry.getKey(), key)) {
            buckets[bucketIndex] = currentEntry.next;
            removedEntry = currentEntry;
        } else {
            while (currentEntry.next != null && !Objects.equals(currentEntry.next.getKey(), key)) {
                currentEntry = currentEntry.next;
            }
            if (currentEntry.next != null && Objects.equals(currentEntry.next.getKey(), key)) {
                removedEntry = currentEntry.next;
                currentEntry.next = currentEntry.next.next;
            }
        }
        if (removedEntry != null) {
            removedEntry.next = null;
            size--;
            return removedEntry.value;
        }
        return null;
    }

    @Override
    public void putAll(Map<K, V> map) {
        Iterator<Map.Entry<K, V>> iterator = map.iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V putIfAbsent(K key, V value) {
        V presentValue = get(key);

        if (presentValue == null) {
            add(new Entry<>(key, value));
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

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private Entry<K, V> findEntryByKey(Entry<K, V> entry) {
        int bucketIndex = getBucketIndexByHash(entry.getHash());
        if (buckets[bucketIndex] == null) {
            return null;
        }
        Entry<K, V> entryFound = buckets[bucketIndex];
        while (entryFound != null && !Objects.equals(entry.getKey(), entryFound.getKey())) {
            entryFound = entryFound.next;
        }
        return entryFound;
    }

    private Entry<K, V> getEntry(K key) {
        int bucketIndex = getBucketIndex(key);
        if (buckets[bucketIndex] == null) {
            return null;
        }
        Entry<K, V> entry = buckets[bucketIndex];
        while (entry != null && !Objects.equals(entry.getKey(), key)) {
            entry = entry.next;
        }
        return entry;
    }

    private void add(Entry<K, V> entry) {
        int bucketIndex = getBucketIndex(entry);
        entry.next = buckets[bucketIndex];
        buckets[bucketIndex] = entry;
        size++;
    }

    private int getBucketIndex(K key) {
        if (key == null) {
            return 0;
        }

        int hash = key.hashCode();
        return getBucketIndexByHash(hash);
    }

    private int getBucketIndexByHash(int hash) {
        if (hash == Integer.MIN_VALUE) {
            return INITIAL_CAPACITY - 1;
        }

        return Math.abs(hash) % INITIAL_CAPACITY;
    }

    private int getBucketIndex(Entry<K, V> entry) {
        int hash = entry.getHash();
        return getBucketIndexByHash(hash);
    }

    private static class Entry<K, V> implements Map.Entry {
        private final int hash;
        private final K key;
        private V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            if (key != null) {
                hash = key.hashCode();
            } else {
                hash = 0;
            }
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int getHash() { return hash;}
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private int currentNumber;
        private int currentBucket = -1;
        Entry<K, V> currentEntry;
        private boolean isRemoved = true;

        @Override
        public boolean hasNext() {
            return currentNumber < size;
        }

        @Override
        public Entry<K, V> next() {
            if (currentNumber == size) {
                throw new NoSuchElementException();
            }
            currentNumber++;
            if (currentEntry == null) {
                findNextCurrentEntry();
            } else {
                currentEntry = currentEntry.next;
                if (currentEntry == null) {
                    findNextCurrentEntry();
                }
            }
            isRemoved = false;
            return currentEntry;
        }

        private void findNextCurrentEntry() {
            while (currentEntry == null && currentBucket < buckets.length) {
                currentBucket++;
                currentEntry = buckets[currentBucket];
            }
        }

        @Override
        public void remove() {
            if (isRemoved) {
                throw new IllegalStateException();
            }
            HashMap.this.remove(currentEntry.getKey());
            currentNumber--;
            isRemoved = true;
        }
    }

}

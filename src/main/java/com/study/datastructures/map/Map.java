package com.study.datastructures.map;

public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {
    V put(K key, V value);

    V get(K key);

    V remove(K key);

    void putAll(Map<K, V> map);

    V putIfAbsent(K key, V value);

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}
package com.study.datastructures.map;

public class Entry<K, V> {
    public final K key;
    public V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

package com.study.datastructures.map;

import org.junit.Test;


import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashMapTest {
    private HashMap<String, String> hashMap = new HashMap<>();

    @Test
    public void testPut() {
        assertNull(hashMap.put("key1", "value1"));
        assertEquals(1, hashMap.size());

        assertNull(hashMap.put("key2", "value2"));
        assertEquals(2, hashMap.size());

        assertEquals("value1", hashMap.put("key1", "value3"));
        assertEquals(2, hashMap.size());
    }

    @Test
    public void testPutAndGet() {
        assertNull(hashMap.put("key1", "value1"));
        assertEquals("value1", hashMap.get("key1"));

        assertNull(hashMap.get("key2"));
    }

    @Test
    public void testPutAndGetNullKey() {
        assertNull(hashMap.put(null, "value1"));
        assertEquals("value1", hashMap.get(null));
    }

    @Test
    public void testPutAndGetNegativeHashCode() {
        assertNull(hashMap.put("aaaaaaaaaa", "value1"));
        assertEquals("value1", hashMap.get("aaaaaaaaaa"));
    }

    @Test
    public void testRemove() {
        hashMap.put("key1", "value1");

        assertEquals("value1", hashMap.remove("key1"));
        assertEquals(0, hashMap.size());

        assertNull(hashMap.remove("key2"));
        assertEquals(0, hashMap.size());
    }

    @Test
    public void testRemoveFromEmptyMap() {
        assertNull(hashMap.remove("key1"));
    }

    @Test
    public void testPutAll() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        HashMap anotherHashMap = new HashMap();
        anotherHashMap.put("key3", "value3");
        anotherHashMap.put("key4", "value4");

        hashMap.putAll(anotherHashMap);
        assertEquals(4, hashMap.size());
        assertEquals("value1", hashMap.get("key1"));
        assertEquals("value2", hashMap.get("key2"));
        assertEquals("value3", hashMap.get("key3"));
        assertEquals("value4", hashMap.get("key4"));
    }

    @Test
    public void testPutAllFromEmptyMap() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        HashMap anotherHashMap = new HashMap();

        hashMap.putAll(anotherHashMap);
        assertEquals(2, hashMap.size());
        assertEquals("value1", hashMap.get("key1"));
        assertEquals("value2", hashMap.get("key2"));
    }

    @Test
    public void testPutIfAbsent() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(null, hashMap.putIfAbsent("key3", "value3"));
        assertEquals(3, hashMap.size());

        assertEquals("value3", hashMap.putIfAbsent("key3", "value4"));
        assertEquals(3, hashMap.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(hashMap.isEmpty());

        hashMap.put("key1", "value1");
        assertFalse(hashMap.isEmpty());

        hashMap.put("key2", "value2");
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void testContainsKey() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertTrue(hashMap.containsKey("key1"));
        assertTrue(hashMap.containsKey("key2"));
        assertFalse(hashMap.containsKey("key3"));
    }

    @Test
    public void testIteratorEmptyMap() {
        Iterator<Map.Entry<String, String>> iterator = hashMap.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNext() {
        Iterator<Map.Entry<String, String>> iterator = hashMap.iterator();
        hashMap.put("key1", "value1");
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testIterator() {
        Iterator<Map.Entry<String, String>> iterator = hashMap.iterator();
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        assertTrue(iterator.hasNext());
        Map.Entry<String, String> entryFirst = iterator.next();
        assertTrue(hashMap.containsKey(entryFirst.getKey()));

        assertTrue(iterator.hasNext());
        Map.Entry<String, String> entrySecond = iterator.next();
        assertTrue(hashMap.containsKey(entrySecond.getKey()));
        assertFalse(iterator.hasNext());

        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @Test
    public void testIteratorRemove() {
        Iterator<Map.Entry<String, String>> iterator = hashMap.iterator();
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            iterator.remove();
        }
        assertEquals(0, hashMap.size());
    }
}

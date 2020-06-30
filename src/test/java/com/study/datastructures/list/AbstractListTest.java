package com.study.datastructures.list;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractListTest {
    private List<Integer> list = getList();

    @BeforeEach
    public void setList() {
        list = getList();
    }

    protected abstract List getList();

    @Test
    public void testToString() {
        assertEquals("[]", list.toString());
    }

    @Test
    public void testToStringOneElement() {
        list.add(1);
        assertEquals("[1]", list.toString());
    }

    @Test
    public void testToStringSomeElements() {
        list.add(1);
        list.add(2);
        assertEquals("[1, 2]", list.toString());
    }

    @Test
    public void testToStringNull() {
        list.add(null);
        list.add(2);
        assertEquals("[null, 2]", list.toString());
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());
    }

    @Test
    public void testAdd() {

        list.add(1);
        list.add(2);

        assertEquals(2, list.size());
    }

    @Test
    public void testGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        Integer expectedFirst = 1;
        Integer expectedSecond = 2;
        Integer expectedThird = 3;
        assertEquals(expectedFirst, list.get(0));
        assertEquals(expectedSecond, list.get(1));
        assertEquals(expectedThird, list.get(2));
    }

    @Test
    public void testGetFromEmptyList() {

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });

    }

    @Test
    public void testGetFromListByNegativeIndex() {

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });

    }

    @Test
    public void testGetFromListByIndexGreaterThanSize() {

        list.add(1);
        list.add(2);
        list.add(3);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(3);
        });

    }

    @Test
    public void testAddByIndexInTheMiddle() {

        list.add(1);
        list.add(3);
        list.add(2, 1);

        assertEquals(3, list.size());
        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    public void testAddByIndexAsFirstElement() {

        list.add(1);
        list.add(2);

        assertEquals(2, list.size());

        list.add(0, 0);

        assertEquals(3, list.size());
        assertEquals("[0, 1, 2]", list.toString());
    }

    @Test
    public void testAddByIndexAsLastElement() {

        list.add(1);
        list.add(2);

        assertEquals(2, list.size());

        list.add(3, 2);

        assertEquals(3, list.size());
        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    public void testAddByIndexGreaterThanSize() {

        list.add(1);
        list.add(3);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(2, 4);
        });

    }

    @Test
    public void testSetByNegativeIndex() {

        list.add(1);
        list.add(3);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set(2, -1);
        });

    }

    @Test
    public void testSetByIndexGreaterThanSize() {

        list.add(1);
        list.add(3);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set(2, 2);
        });

    }

    @Test
    public void testSet() {

        list.add(1);
        list.add(3);

        list.set(2, 0);

        assertEquals(2, list.size());
        assertEquals("[2, 3]", list.toString());

    }

    @Test
    public void testClearEmptyList() {
        list.clear();
        assertEquals(0, list.size());
    }

    ;

    @Test
    public void testClear() {
        list.add(1);
        list.add(3);

        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testIsEmptyForEmptyList() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        list.add(1);
        list.add(3);

        assertFalse(list.isEmpty());
    }

    @Test
    public void testContainsEmptyList() {
        assertFalse(list.contains(1));
    }

    @Test
    public void testContainsElement() {
        list.add(1);
        list.add(3);
        boolean isContains = list.contains(1);
        assertTrue(isContains);
    }

    @Test
    public void testContainsNoElement() {
        list.add(1);
        list.add(3);
        assertFalse(list.contains(2));
    }

    @Test
    public void testIndexOf() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(2, list.indexOf(3));
    }

    @Test
    public void testIndexOfNotExists() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(-1, list.indexOf(5));
    }

    @Test
    public void testLastIndexOf() {
        list.add(1);
        list.add(1);
        list.add(3);
        assertEquals(1, list.lastIndexOf(1));
    }

    @Test
    public void testLastIndexOfNotExists() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(-1, list.lastIndexOf(5));
    }

    @Test
    public void testRemoveFromOneElementArray() {
        list.add(1);

        list.remove(0);

        assertEquals(0, list.size());
    }

    @Test
    public void testRemoveLast() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(2);

        assertEquals("[1, 2]", list.toString());
    }

    @Test
    public void testRemoveFirst() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(0);

        assertEquals("[2, 3]", list.toString());
        assertEquals(0, list.indexOf(2));
        assertEquals(1, list.indexOf(3));
    }

    @Test
    public void testRemoveFromMiddle() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(1);

        assertEquals("[1, 3]", list.toString());
        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(3));
    }

    @Test
    public void testIteratorEmptyArray() {
        Iterator<Integer> it = list.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> {
            it.next();
        });
    }

    @Test
    public void testIterator() {
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> it = list.iterator();

        Integer[] listOfInteger = new Integer[4];
        int i = 0;
        while (it.hasNext()) {
            Integer element = it.next();
            listOfInteger[i] = element;
            i++;
        }

        assertThrows(NoSuchElementException.class, () -> {
            it.next();
        });

        Integer one = 1;
        Integer two = 2;
        Integer three = 3;
        assertEquals(one, listOfInteger[0]);
        assertEquals(two, listOfInteger[1]);
        assertEquals(three, listOfInteger[2]);
        assertNull(listOfInteger[3]);
    }

    @Test
    public void testIteratorRemove() {
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }

        assertEquals(0, list.size());
    }

    @Test
    public void testIteratorRemoveFromMiddle() {
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer value = it.next();
            if (value == 2) {
                it.remove();
            }
        }

        assertEquals(2, list.size());
        assertEquals("[1, 3]", list.toString());
    }

    @Test
    public void testIteratorRemoveLast() {
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer value = it.next();
            if (value == 3) {
                it.remove();
            }
        }

        assertEquals(2, list.size());
        assertEquals("[1, 2]", list.toString());
    }
}

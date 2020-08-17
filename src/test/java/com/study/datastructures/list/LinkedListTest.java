package com.study.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest extends AbstractListTest {

    protected List getList() {
        return new LinkedList();
    }

    @Test
    public void testGet() {
        List list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Integer expectedFirst = 1;
        Integer expectedSecond = 2;
        Integer expectedThird = 3;
        Integer expectedForth = 4;
        Integer expectedFifth = 5;

        assertEquals(expectedFirst, list.get(0));
        assertEquals(expectedSecond, list.get(1));
        assertEquals(expectedThird, list.get(2));
        assertEquals(expectedForth, list.get(3));
        assertEquals(expectedFifth, list.get(4));
    }
}
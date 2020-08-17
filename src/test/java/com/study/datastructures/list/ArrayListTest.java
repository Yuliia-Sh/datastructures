package com.study.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ArrayListTest extends AbstractListTest {

    protected List<Integer> getList() {
        return new ArrayList<Integer>(5);
    }

    @Test
    public void testExtendArray() {
        List<Integer> list = new ArrayList<Integer>(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    public void testArrayWith0InitialCapacity() {
        List<Integer> list = new ArrayList<Integer>(0);
        list.add(1);
    }

    @Test
    public void testArrayWithoutInitialCapacity() {
        List<Integer> list = new ArrayList<Integer>();
        assertEquals(0, list.size());
    }
}
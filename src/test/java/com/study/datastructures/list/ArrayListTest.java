package com.study.datastructures.list;

import org.junit.Test;

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
}
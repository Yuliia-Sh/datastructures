package com.study.datastructures.list;

import org.junit.Test;

public class ArrayListTest extends AbstractListTest {

    protected List getList() {
        return new ArrayList(5);
    }

    @Test
    public void testExtendArray() {
        List list = new ArrayList(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    public void testArrayWith0InitialCapacity() {
        List list = new ArrayList(0);
        list.add(1);
    }
}
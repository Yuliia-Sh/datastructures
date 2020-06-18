package list.impl;

import list.List;
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
    }
}
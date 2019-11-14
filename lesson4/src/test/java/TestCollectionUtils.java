import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestCollectionUtils {

    @Test
    public void testAddAll() {
        List<Integer> dst = new ArrayList<>();
        dst.add(1);
        dst.add(2);

        List<Integer> src = new ArrayList<>();
        src.add(3);
        src.add(4);

        CollectionUtils.addAll(src, dst);
        List<Integer> actual = Arrays.asList(1, 2, 3, 4);
        Assert.assertEquals(dst, actual);
    }

    @Test
    public void testNewArrayList() {
        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(2);

        List<Integer> expected = CollectionUtils.newArrayList();
        expected.add(1);
        expected.add(2);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testIndexOf() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        int actual = 3;
        int expected = CollectionUtils.indexOf(list, 4);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAdd() {
        List<Object> expected = new ArrayList<>();

        CollectionUtils.add(expected, 1);
        List<Integer> actual = new ArrayList<>();
        actual.add(1);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testRemoveAll() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        List<Integer> listRemove = new ArrayList<>();
        listRemove.add(1);
        listRemove.add(2);
        CollectionUtils.removeAll(expected, listRemove);

        List<Integer> actual = new ArrayList<>();
        actual.add(3);
        actual.add(4);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testContainsAll() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);
        actual.add(4);

        List<Integer> actual2 = new ArrayList<>();
        actual2.add(1);
        actual2.add(2);
        actual2.add(5);

        Assert.assertTrue(CollectionUtils.containsAll(expected, actual));
        Assert.assertFalse(CollectionUtils.containsAll(expected, actual2));
    }

    @Test
    public void testContainsAny() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(6);

        List<Integer> actual2 = new ArrayList<>();
        actual2.add(6);
        actual2.add(7);

        Assert.assertTrue(CollectionUtils.containsAny(expected, actual));
        Assert.assertFalse(CollectionUtils.containsAll(expected, actual2));
    }


    class A {
    }


    @Test
    public void testMethodRange() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> expected = CollectionUtils.range(list, 2, 5);
        List<Integer> actual = Arrays.asList(2, 3, 4, 5);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMethodRangeErrorCompile() {
        // ошибка компиляции. wildcard используется правильно
//        List<A> list = new ArrayList<>();
//        List<A> expected = CollectionUtils.range(list, new A(), new A());
//        List<Integer> actual = Arrays.asList(2,3,4,5);
//
//        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMethodRangeWithComparator() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        Comparator<Integer> comp = Comparator.naturalOrder();
        List<Integer> expected = CollectionUtils.range(list, 2, 5, comp);
        List<Integer> actual = Arrays.asList(2, 3, 4, 5);
        Assert.assertEquals(expected, actual);
    }
}

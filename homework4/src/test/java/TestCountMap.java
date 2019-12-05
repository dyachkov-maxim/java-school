import org.junit.Assert;
import org.junit.Test;

public class TestCountMap {

    @Test
    public void testGetCount() {
        CountMap<Integer> map = new CountMapIml<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        int expected = map.getCount(5);
        int actual = 2;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemove() {
        CountMap<Integer> map = new CountMapIml<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        int expected = map.remove(10);
        int actual = 3;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCountDifferentElement() {
        CountMap<Integer> map = new CountMapIml<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        int expected = map.size();
        int actual = 3;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddAll() {
        CountMap<Integer> map = new CountMapIml<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        CountMap<Integer> map1 = new CountMapIml<>();
        map1.add(12);
        map1.add(10);

        CountMap<Integer> map2 = new CountMapIml<>();
        map2.add(13);
        map2.add(10);

        map.addAll(map1);
        map.addAll(map2);

        int expected = map.size();
        int actual = 5;

        Assert.assertEquals(expected, actual);
    }
}

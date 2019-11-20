import org.junit.Assert;
import org.junit.Test;

public class TestBeanUtils {
    @Test
    public void test() {
        MyClass from = new MyClass(456, 2, 3);
        MyClass to = new MyClass();

        int actual = from.getA();
        int expected = to.getA();
        Assert.assertNotEquals(expected, actual);

        BeanUtils.assign(to, from);
        expected = to.getA();

        Assert.assertEquals(expected, actual);
    }
}

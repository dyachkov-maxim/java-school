import org.junit.Assert;
import org.junit.Test;

public class MyScalableThreadPoolTest {
    @Test
    public void testShutdown() {
        int countThreadMin = 4;
        int countThreadMax = 6;
        int countTask = 4;
        MyExecutorService pool = FactoryExecutorService.newScalableThreadPool(countThreadMin, countThreadMax);

        long start = System.currentTimeMillis();

        for (int i = 0; i < countTask; i++) {
            pool.execute(new TestTask(String.format("задача %d", i)));
        }

        pool.shutdown();

        Assert.assertTrue((System.currentTimeMillis() - start) < 5_500);
    }

    @Test
    public void testShutdown2() {
        int countThreadMin = 4;
        int countThreadMax = 6;
        int countTask = 10;
        MyExecutorService pool = FactoryExecutorService.newScalableThreadPool(countThreadMin, countThreadMax);

        long start = System.currentTimeMillis();

        for (int i = 0; i < countTask; i++) {
            pool.execute(new TestTask(String.format("задача %d", i)));
        }

        pool.shutdown();

        Assert.assertTrue((System.currentTimeMillis() - start) < 10_500);
    }

    @Test
    public void testShutdownNow() {
        int countThreadMin = 4;
        int countThreadMax = 6;
        int countTask = 10;
        MyExecutorService pool = FactoryExecutorService.newScalableThreadPool(countThreadMin, countThreadMax);

        long start = System.currentTimeMillis();

        for (int i = 0; i < countTask; i++) {
            pool.execute(new TestTask(String.format("задача %d", i)));
        }

        pool.shutdownNow();

        Assert.assertTrue((System.currentTimeMillis() - start) < 5_500);
    }
}
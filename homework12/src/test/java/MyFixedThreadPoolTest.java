import org.junit.Assert;
import org.junit.Test;

public class MyFixedThreadPoolTest {

    @Test
    public void testShutdown() {
        int countThread = 4;
        int countTask = 4;
        MyExecutorService pool = FactoryExecutorService.newFixedThreadPool(countThread);

        long start = System.currentTimeMillis();

        for (int i = 0; i < countTask; i++) {
            pool.execute(new TestTask(String.format("задача %d", i)));
        }

        pool.shutdown();

        Assert.assertTrue((System.currentTimeMillis() - start) < 5_500);
    }

    @Test
    public void testShutdown2() {
        int countThread = 4;
        int countTask = 10;
        MyExecutorService pool = FactoryExecutorService.newFixedThreadPool(countThread);

        long start = System.currentTimeMillis();

        for (int i = 0; i < countTask; i++) {
            pool.execute(new TestTask(String.format("задача %d", i)));
        }

        pool.shutdown();

        Assert.assertTrue((System.currentTimeMillis() - start) < 15_500);
    }

    @Test
    public void testShutdownNow() {
        int countThread = 4;
        int countTask = 10;
        MyExecutorService pool = FactoryExecutorService.newFixedThreadPool(countThread);

        long start = System.currentTimeMillis();

        for (int i = 0; i < countTask; i++) {
            pool.execute(new TestTask(String.format("задача %d", i)));
        }

        pool.shutdownNow();

        Assert.assertTrue((System.currentTimeMillis() - start) < 5_500);
    }
}
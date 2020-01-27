public class FactoryExecutorService {

    static MyExecutorService newFixedThreadPool(int count) {
        return new MyFixedThreadPool(count);
    }

    static MyExecutorService newScalableThreadPool(int min, int max) {
        return new MyScalableThreadPool(min, max);
    }
}

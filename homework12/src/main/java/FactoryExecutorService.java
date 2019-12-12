public class FactoryExecutorService {

    static MyExecutorService newFixedThreadPool(int count) {
        return new MyFixedThreadPool(count);
    }
}

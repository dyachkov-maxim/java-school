public class Main {
    public static void main(String[] args) {

        MyExecutorService pool = FactoryExecutorService.newFixedThreadPool(4);
//        MyExecutorService pool = FactoryExecutorService.newScalableThreadPool(2, 6);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 4; i++) {
            pool.execute(new TestTask(String.format("задача %d", i)));
        }

        pool.shutdown();

        System.out.println("Затраченное время: " + (System.currentTimeMillis() - start));
    }
}

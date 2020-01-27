public class MyScalableThreadPool extends MyThreadPool {
    private final int min;
    private final int max;

    public MyScalableThreadPool(int min, int max) {
        super(min);

        this.min = min;
        this.max = max;
    }

    @Override
    protected void afterAppendTask() {
        if (queue.size() > threads.size() && threads.size() != max) {
            System.out.println("Хотим увеличить количество потоков");
            Thread t = createThread();
            t.start();
            threads.add(t);
        } else if (queue.size() < threads.size() && threads.size() != min) {
            System.out.println("Хотим уменьшить количество потоков");
            Thread t = threads.remove(threads.size() - 1);
            t.interrupt();
        }
    }
}

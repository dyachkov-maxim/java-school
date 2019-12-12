import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MyFixedThreadPool implements MyExecutorService {
    private final Queue<Runnable> queue;
    private final Thread[] threads;
    private boolean isRunning = true;

    public MyFixedThreadPool(int count) {
        queue = new LinkedList<>();
        threads = new Thread[count];

        for (int i = 0; i < count; i++) {
            threads[i] = new Thread(new TaskWorker());
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isRunning) {
            synchronized (queue) {
                queue.offer(runnable);
            }
        }
    }

    @Override
    public void shutdown() {
        isRunning = false;
    }

    @Override
    public int countWorkedThread() {
        return (int) Arrays.stream(threads).filter(t -> t.isAlive()).count();
    }

    @Override
    public void shutdownNow() {
        shutdown();
        for (Thread t : threads) {
            t.interrupt();
        }
    }

    private final class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask;
                synchronized (queue) {
                    nextTask = queue.poll();
                }

                if (nextTask != null) {
                    System.out.println("Thread #" + Thread.currentThread().getId() + " взял задачу " + nextTask.toString());
                    nextTask.run();
                }
            }
        }
    }
}

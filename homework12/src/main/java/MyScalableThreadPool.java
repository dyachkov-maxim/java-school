import java.util.*;

public class MyScalableThreadPool extends MyThreadPool {
    private final int min;
    private final int max;
    private List<Thread> threadList;

    public MyScalableThreadPool(int min, int max) {
        this.min = min;
        this.max = max;

        threadList = new LinkedList<>();
        for (int i = 0; i < min; i++) {
            Thread t = new Thread(new TaskWorker());
            threadList.add(t);
            t.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isRunning) {
            synchronized (queue) {
                queue.offer(runnable);
                if (queue.size() > threadList.size() && threadList.size() < max) {
                    Thread t = new Thread(new TaskWorker());
                    threadList.add(t);
                    System.out.println("count threads: " + threadList.size());
                    t.start();
                } else if (threadList.size() > queue.size() && threadList.size() > min) {
                    threadList.remove(threadList.size() - 1);
                    System.out.println("count threads: " + threadList.size());
                }
            }
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

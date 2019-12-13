import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class MyThreadPool implements MyExecutorService {
    protected final Queue<Runnable> queue;
    protected List<Thread> threads;
    protected boolean isRunning = true;

    public MyThreadPool(int count) {
        queue = new LinkedList<>();
        threads = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            threads.add(new Thread(new MyTack(queue)));
        }

        threads.forEach(t -> t.start());
    }

    @Override
    public void shutdown() {
        while (true) {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
            }
        }

        shutdownNow();
    }

    @Override
    public void shutdownNow() {
        isRunning = false;
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private class MyTack implements Runnable {
        private Queue<Runnable> queue;

        public MyTack(Queue<Runnable> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            Runnable runnable;
            while (isRunning && !Thread.currentThread().isInterrupted()) {
//                System.out.println("begin");

                synchronized (queue) {
                    runnable = queue.poll();
                }

                if (runnable != null) {
//                    System.out.println("runnable.before ()");
                    runnable.run();
//                    System.out.println("runnable.after()");
                }
//                System.out.println("end");
            }

            System.out.println("Поток " + Thread.currentThread().getId() + " завершился");
        }
    }

    public Thread createThread() {
        return  new Thread(new MyTack(queue));
    }

    @Override
    public void execute(Runnable runnable) {
        int count;
        synchronized (queue) {
            queue.add(runnable);
            count = queue.size();

            afterAppendTask();
        }

        System.out.println("Количество задач в очереди: " + count);
        System.out.println("Количество потоков исполнения: " + threads.size());
    }

    protected void afterAppendTask() {

    }
}

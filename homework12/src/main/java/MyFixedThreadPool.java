public class MyFixedThreadPool extends MyThreadPool {

    public MyFixedThreadPool(int count) {
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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public abstract class MyThreadPool implements MyExecutorService {
    protected final Queue<Runnable> queue;
    protected Thread[] threads;
    protected boolean isRunning = true;

    public MyThreadPool() {
        queue = new LinkedList<>();
    }

    @Override
    public void shutdown() {
        isRunning = false;
    }

    @Override
    public void shutdownNow() {
        shutdown();
        for (Thread t : threads) {
            t.interrupt();
        }
    }

    @Override
    public int countWorkedThread() {
        return (int) Arrays.stream(threads).filter(t -> t.isAlive()).count();
    }
}

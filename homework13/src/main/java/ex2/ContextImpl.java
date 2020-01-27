package ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ContextImpl implements Context {
    private Runnable[] runnables;
    private List<Future<?>> futures;
    private ExecutorService service;

    public ContextImpl(Runnable callback, Runnable... tasks) {
        runnables = tasks;
        futures = new ArrayList<>(runnables.length);
        ExecutorService service = null;
        //service = new Executors.newFixedThreadPool(3);
//
        for (Runnable runnable : runnables) {
            futures.add(service.submit(runnable));
        }
    }

    @Override
    public int getCompletedTaskCount() {
        return futures.stream()
                .filter(f->{f.isDone() == true})
                .count();
    }

    @Override
    public int getFailedTaskCount() {
        return 0;
    }

    @Override
    public int getInterruptedTaskCount() {
        return 0;
    }

    @Override
    public void interrupt() {
        futures.forEach(f->f.cancel(true));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

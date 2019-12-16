package ex2;

public class ExecutionManagerImpl implements ExecutionManager {
    private volatile Context context;
    @Override
    public Context execute(Runnable callback, Runnable...tasks) {

        if (context == null) {
            synchronized (this) {
                if (context == null) {
                    context = new ContextImpl();
                }
            }
        }

        return context;
    }
}

public interface MyExecutorService {
    void execute(Runnable runnable);
    void shutdown();
    void shutdownNow();
}

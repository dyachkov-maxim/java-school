import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyPool {
    private List<Thread> threadList;
    private MyCounter myCounter;

    public MyPool() {
        threadList = new ArrayList<>();
        myCounter = new MyCounter(0);
    }

    public static final int COUNT_MAX_THREAD = 5;

    boolean addBatch(String text) {
        // lock примитива синхронизации для контейнера threadList
        Iterator<Thread> iterator = threadList.listIterator();

        while (iterator.hasNext()) {
            Thread t = iterator.next();
            if (t.isAlive()) {
                iterator.remove();
            }
        }

        if (threadList.size() == COUNT_MAX_THREAD) {
            // unlock примитива синхронизации для контейнера threadList
            return false;
        }

        Thread t = new Thread(new CounterUnicWordsForPool(text, myCounter));
        threadList.add(t);
        t.start();

        // unlock примитива синхронизации для контейнера threadList
        return true;
    }

    public int agregate() {
        for (Thread th:threadList) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return myCounter.getCount();
    }
}

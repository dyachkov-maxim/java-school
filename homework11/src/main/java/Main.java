import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<String> list;
    public static final int COUNT_THREAD = 7;

    public Main() {
        list = new ArrayList<>();
        list.add("/home/max/IdeaProjects/java-school/homework11/src/main/resources/1.txt");
        list.add("/home/max/IdeaProjects/java-school/homework11/src/main/resources/2.txt");
        list.add("/home/max/IdeaProjects/java-school/homework11/src/main/resources/3.txt");
        list.add("/home/max/IdeaProjects/java-school/homework11/src/main/resources/4.txt");
        list.add("/home/max/IdeaProjects/java-school/homework11/src/main/resources/5.txt");
    }

    public static void main(String[] args) {

        Main app = new Main();
        app.oneThread();
        System.out.println("------------------------------------------------------------------");
        app.simpleMultithread();
        System.out.println("------------------------------------------------------------------");
        app.multithread();
        System.out.println("------------------------------------------------------------------");
    }

    public void oneThread() {
        long start = System.currentTimeMillis();

        int count = 0;
        CounterUnicWords counter = new CounterUnicWords();
        for (String path : list) {
            counter.setPath(path);
            count += counter.calc();
        }
        System.out.println("Count words: " + count);
        System.out.println("Time for oneThread: " + (System.currentTimeMillis() - start) + " ms.");
    }

    public void simpleMultithread() {
        long start = System.currentTimeMillis();
        List<Thread> threadList = new ArrayList<>();
        List<CounterUnicWords> counterUnicWordsList = new ArrayList<>();

        int index = 0;
        for (String path : list) {
            CounterUnicWords counterUnicWords = new CounterUnicWords();
            counterUnicWords.setPath(list.get(index++));
            counterUnicWordsList.add(counterUnicWords);
            threadList.add(new Thread(counterUnicWords));
        }

        threadList.forEach(thread -> thread.start());

        for (Thread th : threadList) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int count = 0;
        for (CounterUnicWords cuw : counterUnicWordsList) {
            count += cuw.getCount();
        }

        System.out.println("Count words: " + count);
        System.out.println("Time for simpleMultithread: " + (System.currentTimeMillis() - start) + " ms.");
    }

    public void multithread() {
        long start = System.currentTimeMillis();

        MyPool myPool = new MyPool();

        int count = 0;
        for (String path : list) {
            // читаем данные из файла до заполения буффера
            String buffer = "Professional1way2to3prepare4programming5contest6problem7890";
            while (true) {
                boolean ret = myPool.addBatch(buffer);
                if (ret) {
                    break;
                }
            }
        }

        int ret = myPool.agregate();

        System.out.println("Count words: " + ret);
        System.out.println("Time for multithread: " + (System.currentTimeMillis() - start) + " ms.");
    }
}

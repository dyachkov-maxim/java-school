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
        app.simpleMultithread();
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

        list.remove(0);
        List<Thread> threadList = new ArrayList<>();
        List<CounterUnicWords> counterUnicWordsList = new ArrayList<>();
        for (String path : list) {
            CounterUnicWords counterUnicWords = new CounterUnicWords();
            counterUnicWordsList.add(counterUnicWords);
            threadList.add(new Thread(counterUnicWords));
        }

        threadList.forEach(th -> th.start());


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
        System.out.println("Time for oneThread: " + (System.currentTimeMillis() - start) + " ms.");
    }
}

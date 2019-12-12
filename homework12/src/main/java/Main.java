import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String[] words = {"каждый", "охотник", "желает", "знать", "где", "сидит", "фазан"};
        List<Runnable> list = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            list.add(new MyRunnable(words[i % words.length], i + 1, 5_000));
        }

//        MyExecutorService service = FactoryExecutorService.newFixedThreadPool(4);
        MyExecutorService service = FactoryExecutorService.newScalableThreadPool(2, 6);
        for (Runnable r : list) {
            service.execute(r);
            Thread.sleep(300);
        }

        Thread.sleep(10000);
        service.shutdown();
    }
}

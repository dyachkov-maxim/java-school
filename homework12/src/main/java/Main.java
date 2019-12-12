import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String[] words = {"каждый", "охотник", "желает", "знать", "где", "сидит", "фазан"};
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            list.add(new MyRunnable(words[i], i + 1, 5_000));
        }

        MyExecutorService service = FactoryExecutorService.newFixedThreadPool(4);

        for (Runnable r : list) {
            service.execute(r);
        }

        service.shutdown();
    }
}

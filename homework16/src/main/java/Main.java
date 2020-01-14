import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map <Integer, String> map = new HashMap<>();
        for (int i = 0; i < 100_000; i++) {
            map.put(i, String.format("value %d", i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

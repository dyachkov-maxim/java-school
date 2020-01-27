import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        CountMap<Integer> map = new CountMapIml<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        System.out.println("Count: " + map.getCount(10));

        map.remove(10);
        System.out.println("Count: " + map.getCount(10));

    }
}

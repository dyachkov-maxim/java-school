import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MyStream<T> {
    private List<T> list = null;

    private MyStream(List<T> list) {
        this.list = list;
    }

    public static <T> MyStream<T> of(T... values) {
        List<T> arrayList = Arrays.asList(values);
        return new MyStream<T>(arrayList);
    }

    public MyStream<T> filter(Predicate<? super T> predicate) {
        list = list.stream().filter(predicate).collect(Collectors.toList());
        return this;
    }

    public MyStream<T> transform(Consumer<? super T> consumer) {
        list.forEach(consumer);
        return this;
    }

    public Map toMap(Supplier<? super T> supplier) {
        Map <Object, Object> map = new HashMap<>();
        return new HashMap<>();
    }

    @Override
    public String toString() {
        return "MyStream{" +
                "list=" + list +
                '}';
    }
}

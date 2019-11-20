package exercise1;

import java.util.Collections;
import java.util.List;

public class Runtime<T extends Number> implements java.util.concurrent.Callable<Double> {
    private final List<Integer> integers = Collections.emptyList();

    public List<T> numbers() {
        return Collections.emptyList();
    }

    public List<String> strings() {
        return Collections.emptyList();
    }

    @Override
    public Double call() {
        return 0d;
    }
}
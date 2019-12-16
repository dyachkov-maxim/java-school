package ex1;

import java.util.concurrent.Callable;

public class CallableImpl implements Callable <Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(5000);
        return 777;
    }
}

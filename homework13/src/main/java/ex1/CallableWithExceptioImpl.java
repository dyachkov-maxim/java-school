package ex1;

import java.util.concurrent.Callable;

public class CallableWithExceptioImpl implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        throw new Exception("test");
    }
}

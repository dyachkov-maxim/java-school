package ex1;

import java.util.concurrent.Callable;

public class Task<T> {
    private Callable<? extends T> callable;
    private volatile T value = null;
    private volatile static boolean isException = false;
    private static final CalculateException ce = new CalculateException("Ошибка при вычислении");

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() throws CalculateException {

        if (isException) {
            throw ce;
        }

        if (value == null) {
            synchronized (this) {
                if (value == null) {
                    try {
                        value = callable.call();
                    } catch (Exception e) {
                        isException = true;
                        throw ce;
                    }
                }
            }
        }

        return value;
    }

//    public T get() throws Exception {
//
//        guard.lock();
//
//        try {
//            if (value != null) {
//                System.out.println("Возврвщаем вычисленное зничение");
//                return value;
//            }
//
//            System.out.println("Вычисляем зничение");
//            value = callable.call();
//            return value;
//        } finally {
//            guard.unlock();
//        }
//    }
}

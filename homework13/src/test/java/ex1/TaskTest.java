package ex1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskTest {

    @Test
    public void get() {

        long start = System.currentTimeMillis();

        Task<Integer> task = new Task<>(new CallableImpl());
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(() -> {
                try {
                    Integer ret = task.get();
                    System.out.println(Thread.currentThread().getName() + " result get = " + ret);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            threads.add(thread);
        }

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long result = System.currentTimeMillis() - start;

        Assert.assertTrue(result <= 5_100);
    }

    @Test(expected = CalculateException.class)
    public void getWithException() throws CalculateException {
        throw new CalculateException("sdf");

//        Task<Integer> task = new Task<>(new CallableWithExceptioImpl());
//        List<Thread> threads = new ArrayList<>();
//
//        class RunnableException implements Runnable {
//            @Override
//            public void run() {
//                try {
//                    Integer ret = task.get();
//                    System.out.println(Thread.currentThread().getName() + " result get = " + ret);
//                } catch (CalculateException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        for (int i = 0; i < 10; i++) {
//
//            threads.add(new Thread(new RunnableException()));
//        }
//
//        threads.forEach(Thread::start);
//
//        for (Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

}
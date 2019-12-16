package ex1;

public class Main {
    public static void main(String[] args) {
        Task<Integer> task = new Task<>(new CallableImpl());

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Integer ret = task.get();
                    System.out.println(Thread.currentThread().getName() + " result get = " + ret);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

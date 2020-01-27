public class MyRunnable implements Runnable {
    private String text;
    private int id;
    private long sleep;

    public MyRunnable(String text, int id, long sleep) {
        this.text = text;
        this.id = id;
        this.sleep = sleep;
    }

    @Override
    public void run() {
        System.out.println(text);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

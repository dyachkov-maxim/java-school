public class TestTask implements Runnable {
    private String text;

    public TestTask(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        System.out.println(text);
        try {
            //System.out.println("Thread.before sleep " + Thread.currentThread().getName());
            Thread.sleep(5000);
            //System.out.println("Thread.after sleep " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
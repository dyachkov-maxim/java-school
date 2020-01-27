public class MyCounter {
    private int count;

    public MyCounter(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void append(int count) {
        // захват примитива синхронизации
        this.count += count;
    }
}

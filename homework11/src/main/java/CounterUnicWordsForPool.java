public class CounterUnicWordsForPool implements Runnable {
    private String text;
    private int count = 0;
    private MyCounter myCounter;

    public int getCount() {
        return count;
    }

    private boolean isDiapason(char ch, int begin, int end) {
        return ((int) ch >= begin) && ((int) ch <= end);
    }

    private boolean isLetter(char ch) {
        return isDiapason(ch, 65, 90) || isDiapason(ch, 97, 122);
    }

    private int calc0(String text) {
        boolean flag = false;
        for (int i = 0; i < text.length(); i++) {
            if (isLetter(text.charAt(i))) {
                if (!flag) {
                    flag = true;
                }
            } else {
                if (flag == true) {
                    count++;
                    flag = false;
                }
            }
        }

        if (flag == true) {
            count++;
        }

        return count;
    }

    public int calc() {
        System.out.println(String.format("In calc thread#%d", Thread.currentThread().getId()));
        return calc0(text);
    }

    @Override
    public void run() {
        myCounter.append(this.calc());
    }

    public CounterUnicWordsForPool(String text, MyCounter myCounter) {
        this.text = text;
        this.myCounter = myCounter;
    }
}

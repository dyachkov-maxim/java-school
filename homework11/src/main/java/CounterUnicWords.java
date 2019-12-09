public class CounterUnicWords implements Runnable {
    private String path;
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private boolean isDiapason(char ch, int begin, int end) {
        return ((int) ch >= begin) && ((int) ch <= end);
    }

    private boolean isLetter(char ch) {
        return isDiapason(ch, 65, 90) || isDiapason(ch, 97, 122);
    }

    private int calc0(String text) {
        count = 0;
        boolean flag = false;
        for (int i = 0; i < text.length(); i++) {
            if (isLetter(text.charAt(i))) {
                if (! flag) {
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
// чтение из файла
//        try {
//            //Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return calc0("Professional1way2to3prepare4programming5contest6problem7890");
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        this.calc();
    }
}

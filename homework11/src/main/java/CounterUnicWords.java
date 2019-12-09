import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

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
        return calc0(readAll());
    }

    @Override
    public void run() {
        this.calc();
    }

    private String readAll() {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}

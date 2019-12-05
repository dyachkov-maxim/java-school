import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Browser browser = new BrowserImpl();
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;

        do {
            System.out.print("Введите url: ");
            String url = scanner.nextLine();
            flag = browser.readContent(url);
        } while (! flag);
    }
}

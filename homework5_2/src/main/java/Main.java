import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TerminalImpl terminal = new TerminalImpl();
        int pin = 0;
        while (true) {
            System.out.print("Введите pin: ");
            pin = scanner.nextInt();
            terminal.getMenu(pin);
        }
    }
}

import java.util.Scanner;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final Account account;

    public TerminalImpl() {
        server = new TerminalServer();
        account = new Account();
    }

    @Override
    public int amountMoney() {
        return 0;
    }

    @Override
    public boolean putMoney(int amount) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean pullMoney(int amount) {

    }

    public void getMenu(int pin) {
        try {
            if (! account.isValid(pin)) {
                return;
            }
        } catch (AccountIsLockedException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println("1. state account");
        System.out.println("2. put cash");
        System.out.println("3. pull cash");
        System.out.print("Enter number menu: ");

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        try {
            switch (num) {
                case 1: printStateAccount(); break;
                case 2: menuPutMoney(scanner); break;
                case 3: menuPopMoney(scanner); break;
                default:;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printStateAccount() {
        System.out.println("Cash: " + server.getCash());
    }

    private int menuActionMoney(Scanner scanner) throws IllegalArgumentException {
        System.out.print("Enter amount : ");
        int num = scanner.nextInt();

        if (num <= 0) {
            throw new IllegalArgumentException("Количество добжно быть положительным числом!");
        }

        if (num % 100 != 0) {
            throw new IllegalArgumentException("Количество добжно быть кратно 100!");
        }

        return num;
    }

    private void menuPutMoney(Scanner scanner) throws IllegalArgumentException {
        int count  = menuActionMoney(scanner);
        server.pullCash(count);
    }

    private void menuPopMoney(Scanner scanner) throws IllegalArgumentException {
            int count  = menuActionMoney(scanner);
            server.popCash(count);
    }
}

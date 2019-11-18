public class TerminalServer {
    private int cash = 100_000;

    public void popCash(int amount) throws IllegalArgumentException {
        if (amount > cash) {
            throw new IllegalArgumentException("Запрашиваемая сумма больше чем остаток.");
        }

        cash -= amount;
    }

    public void pullCash(int amount){
        cash += amount;
    }

    public int getCash() {
        return cash;
    }
}

interface Terminal {
    int amountMoney();
    boolean putMoney(int amount) throws IllegalArgumentException;
    boolean pullMoney(int amount) throws IllegalArgumentException;
}

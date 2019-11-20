package exercise3;

import exercise4.MyCachedMethod;

public class CalculatorImpl implements Calculator {
    @Override
    @MyCachedMethod
    public int calculate(int value) {
        return (int)Math.pow((double) value, (double) value);
    }
}

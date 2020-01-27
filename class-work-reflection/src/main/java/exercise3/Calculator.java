package exercise3;

import java.lang.reflect.InvocationTargetException;

public interface Calculator {
    int calculate(int value) throws InvocationTargetException, IllegalAccessException;
}

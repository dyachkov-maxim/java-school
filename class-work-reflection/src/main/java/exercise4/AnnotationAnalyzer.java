package exercise4;

import exercise3.Calculator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnnotationAnalyzer implements Calculator {
    private Calculator calculator = null;
    private Map<Integer, Integer> map = new HashMap<>();

    public AnnotationAnalyzer(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calculate(int value) throws InvocationTargetException, IllegalAccessException {
        Class <?> clazz = calculator.getClass();
        Integer result = 0;

        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(MyCachedMethod.class)) {
                if (map.containsKey(value)) {
                    result = map.get(value);
                    System.out.println("from cached (" + value + ")");
                } else {
                    result = (Integer) method.invoke(calculator, value);
                    map.put((Integer) value, result);
                    System.out.println("calculate (" + value + ")");
                }
            }
        }

        return result;
    }
}

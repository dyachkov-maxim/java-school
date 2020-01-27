package exercise3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InvocationHandlerCalculator implements InvocationHandler {

    private Object obj;
    private Map<Integer, Integer> map = new HashMap<>();

    public InvocationHandlerCalculator(CalculatorImpl calculator) {
        this.obj = calculator;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Integer result = 0;
        if (map.containsKey(objects[0])) {
            result = map.get(objects[0]);
            System.out.println("from cached (" + objects[0] + ")");
        } else {
            result = (Integer) method.invoke(obj, objects);
            map.put((Integer) objects[0], result);
            System.out.println("calculate (" + objects[0] + ")");
        }

        return result;
    }
}

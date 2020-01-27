package exercise1;

import java.lang.reflect.Type;

public class StudyClassRuntime {
    public void method() throws NoSuchMethodException, NoSuchFieldException {
        System.out.println(Runtime.class.getMethod("numbers").getGenericReturnType().getTypeName());
        System.out.println(Runtime.class.getMethod("strings").getGenericReturnType().getTypeName());
        System.out.println(Runtime.class.getMethod("call").getReturnType().getName());
        System.out.println(Runtime.class.getDeclaredField("integers").getGenericType().getTypeName());

        for (Type type : Runtime.class.getGenericInterfaces()) {
            System.out.println(type.getTypeName());
        }

        System.out.println(Runtime.class.toGenericString());
    }
}

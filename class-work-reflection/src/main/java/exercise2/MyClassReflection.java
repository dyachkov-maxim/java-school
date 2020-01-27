package exercise2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MyClassReflection {

    public void printAllMethods() {
        Class<?> clazz = MyClass.class;

        while (clazz != null) {

            System.out.println(String.format("Класс [ %s ] декларирует следующие методы:", clazz));

            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println(" - " + method.getName());
            }

            System.out.println();
            clazz = clazz.getSuperclass();
        }
    }

    public void printAllGetterMethods() {
        Method[] methods = MyClass.class.getDeclaredMethods();

        for (Method method : methods) {
            if ((Modifier.isPublic(method.getModifiers())) &&
                    (!method.getReturnType().toString().contains("void")) &&
                    (method.getName().contains("get"))) {
                System.out.println(method.getName());
            }
        }
    }

    public void isStringConstantsValueEqualsName() throws IllegalAccessException {
        Field[] fields = MyClass.class.getDeclaredFields();
        int countStringConstants = 0;
        int countEqualsNameStringConstants = 0;

        for (Field field : fields) {
            if (Modifier.isFinal(field.getModifiers()) &&
                    (Modifier.isStatic(field.getModifiers())) &&
                    (field.getType().equals(String.class))) {

                countStringConstants++;
                if (field.getName().equals(field.get(MyClass.class).toString())) {
                    System.out.println(field.getName());
                    countEqualsNameStringConstants++;
                }
            }
        }

        System.out.println(String.format("%d из %d имен строковых констант равны своему значению",
                countEqualsNameStringConstants, countStringConstants));
    }
}

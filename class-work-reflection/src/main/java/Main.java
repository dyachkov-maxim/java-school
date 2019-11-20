import exercise1.StudyClassRuntime;
import exercise2.MyClassReflection;
import exercise3.Calculator;
import exercise3.CalculatorImpl;
import exercise3.InvocationHandlerCalculator;
import exercise4.AnnotationAnalyzer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;


public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {

        // задание #1
        StudyClassRuntime studyClassRuntime = new StudyClassRuntime();
        System.out.println("---------------------- задание 1 ------------------------------");
        System.out.println("----------------------------------------------------");
        studyClassRuntime.method();
        System.out.println("----------------------------------------------------");

        // задание #2
        MyClassReflection myClassReflection = new MyClassReflection();
        System.out.println("---------------------- задание 2 ------------------------------");
        System.out.println("----------------------------------------------------");
        System.out.println("Вывести все методы класса, в том числе и родительские (вне зависимости от модификатора).");
        System.out.println("----------------------------------------------------");
        myClassReflection.printAllMethods();
        System.out.println("----------------------------------------------------");
        System.out.println("Вывести все геттеры класса.");
        System.out.println("----------------------------------------------------");
        myClassReflection.printAllGetterMethods();
        System.out.println("----------------------------------------------------");
        System.out.println("Проверить что все String константы имеют значение = их имени");
        System.out.println("----------------------------------------------------");
        myClassReflection.isStringConstantsValueEqualsName();
        System.out.println("----------------------------------------------------");

        // задание #3
        System.out.println("---------------------- задание 3 ------------------------------");
        System.out.println("Реализовать кэширующий прокси.");
        System.out.println("----------------------------------------------------");
        CalculatorImpl calculator = new CalculatorImpl();
        Calculator calculatorProxy = (Calculator) Proxy.newProxyInstance(
                CalculatorImpl.class.getClassLoader(),
                CalculatorImpl.class.getInterfaces(),
                new InvocationHandlerCalculator(calculator));

        calculatorProxy.calculate(1);
        calculatorProxy.calculate(1);
        calculatorProxy.calculate(2);
        calculatorProxy.calculate(1);
        calculatorProxy.calculate(2);
        calculatorProxy.calculate(2);
        calculatorProxy.calculate(2);

        // задание #4
        System.out.println("---------------------- задание 4 ------------------------------");
        System.out.println("Реализовать кэширующий прокси через аннотацию.");
        System.out.println("----------------------------------------------------");
        AnnotationAnalyzer calculatorProxyAnnotation = new AnnotationAnalyzer(calculator);
        calculatorProxyAnnotation.calculate(1);
        calculatorProxyAnnotation.calculate(1);
        calculatorProxyAnnotation.calculate(2);
        calculatorProxyAnnotation.calculate(1);
        calculatorProxyAnnotation.calculate(2);
        calculatorProxyAnnotation.calculate(2);
        calculatorProxyAnnotation.calculate(2);
    }
}

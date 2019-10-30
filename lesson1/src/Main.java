import com.sbt.javaschool.lesson1.MyFirstJavaClass;

public class Main {
    public static void main(String[] args) {
        MyFirstJavaClass myFirstJavaClass = new MyFirstJavaClass();
        myFirstJavaClass.printHello();

        if (args.length > 0) {
            myFirstJavaClass.print(args[0]);
        }
    }
}

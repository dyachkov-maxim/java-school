import com.sun.org.apache.xerces.internal.xs.XSTerm;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MyStream<Person> stream = MyStream.of(
                new Person("Ivan", 55),
                new Person("Oleg", 15),
                new Person("Anton", 30))
                .filter(p -> p.getAge() > 30)
                .transform(p -> new Person(p.getAge() + 30));
        System.out.println(stream);

        Map map = stream.toMap();

        System.out.println("--------------------------------------------------");
        MyStream<Person> stream1 = stream
                .filter(p -> p.getAge() > 30)
                .transform(p -> new Person(p.getAge() + 30));


        System.out.println(stream1);
    }
}

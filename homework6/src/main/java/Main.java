public class Main {
    public static void main(String[] args) {
        MyClass from = new MyClass(1, 5, 777);
        MyClass to = new MyClass();

        System.out.println("---------------- before call method -----------------");
        print(from, to);

        System.out.println("---------------------------------");
        BeanUtils.assign(to, from);
        System.out.println("---------------------------------");

        System.out.println("---------------- after call method -----------------");
        print(from, to);
        System.out.println("---------------------------------");
    }

    static void print(Object from, Object to) {
        System.out.println("Object from: " + from.toString());
        System.out.println("---------------------------------");
        System.out.println("Object to: " + to.toString());
        System.out.println("---------------------------------");
        System.out.println("Object 'from' equals 'to'? : " + from.equals(to));
    }


}

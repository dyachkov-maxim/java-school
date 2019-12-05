import java.util.Objects;

public class MyClass {
    private int a;
    private int b;
    private int c;

    public MyClass(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public MyClass() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
    }

    public int getA() {
        return a;
    }

    private void getB() {
    }

    private int getC() {
        return c;
    }

    public void setA(int a) {
        this.a = a;
    }

    private void setB() {
        b = 0;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return String.format("[a = %d], [b = %d], [c = %d]", a, b, c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return a == myClass.a &&
                b == myClass.b &&
                c == myClass.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}

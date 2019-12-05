import java.util.Map;

public class ServicePresent extends Service {
    public ServicePresent() {
    }

    public ServicePresent(Person person) {
        super(person);
    }

    public void add(Person from, String present) {
        person.addPresent(from, present);
    }

    public void add(Person from, Person to, String present) {
        setPerson(to);
        add(from, present);
    }

    public void view() {
        long i = 0;
        for (Map.Entry<Object, String> pair : person.getPresents().entrySet()) {
            i++;
            System.out.println( i  + ") " + pair.getValue());
        }

    }

    public void view(Person from) {
        setPerson(from);
        view();
    }
}

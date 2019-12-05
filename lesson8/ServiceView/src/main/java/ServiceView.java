public class ServiceView extends Service {
    public ServiceView() {
    }

    public ServiceView(Person person) {
        super(person);
    }

    public void info() {
        System.out.println(person);
    }

    public void friends() {
        person.getFriends().stream().forEach(System.out::println);
    }

    public void info(Person person) {
        setPerson(person);
        info();
    }

    public void friends(Person person) {
        setPerson(person);
        friends();
    }
}

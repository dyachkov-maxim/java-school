public class ServiceFinder extends Service {

    public ServiceFinder() {
    }

    public ServiceFinder(Person person) {
        super(person);
    }

    Person find(String name) {
        // поиск по БД
        return new Person(1, "John");
    }

    public void add(Person person) {
        // проверки
        person.addFriend(person);
    }
}

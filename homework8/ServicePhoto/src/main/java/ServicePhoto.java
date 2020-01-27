public class ServicePhoto extends Service {
    public ServicePhoto() {
    }

    public ServicePhoto(Person person) {
        super(person);
    }

    public boolean load(String url) {
        person.setUrlPhoto(url);

        return true;
    }

    public boolean load(Person person, String url) {
        setPerson(person);
        return load(url);
    }

    public void view() {
        System.out.println(person.getUrlPhoto());
    }
    public void view(Person person) {
        setPerson(person);
        view();
    }

}

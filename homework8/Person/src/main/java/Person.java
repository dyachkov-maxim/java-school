import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
    private int id;
    private String name;
    private Map<Object, String> presents;
    private String urlPhoto;
    private List<Person> friends;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
        presents = new HashMap<>();
        friends = new ArrayList<>();
    }

    public void addPresent(Person from, String name) {
        presents.putIfAbsent(from, name);
    }

    public Map<Object, String> getPresents() {
        return presents;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public String getName() {
        return name;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;

    }

    public List<Person> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", presents=" + presents +
                ", urlPhoto='" + urlPhoto + '\'' +
                ", friends=" + friends +
                '}';
    }

    public void addFriend(Person person) {
        friends.add(person);
    }
}

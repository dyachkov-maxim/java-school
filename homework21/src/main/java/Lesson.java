import java.time.LocalDate;

public class Lesson {
    private int id;
    private String name;
    private LocalDate date;

    public Lesson(int id, String name, LocalDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

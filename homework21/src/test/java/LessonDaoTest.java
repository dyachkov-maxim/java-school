import org.h2.jdbcx.JdbcDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

public class LessonDaoTest {
    private JdbcDataSource ds = new JdbcDataSource();
    private LessonDao ld;
    private String SQL_CREATE = "CREATE TABLE Lessons(" +
            "    id INT," +
            "    name VARCHAR(30)," +
            "    date DATE," +
            "    PRIMARY KEY (id)" +
            "                    );";

    private String SQL_INSERT = "INSERT INTO Lessons VALUES (" +
            "1, 'Math', '2020-05-01')," +
            "(2, 'Russian', '2020-05-01')," +
            "(3, 'English', '2020-05-01')," +
            "(4, 'Geomertry', '2020-05-01');";


    public LessonDaoTest() {
        ds.setUser("max");
        ds.setURL("jdbc:h2:~/test");
        ld = new LessonDao(ds);
    }

    @Before
    public void setUp() throws Exception {
        try {
            Statement statement = ds.getConnection().createStatement();
            statement.execute("DROP TABLE IF EXISTS Student_Visits;");
            statement.execute("DROP TABLE IF EXISTS Lessons;");
            statement.executeUpdate(SQL_CREATE);
            statement.executeUpdate(SQL_INSERT);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void get() {
        Optional<Lesson> lesson = ld.get(3);
        Assert.assertTrue(lesson.isPresent());
        Assert.assertEquals(lesson.get().getName(), "English");
    }

    @Test
    public void getAll() {
        Assert.assertTrue(ld.getAll().size() == 4);
    }

    @Test
    public void f(){

    }

    @Test
    public void save() {
        Lesson lesson = new Lesson(5, "BGD", LocalDate.now());
        ld.save(lesson);
        Optional<Lesson> optionalLesson = ld.get(5);
        Assert.assertTrue(ld.getAll().size() == 5);
        Assert.assertTrue(optionalLesson.isPresent());
        Assert.assertEquals(optionalLesson.get().getName(), "BGD");
    }

    @Test
    public void update() {
        Lesson lesson = new Lesson(5, "BGD", LocalDate.now());
        ld.save(lesson);
        String[] a = {"Biology"};
        ld.update(5, a);
        Optional<Lesson> optionalLesson = ld.get(5);
        Assert.assertTrue(ld.getAll().size() == 5);
        Assert.assertTrue(optionalLesson.isPresent());
        Assert.assertEquals(optionalLesson.get().getName(), "Biology");
    }

    @Test
    public void delete() {
        Lesson lesson = new Lesson(5, "BGD", LocalDate.now());
        ld.save(lesson);
        Optional<Lesson> optionalLesson = ld.get(5);
        Assert.assertTrue(ld.getAll().size() == 5);
        Assert.assertTrue(optionalLesson.isPresent());

        ld.delete(5);
        optionalLesson = ld.get(5);
        Assert.assertTrue(ld.getAll().size() == 4);
        Assert.assertFalse(optionalLesson.isPresent());
    }
}
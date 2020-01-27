import org.h2.jdbcx.JdbcDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class StudentDaoTest {
    private JdbcDataSource ds = new JdbcDataSource();
    private StudentDao studentDao;

    private String SQL_CREATE = "CREATE TABLE Students(" +
            "    id INT," +
            "    name VARCHAR(30)," +
            "    PRIMARY KEY (id)" +
            "                     );";

    private String SQL_INSERT = "INSERT INTO Students VALUES " +
            "(1, 'Ivan')," +
            "(2, 'Anton')," +
            "(3, 'Ignat')," +
            "(4, 'Seva');";

    public StudentDaoTest() {
        ds.setUser("max");
        ds.setURL("jdbc:h2:~/test");
        studentDao = new StudentDao(ds);
    }

    @Before
    public void setUp() throws Exception {
        try {
            Statement statement = ds.getConnection().createStatement();
            statement.execute("DROP TABLE IF EXISTS Student_Visits;");
            statement.execute("DROP TABLE IF EXISTS Students;");
            statement.executeUpdate(SQL_CREATE);
            statement.executeUpdate(SQL_INSERT);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void get() {
        Optional<Student> student = studentDao.get(3);
        Assert.assertTrue(student.isPresent());
        Assert.assertEquals(student.get().getName(), "Ignat");
    }

    @Test
    public void getAll() {
        Assert.assertTrue(studentDao.getAll().size() == 4);
    }

    @Test
    public void save() {
        Student student = new Student(5, "John");
        studentDao.save(student);
        Optional<Student> optionalLesson = studentDao.get(5);
        Assert.assertTrue(studentDao.getAll().size() == 5);
        Assert.assertTrue(optionalLesson.isPresent());
        Assert.assertEquals(optionalLesson.get().getName(), "John");
    }

    @Test
    public void update() {
        Student student = new Student(5, "John");
        studentDao.save(student);
        String[] a = {"John II"};
        studentDao.update(5, a);
        Optional<Student> optionalLesson = studentDao.get(5);
        Assert.assertTrue(studentDao.getAll().size() == 5);
        Assert.assertTrue(optionalLesson.isPresent());
        Assert.assertEquals(optionalLesson.get().getName(), "John II");
    }

    @Test
    public void delete() {
        Student student = new Student(5, "John");
        studentDao.save(student);
        Optional<Student> optionalLesson = studentDao.get(5);
        Assert.assertTrue(studentDao.getAll().size() == 5);
        Assert.assertTrue(optionalLesson.isPresent());

        studentDao.delete(5);
        optionalLesson = studentDao.get(5);
        Assert.assertTrue(studentDao.getAll().size() == 4);
        Assert.assertFalse(optionalLesson.isPresent());
    }
}
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

public class StudentVisitsDaoTest {

    private JdbcDataSource ds = new JdbcDataSource();
    private StudentVisitsDao dao;

    private String SQL_CREATE = "CREATE TABLE Student_Visits(" +
            "    student_id INT NOT NULL," +
            "    lesson_id INT NOT NULL," +
            "    is_delete BOOLEAN," +
            "    FOREIGN KEY (student_id) REFERENCES Students(id)," +
            "    FOREIGN KEY (lesson_id) REFERENCES Lessons(id)" +
            "                          );";

    private String SQL_INSERT = "INSERT INTO Student_Visits VALUES " +
            "(1, 1, false)," +
            "(1, 2, false)," +
            "(1, 3, false)," +
            "(1, 4, false)," +
            "(2, 1, false);";


    public StudentVisitsDaoTest() {
        ds.setUser("max");
        ds.setURL("jdbc:h2:~/test");
        dao = new StudentVisitsDao(ds);
    }

    @Before
    public void setUp() throws Exception {
        try {
            Statement statement = ds.getConnection().createStatement();
            statement.execute("DROP TABLE IF EXISTS Student_Visits;");
            statement.executeUpdate(SQL_CREATE);
            statement.executeUpdate(SQL_INSERT);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void get() {
        dao.get(1);
    }

    @Test
    public void getAll() {
        Assert.assertTrue(dao.getAll().size() == 5);
    }

    @Test
    public void save() {
        StudentVisits sv = new StudentVisits(2, 2);
        dao.save(sv);
        Assert.assertTrue(dao.getAll().size() == 6);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() {
        String[] a = {"John II"};
        dao.update(1, a);
    }

    @Test
    public void delete() {
        StudentVisits studentVisits = new StudentVisits(2, 2);
        dao.save(studentVisits);
        Assert.assertTrue(dao.getAll().size() == 6);

        dao.delete(5);
        Assert.assertTrue(dao.getAll().size() == 5);
    }
}
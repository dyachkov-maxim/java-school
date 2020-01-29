import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDao implements Dao<Student> {

    private Connection connection;

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM Students;";
    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT * FROM Students WHERE id = ?;";
    //language=SQL
    private final String SQL_INSERT = "INSERT INTO Students VALUES (?, ?);";
    //language=SQL
    private final String SQL_DELETE_BY_ID = "DELETE FROM Students WHERE id = ?";
    //language=SQL
    private final String SQL_UPDATE_BY_ID = "UPDATE Students SET name = ? WHERE id = ?";

    public StudentDao(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Student> get(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                return  Optional.of(new Student((int)id, name));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Student> getAll() {
        try {
            List<Student> students = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Student student = new Student(id, name);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(long id, String[] params) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setString(1, params[0]);
            statement.setLong(2, id);
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1, id);
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

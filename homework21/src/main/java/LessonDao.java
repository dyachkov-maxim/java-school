import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LessonDao implements Dao<Lesson> {

    private Connection connection;
    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM Lessons;";
    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT * FROM Lessons WHERE id = ?;";
    //language=SQL
    private final String SQL_INSERT = "INSERT INTO Lessons VALUES (?, ?, ?);";
    //language=SQL
    private final String SQL_DELETE_BY_ID = "DELETE FROM Lessons WHERE id = ?";
    //language=SQL
    private final String SQL_UPDATE_BY_ID = "UPDATE Lessons SET name = ? WHERE id = ?";

    public LessonDao(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Lesson> get(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                return  Optional.of(new Lesson((int)id, name, LocalDate.now()));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Lesson> getAll() {
        try {
            List<Lesson> lessons = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Lesson lesson = new Lesson(id, name, LocalDate.now());
                lessons.add(lesson);
            }
            return lessons;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Lesson lesson) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setInt(1, lesson.getId());
            statement.setString(2, lesson.getName());
            statement.setDate(3, Date.valueOf(lesson.getDate()));
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

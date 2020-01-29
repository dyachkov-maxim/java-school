import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentVisitsDao implements Dao<StudentVisits> {
    private Connection connection;

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM Student_Visits WHERE IS_DELETE = FALSE;";
    //language=SQL
    private final String SQL_INSERT = "INSERT INTO Student_Visits VALUES (?, ?, ?);";
    //language=SQL
    private final String SQL_DELETE = "UPDATE Student_Visits SET is_delete = TRUE WHERE student_id = ? AND lesson_id = ?;";


    public StudentVisitsDao(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<StudentVisits> get(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<StudentVisits> getAll() {
        try {
            List<StudentVisits> studentVisits = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                int lessonId = resultSet.getInt("lesson_id");
                boolean isDelete = resultSet.getBoolean("is_delete");

                if (! isDelete) {
                    StudentVisits student = new StudentVisits(studentId, lessonId);
                    studentVisits.add(student);
                }
            }
            return studentVisits;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(StudentVisits studentVisits) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setInt(1, studentVisits.getStudent_id());
            statement.setInt(2, studentVisits.getLesson_id());
            statement.setBoolean(3, false);
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(long id, String[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, 2);
            statement.setInt(2, 1);
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "max", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT s.name, l.name, l.date\n" +
                    "        FROM Students s\n" +
                    "        JOIN Student_Visits sv on s.id = sv.student_id\n" +
                    "        JOIN Lessons l on sv.lesson_id = l.id;");

            while (resultSet.next()) {
                String item = resultSet.getString(1) +
                        " | " + resultSet.getString(2) +
                        "| " + resultSet.getDate(3).toString();
                System.out.println(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

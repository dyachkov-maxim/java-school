import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    @Test
    public void connectionSuccess() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "max", "");
        Assert.assertFalse(conn.isClosed());
        Assert.assertTrue(conn.isValid(1));
        conn.close();
    }

    @Test(expected = SQLException.class)
    public void connectionError() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "undefined", "");
        Assert.assertTrue(conn.isClosed());
        Assert.assertFalse(conn.isValid(1));
        conn.close();
    }
}

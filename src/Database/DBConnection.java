package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection
{
    public static Connection connection() throws Exception
    {
        String url = "jdbc:postgresql://localhost:5432/attendance_db";
        String user = "postgres";
        String password = "0404";
        return DriverManager.getConnection(url, user, password);
    }
}

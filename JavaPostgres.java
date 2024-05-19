import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaPostgres {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/testdb", // URL of the database
                    "testuser", // Database user
                    "testpass" // Database password
            );
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT version()");

            while (resultSet.next()) {
                System.out.println("PostgreSQL Version: " + resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


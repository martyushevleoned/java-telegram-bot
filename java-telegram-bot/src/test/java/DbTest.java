import junit.framework.TestCase;
import org.example.database.PojoBuilder;
import org.example.tables.User;

import java.sql.*;
import java.util.Optional;

public class DbTest extends TestCase {
    public void testQuery() {

        int id = 1;

        Optional<User> user = PojoBuilder.getUser(id);

        if (user.isPresent())
            System.out.println(user.get());
    }

    public void testJDBC() {
        String url = "jdbc:postgresql://localhost:5432/telegram";
        String user = "postgres";
        String pass = "testtest";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from \"User\"");

            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1) + "\t");
                System.out.print(resultSet.getString(2) + "\t");
                System.out.print(resultSet.getString(3) + "\t");
                System.out.println();
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

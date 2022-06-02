import java.sql.*;

public class DBController {
    private static DBController dbController = new DBController();

    private Connection connection;
    private Statement statement;

    public static DBController getInstance(){
        return dbController;
    }

    private DBController() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
            statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ResultSet executeQuery(String str) throws SQLException {
        return statement.executeQuery(str);
    }

    public void executeUpdate(String str) throws SQLException {
        statement.executeUpdate(str);
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}

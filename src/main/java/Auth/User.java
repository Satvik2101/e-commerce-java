package Auth;

import java.sql.*;

public class User {
    public final String username;
    final String sellerName;

    final String password;

    public User(String username,  String password,String sellerName) throws SQLException {
        this.username = username;
        this.password = password;
        this.sellerName = sellerName;

    }

    public void writeToDatabase() throws SQLException {
        String jdbcUrl = "jdbc:sqlite:database.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);

        String createTableQuery = "create table if not exists users " +
                "(username TEXT PRIMARY KEY,password TEXT, sellerName TEXT);";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createTableQuery);

        String insertCommand = "insert into users values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertCommand);
        preparedStatement.setString(1, username);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,sellerName);
        preparedStatement.executeUpdate();
        connection.close();
    }

}
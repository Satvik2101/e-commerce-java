import java.sql.*;

class User {
    final String username;
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
    }

}

public class Auth {
    User user;
    boolean isAuthenticated = false;
    public boolean tryRegister(String username) {
        try {
            ResultSet set = getUsernameResultSet(username);
            return !set.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ResultSet getUsernameResultSet(String username) throws SQLException {
        String jdbcUrl = "jdbc:sqlite:database.db";

        Connection connection = DriverManager.getConnection(jdbcUrl);

        String createTableQuery = "create table if not exists users " +
                "(username TEXT PRIMARY KEY,password TEXT, sellerName TEXT);";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createTableQuery);
        String checkIfExists = "select * from users where (username=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(checkIfExists);
        preparedStatement.setString(1, username);
        return preparedStatement.executeQuery();
    }

    public void register(String username,String password,String sellerName){
        try {
            user = new User(username,password,sellerName);
            user.writeToDatabase();
            isAuthenticated= true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("REGISTRATION SUCCESSFUL");
    }

    public boolean login(String username,String password){
        try {
            ResultSet usernameSet = getUsernameResultSet(username);
            if (!usernameSet.next()){
                System.out.println("USERNAME NOT FOUND");
                return false;
            }
            else if (!usernameSet.getString("password").equals(password)){
                System.out.println("WRONG PASSWORD");
                return false;
            }
            else{
                user = new User(username,password,usernameSet.getString("sellerName"));
                isAuthenticated= true;
                System.out.println("LOGIN SUCCESSFUL");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
}
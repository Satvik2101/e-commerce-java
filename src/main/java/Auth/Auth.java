package Auth;

import java.sql.*;


public class Auth {
    public User user;
    public boolean isAuthenticated = false;
    Connection connection;
    public Auth(){
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
            connection.close();

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
                connection.close();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
}
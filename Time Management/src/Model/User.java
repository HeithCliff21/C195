/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.DataBase;

 
/**
 *
 * @author Heith
 */
public class User {
    private String username;
    private int userId;
    
     public String getUsername() {
        return username;
    }
     public int getUserId() {
         return userId;
     }

    public void setUsername(String username) {
        this.username = username;
}
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
}
    
  public static Boolean login(String userName, String password) throws SQLException, Exception {
        try {
            
            Statement statement = DataBase.getConnection().createStatement();                   
            String sqlLogin = "SELECT * FROM user WHERE userName='" + userName + "' AND password='" + password + "'";
            ResultSet loginAttempt = statement.executeQuery(sqlLogin);
            if(loginAttempt.next()) {
                currentUser = new User();
                currentUser.setUsername(loginAttempt.getString("userName"));
                currentUser.setUserId(loginAttempt.getInt("userId"));
                statement.close();
                //Logger.log(username, true);
                return true;
            } else {
                //Logger.log(username, false);
                return false;
            }
        } catch(SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
    
}
}
}

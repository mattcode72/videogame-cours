package dao;

import bean.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public Boolean ajoutUser(User user) {
        try {
            Database.Connect();
            PreparedStatement sql = Database.connexion.prepareStatement("INSERT INTO users (pseudo,password,email,is_admin)"
                    + " VALUES (?,?,?,?)");
            sql.setString(1, user.getPseudo());
            sql.setString(2, user.getPassword());
            sql.setString(3, user.getEmail());
            sql.setBoolean(4, user.isAdmin());

            sql.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String mail, String password) {
        Database.Connect();

        try {
            PreparedStatement prepare = Database.connexion.prepareStatement("select * from users where email = ?");
            prepare.setString(1, mail);
            ResultSet rs = prepare.executeQuery();
            if(rs.next()) {
                if(BCrypt.checkpw(password, rs.getString("pwd"))) {
                    return new User(rs.getInt("id"),rs.getString("pseudo"), rs.getString("password"),rs.getString("email"), rs.getBoolean("is_admin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

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
            sql.setBoolean(4, false);

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
                if(BCrypt.checkpw(password, rs.getString("password"))) {
                    return new User(rs.getInt("id"),rs.getString("pseudo"), rs.getString("password"),rs.getString("email"), rs.getBoolean("is_admin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkMailExist(String email) {
        try {
            Database.Connect();
            PreparedStatement sql = Database.connexion.prepareStatement("select * from users "
                    + "WHERE users.email=?");
            sql.setString(1, email);
            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean edit(User user) {
        try {
            Database.Connect();
            PreparedStatement sql = Database.connexion.prepareStatement(" UPDATE users SET"
                    + " pseudo = ?, email=? "
                    + " WHERE id= ?");

            sql.setString(1, user.getPseudo());
            sql.setString(2, user.getEmail());
            sql.setInt(3, user.getId());

            sql.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findById(int id) {
        try {


            PreparedStatement sql = Database.connexion.prepareStatement("select * from users WHERE users.id=?");

            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("pseudo"), rs.getString("password"),
                        rs.getString("email"), rs.getBoolean("is_admin"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(User user, String newPassword) {
        try {
            Database.Connect();
            PreparedStatement sql = Database.connexion.prepareStatement(" UPDATE users SET"
                    + " password = ?"
                    + " WHERE id= ?");

            sql.setString(1, newPassword);
            sql.setInt(2, user.getId());

            sql.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(User user) {
        try {
            Database.Connect();
            PreparedStatement sql = Database.connexion.prepareStatement(" DELETE FROM users"
                    + " WHERE id= ?");

            sql.setInt(1, user.getId());

            sql.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deactivate(User user, String password) {
        try {
            Database.Connect();
            PreparedStatement sql = Database.connexion.prepareStatement(
                    "select * from users where email=?");
            sql.setString(1, user.getEmail());

            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                if (BCrypt.checkpw(password, rs.getString("pwd"))) {

                    sql = Database.connexion.prepareStatement("DELETE FROM users"
                            + " WHERE id= ?");
                    sql.setInt(1, user.getId());

                    sql.execute();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

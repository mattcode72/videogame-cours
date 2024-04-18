package dao;

import bean.Order;
import bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDAO {

    public Order findById(int id) {
        UserDAO userDAO = new UserDAO();

        try {

            PreparedStatement sql = Database.connexion.prepareStatement("select * from order WHERE order.id=?");

            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                User user = userDAO.findById(rs.getInt("user_id"));

                return new Order(rs.getInt("id"), rs.getDate("date"), user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Order getCurrentOrder(User user) {
        UserDAO userDAO = new UserDAO();

        try {
            // On récupère la dernière commande de l'utilisateur connecté
            PreparedStatement sql = Database.connexion.prepareStatement("SELECT top 1 * FROM orders where orders.user_id = ? order by id desc");

            sql.setInt(1, user.getId());

            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                return new Order(rs.getInt("id"), rs.getDate("date"), user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void createOrder(User user) {
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("INSERT INTO orders (date, user_id) VALUES (now(), ?)");

            sql.setInt(1, user.getId());

            sql.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package dao;

import bean.Order;
import bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDAO {

    public Order findById(int id) {
        UserDAO userDAO = new UserDAO();

        try {

            PreparedStatement sql = Database.connexion.prepareStatement("select * from order WHERE order.id=?");

            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                User user = userDAO.findById(rs.getInt("users_id"));

                return new Order(rs.getInt("id"), rs.getDate("date"), user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Order getCurrentOrder(User user) {
        try {
            // On récupère la dernière commande en cours de l'utilisateur connecté
            PreparedStatement sql = Database.connexion.prepareStatement("SELECT * FROM orders " +
                                                                            " where orders.users_id = ? and orders.is_finished = 0" +
                                                                            " order by id desc limit 1");

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
            PreparedStatement sql = Database.connexion.prepareStatement("INSERT INTO orders (date, users_id, is_finished) VALUES (now(), ?, 0)");

            sql.setInt(1, user.getId());

            sql.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void confirmCart (Order order) {
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("UPDATE game_orders SET is_ordered = 1 WHERE orders_id = ?");

            sql.setInt(1, order.getId());

            sql.executeUpdate();

            PreparedStatement sql2 = Database.connexion.prepareStatement("UPDATE orders SET is_finished = 1 WHERE id = ?");

            sql2.setInt(1, order.getId());

            sql2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Order> getAllOrdersFinishedByUser(User user) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("SELECT * FROM orders WHERE users_id = ? and is_finished = 1");

            sql.setInt(1, user.getId());

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"), rs.getDate("date"), user));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
}

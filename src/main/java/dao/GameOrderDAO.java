package dao;

import bean.Game;
import bean.GameOrder;
import bean.Order;
import bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameOrderDAO {

    public void addGame(Game gameToAdd, Order order) {
        try {
            if (checkGameInCart(gameToAdd, order)) {
                addGameQuantity(gameToAdd, order);
                return;
            }

            PreparedStatement sql = Database.connexion.prepareStatement("INSERT INTO game_orders (game_id, orders_id, quantity, is_ordered) VALUES (?, ?, 1, 0)");

            sql.setInt(1, gameToAdd.getId());
            sql.setInt(2, order.getId());

            sql.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Boolean checkGameInCart (Game game, Order order) {
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("SELECT * FROM game_orders WHERE game_id = ? and orders_id = ?");

            sql.setInt(1, game.getId());
            sql.setInt(2, order.getId());

            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeGame(Game gameToRemove, Order order) {

        try {
            PreparedStatement sql = Database.connexion.prepareStatement("DELETE FROM game_orders WHERE game_id = ? and orders_id = ?");

            sql.setInt(1, gameToRemove.getId());
            sql.setInt(2, order.getId());

            sql.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<GameOrder> getGamesInCart(Order order) {
        ArrayList<GameOrder> gamesInCart = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("SELECT * FROM game_orders WHERE orders_id = ? AND is_ordered = 0");

            sql.setInt(1, order.getId());

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Game game = new GameDAO().findById(rs.getInt("game_id"));

                GameOrder gameOrder = new GameOrder(game, order, rs.getInt("quantity"));

                gamesInCart.add(gameOrder);
            }

            return gamesInCart;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void addGameQuantity (Game game, Order order) {
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("UPDATE game_orders SET quantity = quantity + 1 WHERE game_id = ? and orders_id = ?");

            sql.setInt(1, game.getId());
            sql.setInt(2, order.getId());

            sql.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeGameQuantity (Game game, Order order) {
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("UPDATE game_orders SET quantity = quantity - 1 WHERE game_id = ? and orders_id = ?");

            sql.setInt(1, game.getId());
            sql.setInt(2, order.getId());

            sql.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

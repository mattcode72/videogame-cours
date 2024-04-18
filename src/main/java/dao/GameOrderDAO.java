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
            PreparedStatement sql = Database.connexion.prepareStatement("INSERT INTO game_orders (game_id, orders_id, quantity, is_ordered) VALUES (?, ?, 1, 0)");

            sql.setInt(1, gameToAdd.getId());
            sql.setInt(2, order.getId());

            sql.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeGame(Game gameToRemove, User user) {
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("DELETE FROM game_orders WHERE game_id = ? and users_id = ?");

            sql.setInt(1, gameToRemove.getId());
            sql.setInt(2, user.getId());

            sql.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Game> getGamesInCart(Order order) {
        ArrayList<Game> gamesInCart = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("SELECT * FROM game_orders WHERE orders_id = ? AND is_ordered = 0");

            sql.setInt(1, order.getId());

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Game game = new GameDAO().findById(rs.getInt("game_id"));

                gamesInCart.add(game);
            }

            return gamesInCart;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

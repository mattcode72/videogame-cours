package dao;

import bean.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameDAO {

    GameCategoryDAO gameCategoryDAO = new GameCategoryDAO();

    public ArrayList<Game> getAll() {
        ArrayList<Game> games = new ArrayList<>();
        Database.Connect();

        CategoryDAO categoryDAO = new CategoryDAO();

        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select game.id, game.name, game.description, game.release_date, game.price from game");

            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                ArrayList<GameCategory> gameCategories = gameCategoryDAO.getCategoriesByGameId(rs.getInt("game.id"));
                ArrayList<Category> categories = new ArrayList<>();

                for (GameCategory gameCategory : gameCategories) {
                    categories.add(categoryDAO.findById(gameCategory.getCategory_id()));
                }

                Game game = new Game(rs.getInt("game.id"), rs.getString("game.name"), rs.getString("game.description"), rs.getDate("game.release_date"), rs.getInt("game.price"), categories);

                games.add(game);
            }

            return games;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Game findById(int id) {
        try  {

            PreparedStatement sql = Database.connexion.prepareStatement("select * from game WHERE game.id=?");

            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                ArrayList<GameCategory> categories = gameCategoryDAO.getCategoriesByGameId(rs.getInt("game.id"));

                return new Game(rs.getString("name"), rs.getString("description"), rs.getDate("release_date"), rs.getInt("price"), categories);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

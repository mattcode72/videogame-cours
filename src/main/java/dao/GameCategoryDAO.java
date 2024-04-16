package dao;

import bean.Category;
import bean.Game;
import bean.GameCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameCategoryDAO {

    public ArrayList<GameCategory> getCategoriesByGameId (int gameId) {
        ArrayList<GameCategory> gameCategories = new ArrayList<>();
        Database.Connect();

        GameDAO gameDAO = new GameDAO();

        try {
            PreparedStatement sqlCategories = Database.connexion.prepareStatement("select category.name from category" +
                    " LEFT JOIN game_category ON category.id = game_category.category_id" +
                    " LEFT JOIN game ON game_category.game_id = game.id" +
                    " WHERE game.id = ?");

            sqlCategories.setInt(1, gameId);

            ResultSet rs = sqlCategories.executeQuery();

            if (rs.next()) {
                Category category = new Category(rs.getString("category.name"));
                Game game = gameDAO.findById(gameId);

                GameCategory gameCategory = new GameCategory(game, category);
                gameCategories.add(gameCategory);
            }

            return gameCategories;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
}

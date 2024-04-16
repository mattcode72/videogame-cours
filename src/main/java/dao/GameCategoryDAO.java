package dao;

import bean.Category;
import bean.Game;
import bean.GameCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameCategoryDAO {

    public ArrayList<GameCategory> getCategoriesByGameId (Game game) {
        ArrayList<GameCategory> gameCategories = new ArrayList<>();

        GameDAO gameDAO = new GameDAO();
        int gameId = game.getId();

        try {
            PreparedStatement sqlCategories = Database.connexion.prepareStatement("select category.name from category" +
                    " LEFT JOIN game_category ON category.id = game_category.category_id" +
                    " WHERE game_category.game_id = ?");

            sqlCategories.setInt(1, gameId);

            ResultSet rs = sqlCategories.executeQuery();

            while (rs.next()) {
                Category category = new Category(rs.getString("category.name"));

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

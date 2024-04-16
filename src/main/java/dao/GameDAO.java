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

            while (rs.next()) {
                ArrayList<GameCategory> gameCategories = gameCategoryDAO.getCategoriesByGameId(rs.getInt("game.id"));

                System.out.println("gameCategories: " + gameCategories);

                ArrayList<Category> categories = new ArrayList<>();

                for (GameCategory gameCategory : gameCategories) {
                    categories.add(categoryDAO.findById(gameCategory.getCategory().getId()));
                    System.out.println(gameCategory.getCategory().getId());
                }

                Media thumbnail = new MediaDAO().getThumbnailByGameId(rs.getInt("game.id"));

                Game game = new Game(rs.getInt("game.id"), rs.getString("game.name"), rs.getString("game.description"), rs.getDate("game.release_date"), rs.getInt("game.price"), categories, thumbnail);

                games.add(game);
            }

            return games;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Game findById(int id) {
        CategoryDAO categoryDAO = new CategoryDAO();
        try  {

            PreparedStatement sql = Database.connexion.prepareStatement("select * from game WHERE game.id = ?");

            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                ArrayList<GameCategory> gameCategories = gameCategoryDAO.getCategoriesByGameId(rs.getInt("game.id"));
                ArrayList<Category> categories = new ArrayList<>();

                for (GameCategory gameCategory : gameCategories) {
                    categories.add(categoryDAO.findById(gameCategory.getCategory().getId()));
                }

                ArrayList<Media> images = new MediaDAO().getMediasPageByGameId(rs.getInt("game.id"), "image");
                ArrayList<Media> videos = new MediaDAO().getMediasPageByGameId(rs.getInt("game.id"), "video");

                ArrayList<GamePlatform> gamePlatforms = new GamePlatformDAO().getPlatformsByGameId(rs.getInt("game.id"));
                ArrayList<Platform> platforms = new ArrayList<>();

                for (GamePlatform gamePlatform : gamePlatforms) {
                    platforms.add(gamePlatform.getPlatform());
                }

                return new Game(rs.getString("name"), rs.getString("description"), rs.getDate("release_date"), rs.getInt("price"), categories, images, videos, platforms);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

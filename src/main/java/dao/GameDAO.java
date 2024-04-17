package dao;

import bean.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

                ArrayList<Category> categories = new ArrayList<>();

                for (GameCategory gameCategory : gameCategories) {
                    categories.add(categoryDAO.findById(gameCategory.getCategory().getId()));
                }

                ArrayList<GamePlatform> gamePlatforms = new GamePlatformDAO().getPlatformsByGameId(rs.getInt("game.id"));
                ArrayList<Platform> platforms = new ArrayList<>();

                for (GamePlatform gamePlatform : gamePlatforms) {
                    platforms.add(gamePlatform.getPlatform());
                }

                Media thumbnail = new MediaDAO().getThumbnailByGameId(rs.getInt("game.id"));

                Game game = new Game(rs.getInt("game.id"), rs.getString("game.name"), rs.getString("game.description"), rs.getDate("game.release_date"), rs.getInt("game.price"), categories, platforms, thumbnail);

                games.add(game);
            }

            return games;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Game> getGamesByFilter(int categoryId, int platformId) {
        Database.Connect();

        Stream<Game> gamesFiltered = getAll().stream();

        if (categoryId != 0) {
            gamesFiltered = gamesFiltered.filter(game -> game.getCategories().stream().anyMatch(category -> category.getId() == categoryId));
        }
        if (platformId != 0) {
            gamesFiltered = gamesFiltered.filter(game -> game.getPlatforms().stream().anyMatch(platform -> platform.getId() == platformId));
        }

        return gamesFiltered.collect(Collectors.toCollection(ArrayList<Game>::new));
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

                ArrayList<GameLang> gameLangs = new GameLangDAO().getLangsByGameId(rs.getInt("game.id"));
                ArrayList<Lang> langs = new ArrayList<>();

                for (GameLang gameLang : gameLangs) {
                    langs.add(gameLang.getLang());
                }

                ArrayList<GameGameMode> gameGameModes = new GameGameModeDAO().getGameModesByGameId(rs.getInt("game.id"));
                ArrayList<GameMode> gameModes = new ArrayList<>();

                for (GameGameMode gameGameMode : gameGameModes) {
                    gameModes.add(gameGameMode.getGameMode());
                }

                ArrayList<GameDeveloper> gameDevelopers = new GameDeveloperDAO().getDevelopersByGameId(rs.getInt("game.id"));
                ArrayList<Developer> developers = new ArrayList<>();

                for (GameDeveloper gameDeveloper : gameDevelopers) {
                    developers.add(gameDeveloper.getDeveloper());
                }

                ArrayList<Review> reviews = new ReviewDAO().getReviewsByGameId(rs.getInt("game.id"));

                return new Game(rs.getString("name"), rs.getString("description"), rs.getDate("release_date"), rs.getInt("price"), categories, images, videos, platforms, langs, gameModes, developers, reviews);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

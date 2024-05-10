package dao;

import bean.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
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

                ArrayList<GameLang> gameLangs = new GameLangDAO().getLangsByGameId(rs.getInt("game.id"));
                ArrayList<Lang> langs = new ArrayList<>();

                for (GameLang gameLang : gameLangs) {
                    langs.add(gameLang.getLang());
                }

                Media thumbnail = new MediaDAO().getThumbnailByGameId(rs.getInt("game.id"));

                Game game = new Game(rs.getInt("game.id"), rs.getString("game.name"), rs.getString("game.description"), rs.getDate("game.release_date"), rs.getInt("game.price"), categories, platforms, langs, thumbnail);

                games.add(game);
            }

            return games;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Game> getGamesByFilter(String[] categoriesId, String[] platformsId, String[] langsId, String search) {

        Stream<Game> gamesFiltered = getAll().stream();

        if (categoriesId != null) {
            for (String category : categoriesId) {
                if (category != null) {
                    gamesFiltered = gamesFiltered.filter(game -> game.getCategories().stream().anyMatch(categ -> categ.getId() == Integer.parseInt(category)));
                }
            }
        }

        if (platformsId != null) {
            for (String platform : platformsId) {
                if (platform != null) {
                    gamesFiltered = gamesFiltered.filter(game -> game.getPlatforms().stream().anyMatch(plat -> plat.getId() == Integer.parseInt(platform)));
                }
            }
        }

        if (langsId != null) {
            for (String lang : langsId) {
                if (lang != null) {
                    gamesFiltered = gamesFiltered.filter(game -> game.getLangs().stream().anyMatch(lan -> lan.getId() == Integer.parseInt(lang)));
                }
            }
        }

        if (!search.isEmpty()) {
            gamesFiltered = gamesFiltered.filter(game -> game.getName().toLowerCase().contains(search.toLowerCase()));
        }

        return gamesFiltered.collect(Collectors.toCollection(ArrayList<Game>::new));
    }


    public Game findById(int id) {
        Database.Connect();
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

                return new Game(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDate("release_date"), rs.getInt("price"), categories, platforms, langs, gameModes, developers, reviews);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateGame (Game game, String action) {
        try {
            Database.Connect();
            PreparedStatement sql = Database.connexion.prepareStatement(" UPDATE game SET"
                    + " name = ?, description = ?, release_date = ?, price = ?"
                    + " WHERE id= ?");

            sql.setString(1, game.getName());
            sql.setString(2, game.getDescription());
            sql.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            sql.setInt(4, game.getPrice());
            sql.setInt(5, game.getId());

            sql.executeUpdate();

            if (Objects.equals(action, "delete")) {
                PreparedStatement sql2 = Database.connexion.prepareStatement("DELETE FROM game_platform WHERE game_id = ? and platform_id = ?");
                for (Platform platform : game.getPlatforms()) {
                    sql2.setInt(1, game.getId());
                    sql2.setInt(2, platform.getId());
                    sql2.executeUpdate();
                }

                PreparedStatement sql3 = Database.connexion.prepareStatement("DELETE FROM game_lang WHERE game_id = ? and lang_id = ?");
                for (Lang lang : game.getLangs()) {
                    sql3.setInt(1, game.getId());
                    sql3.setInt(2, lang.getId());
                    sql3.executeUpdate();
                }

                PreparedStatement sql4 = Database.connexion.prepareStatement("DELETE FROM game_developer WHERE game_id = ? and developer_id = ?");
                for (Developer developer : game.getDevelopers()) {
                    sql4.setInt(1, game.getId());
                    sql4.setInt(2, developer.getId());
                    sql4.executeUpdate();
                }

                PreparedStatement sql5 = Database.connexion.prepareStatement("DELETE FROM game_category WHERE game_id = ? and category_id = ?");
                for (Category category : game.getCategories()) {
                    sql5.setInt(1, game.getId());
                    sql5.setInt(2, category.getId());
                    sql5.executeUpdate();
                }

                PreparedStatement sql6 = Database.connexion.prepareStatement("DELETE FROM game_gamemode WHERE game_id = ? and gamemode_id = ?");
                for (GameMode gameMode : game.getGameModes()) {
                    sql6.setInt(1, game.getId());
                    sql6.setInt(2, gameMode.getId());
                    sql6.executeUpdate();
                }
            } else {
                PreparedStatement sql2 = Database.connexion.prepareStatement("INSERT INTO game_platform (game_id, platform_id) VALUES (?, ?)");
                for (Platform platform : game.getPlatforms()) {
                    sql2.setInt(1, game.getId());
                    sql2.setInt(2, platform.getId());
                    sql2.executeUpdate();
                }

                PreparedStatement sql3 = Database.connexion.prepareStatement("INSERT INTO game_lang (game_id, lang_id) VALUES (?, ?)");
                for (Lang lang : game.getLangs()) {
                    sql3.setInt(1, game.getId());
                    sql3.setInt(2, lang.getId());
                    sql3.executeUpdate();
                }

                PreparedStatement sql4 = Database.connexion.prepareStatement("INSERT INTO game_developer (game_id, developer_id) VALUES (?, ?)");
                for (Developer developer : game.getDevelopers()) {
                    sql4.setInt(1, game.getId());
                    sql4.setInt(2, developer.getId());
                    sql4.executeUpdate();
                }

                PreparedStatement sql5 = Database.connexion.prepareStatement("INSERT INTO game_category (game_id, category_id) VALUES (?, ?)");
                for (Category category : game.getCategories()) {
                    sql5.setInt(1, game.getId());
                    sql5.setInt(2, category.getId());
                    sql5.executeUpdate();
                }

                PreparedStatement sql6 = Database.connexion.prepareStatement("INSERT INTO game_gamemode (game_id, gamemode_id) VALUES (?, ?)");
                for (GameMode gameMode : game.getGameModes()) {
                    sql6.setInt(1, game.getId());
                    sql6.setInt(2, gameMode.getId());
                    sql6.executeUpdate();
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int addGame (Game game) {
        try {
            Database.Connect();
            PreparedStatement sql = Database.connexion.prepareStatement("INSERT INTO game (name, description, release_date, price)"
                    + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            sql.setString(1, game.getName());
            sql.setString(2, game.getDescription());
            sql.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            sql.setInt(4, game.getPrice());

            sql.executeUpdate();

            int gameId = 0;
            ResultSet generatedKeys = sql.getGeneratedKeys();
            if (generatedKeys.next()) {
                gameId = generatedKeys.getInt(1);
            }


            PreparedStatement sql2 = Database.connexion.prepareStatement("INSERT INTO game_platform (game_id, platform_id) VALUES (?, ?)");

            for (Platform platform : game.getPlatforms()) {
                sql2.setInt(1, gameId);
                sql2.setInt(2, platform.getId());
                sql2.executeUpdate();
            }

            PreparedStatement sql3 = Database.connexion.prepareStatement("INSERT INTO game_lang (game_id, lang_id) VALUES (?, ?)");

            for (Lang lang : game.getLangs()) {
                sql3.setInt(1, gameId);
                sql3.setInt(2, lang.getId());
                sql3.executeUpdate();
            }

            PreparedStatement sql4 = Database.connexion.prepareStatement("INSERT INTO game_category (game_id, category_id) VALUES (?, ?)");

            for (Category category : game.getCategories()) {
                sql4.setInt(1, gameId);
                sql4.setInt(2, category.getId());
                sql4.executeUpdate();
            }

            PreparedStatement sql5 = Database.connexion.prepareStatement("INSERT INTO game_developer (game_id, developer_id) VALUES (?, ?)");

            for (Developer developer : game.getDevelopers()) {
                sql5.setInt(1, gameId);
                sql5.setInt(2, developer.getId());
                sql5.executeUpdate();
            }

            PreparedStatement sql6 = Database.connexion.prepareStatement("INSERT INTO game_gamemode (game_id, gamemode_id) VALUES (?, ?)");

            for (GameMode gameMode : game.getGameModes()) {
                sql6.setInt(1, gameId);
                sql6.setInt(2, gameMode.getId());
                sql6.executeUpdate();
            }

            PreparedStatement sql7 = Database.connexion.prepareStatement("INSERT INTO media (path, is_thumbnail, game_id, media_type_id) VALUES (?, ?, ?, ?)");
            sql7.setString(1, game.getThumbnail().getPath());
            sql7.setBoolean(2, true);
            sql7.setInt(3, gameId);
            sql7.setInt(4, 1);
            sql7.executeUpdate();

            return gameId;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void delete(int gameId) {
        try {
            Database.Connect();

            PreparedStatement sql2 = Database.connexion.prepareStatement("DELETE FROM game_platform WHERE game_id = ?");
            sql2.setInt(1, gameId);
            sql2.executeUpdate();

            PreparedStatement sql3 = Database.connexion.prepareStatement("DELETE FROM game_lang WHERE game_id = ?");
            sql3.setInt(1, gameId);
            sql3.executeUpdate();

            PreparedStatement sql4 = Database.connexion.prepareStatement("DELETE FROM game_developer WHERE game_id = ?");
            sql4.setInt(1, gameId);
            sql4.executeUpdate();

            PreparedStatement sql5 = Database.connexion.prepareStatement("DELETE FROM game_category WHERE game_id = ?");
            sql5.setInt(1, gameId);
            sql5.executeUpdate();

            PreparedStatement sql6 = Database.connexion.prepareStatement("DELETE FROM game_gamemode WHERE game_id = ?");
            sql6.setInt(1, gameId);
            sql6.executeUpdate();

            PreparedStatement sql = Database.connexion.prepareStatement("DELETE FROM game WHERE id = ?");
            sql.setInt(1, gameId);
            sql.executeUpdate();

            PreparedStatement sql7 = Database.connexion.prepareStatement("DELETE FROM media WHERE game_id = ?");
            sql7.setInt(1, gameId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

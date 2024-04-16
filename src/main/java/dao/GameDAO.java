package dao;

import bean.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameDAO {

    public ArrayList<Game> getAll() {
        ArrayList<Game> games = new ArrayList<>();
        Database.Connect();

        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select game.name, game.description, game.release_date, lang.name from game" +
                    "LEFT JOIN game_developer ON game.id = game_developer.game_id" +
                    "LEFT JOIN developer ON game_developer.developer_id = developer.id" +
                    "LEFT JOIN game_category ON game.id = game_category.game_id" +
                    "LEFT JOIN category ON game_category.category_id = category.id" +
                    "LEFT JOIN game_platform ON game.id = game_platform.game_id" +
                    "LEFT JOIN platform ON game_platform.platform_id = platform.id" +
                    "LEFT JOIN game_tag ON game.id = game_tag.game_id" +
                    "LEFT JOIN tag ON game_tag.tag_id = tag.id" +
                    "LEFT JOIN game_lang ON game.id = game_lang.game_id" +
                    "LEFT JOIN lang ON game_lang.lang_id = lang.id" +
                    "LEFT JOIN game_gamemode ON game.id = game_gamemode.game_id" +
                    "LEFT JOIN gamemode ON game_gamemode.gamemode_id = gamemode.id" +
                    "LEFT JOIN review ON game.id = review.game_id" +
                    "LEFT JOIN media ON game.id = media.game_id");

            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                Lang lang = new Lang(rs.getString("lang.name"));
                Platform platform = new Platform(rs.getString("platform.name"));
                Category category = new Category(rs.getString("category.name"));
                GameMode gameMode = new GameMode(rs.getString("gamemode.name"));
                Tag tag = new Tag(rs.getString("tag.name"));
                Developer developer = new Developer(rs.getString("developer.name"));

                Game game = new Game(rs.getString("game.name"),
                        rs.getString("game.description"),
                        rs.getDate("game.release_date"),
                        lang,
                        platform,
                        category,
                        gameMode,
                        tag,
                        developer);

                games.add(game);
            }

            return games;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

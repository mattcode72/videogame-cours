package dao;

import bean.GamePlatform;
import bean.Platform;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GamePlatformDAO {

    public ArrayList<GamePlatform> getPlatformsByGameId (int gameId) {
        ArrayList<GamePlatform> gamePlatforms = new ArrayList<>();

        try {
            PreparedStatement sqlPlatforms = Database.connexion.prepareStatement("select platform.id ,platform.name from platform" +
                    " LEFT JOIN game_platform ON platform.id = game_platform.platform_id" +
                    " WHERE game_platform.game_id = ?");

            sqlPlatforms.setInt(1, gameId);

            ResultSet rs = sqlPlatforms.executeQuery();

            while (rs.next()) {
                Platform platform = new Platform(rs.getInt("platform.id"), rs.getString("platform.name"));

                GamePlatform gamePlatform = new GamePlatform(platform);

                gamePlatforms.add(gamePlatform);
            }

            return gamePlatforms;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

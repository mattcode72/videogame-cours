package dao;

import bean.GameGameMode;
import bean.GameMode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameGameModeDAO {

    public ArrayList<GameGameMode> getGameModesByGameId(int gameId) {
        ArrayList<GameGameMode> gameGameModes = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from game_gamemode where game_id = ?");

            sql.setInt(1, gameId);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                GameMode gameMode = new GameMode(rs.getInt("id"), rs.getString("name"));

                GameGameMode gameGameMode = new GameGameMode(gameMode);

                gameGameModes.add(gameGameMode);
            }

            return gameGameModes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

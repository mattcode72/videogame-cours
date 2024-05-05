package dao;

import bean.GameMode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameModeDAO {

    public ArrayList<GameMode> getAll() {
        ArrayList<GameMode> gameModes = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from gamemode");

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                GameMode gameMode = new GameMode(rs.getInt("id"), rs.getString("name"));

                gameModes.add(gameMode);
            }

            return gameModes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

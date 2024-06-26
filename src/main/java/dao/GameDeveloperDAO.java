package dao;

import bean.Developer;
import bean.GameDeveloper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameDeveloperDAO {

    public ArrayList<GameDeveloper> getDevelopersByGameId(int gameId) {
        ArrayList<GameDeveloper> gameDevelopers = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from game_developer " +
                                                            " inner join developer on game_developer.developer_id = developer.id " +
                                                            " where game_id = ?");

            sql.setInt(1, gameId);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Developer developer = new Developer(rs.getInt("developer.id"), rs.getString("developer.name"));

                GameDeveloper gameDeveloper = new GameDeveloper(developer);

                gameDevelopers.add(gameDeveloper);
            }

            return gameDevelopers;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package dao;

import bean.GameLang;
import bean.Lang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameLangDAO {

    public ArrayList<GameLang> getLangsByGameId(int gameId) {
        ArrayList<GameLang> gameLangs = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from game_lang where game_id = ?");

            sql.setInt(1, gameId);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Lang lang = new Lang(rs.getInt("id"), rs.getString("name"));

                GameLang gameLang = new GameLang(lang);

                gameLangs.add(gameLang);
            }

            return gameLangs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

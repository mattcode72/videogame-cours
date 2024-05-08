package dao;

import bean.Lang;
import bean.Platform;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LangDAO {
    public ArrayList<Lang> getAll() {
        Database.Connect();
        ArrayList<Lang> langs = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from lang");

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Lang lang = new Lang(rs.getInt("id"), rs.getString("name"));

                langs.add(lang);
            }

            return langs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lang findById(int id) {
        Database.Connect();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from lang where id = ?");
            sql.setInt(1, id);

            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                return new Lang(rs.getInt("id"), rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

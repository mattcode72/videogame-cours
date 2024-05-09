package dao;

import bean.Category;
import bean.Platform;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlatformDAO {

    public Platform findById(int id) {
        try  {

            PreparedStatement sql = Database.connexion.prepareStatement("select * from platform WHERE platform.id=?");

            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                return new Platform(rs.getInt("id"), rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Platform> getAll() {
        Database.Connect();
        ArrayList<Platform> platforms = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from platform");

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Platform platform = new Platform(rs.getInt("id"), rs.getString("name"));

                platforms.add(platform);
            }

            return platforms;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

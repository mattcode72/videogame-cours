package dao;

import bean.Platform;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlatformDAO {

    public Platform findById(int id) {
        try  {

            PreparedStatement sql = Database.connexion.prepareStatement("select * from platform WHERE platform.id=?");

            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                return new Platform(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

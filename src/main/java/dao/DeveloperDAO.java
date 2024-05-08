package dao;

import bean.Developer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DeveloperDAO {

    public ArrayList<Developer> getAll() {
        Database.Connect();
        ArrayList<Developer> developers = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from developer");

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Developer developer = new Developer(rs.getInt("id"), rs.getString("name"));

                developers.add(developer);
            }

            return developers;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Developer findById(int id) {
        Database.Connect();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from developer where id = ?");
            sql.setInt(1, id);

            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                return new Developer(rs.getInt("id"), rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

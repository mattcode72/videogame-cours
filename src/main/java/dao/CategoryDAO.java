package dao;

import bean.Category;
import bean.Game;
import bean.GameCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryDAO {
    public Category findById(int id) {
        try  {

            PreparedStatement sql = Database.connexion.prepareStatement("select * from category WHERE category.id=?");

            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                return new Category(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

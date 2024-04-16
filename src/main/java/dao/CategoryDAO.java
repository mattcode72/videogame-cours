package dao;

import bean.Category;

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

    public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from category");

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                Category category = new Category(rs.getInt("id"), rs.getString("name"));

                categories.add(category);
            }

            return categories;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

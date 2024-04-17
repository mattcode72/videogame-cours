package dao;

import bean.Review;
import bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReviewDAO {

    public ArrayList<Review> getReviewsByGameId(int gameId) {
        ArrayList<Review> reviews = new ArrayList<>();

        UserDAO userDAO = new UserDAO();

        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from review where game_id = ?");

            sql.setInt(1, gameId);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                User user = userDAO.findById(rs.getInt("user_id"));

                Review review = new Review(rs.getInt("id"), rs.getInt("rating"), rs.getString("content"), rs.getDate("date"), user);

                reviews.add(review);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

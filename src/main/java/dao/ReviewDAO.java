package dao;

import bean.Game;
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
            PreparedStatement sql = Database.connexion.prepareStatement("select * from review where game_id = ? and is_validated = true");

            sql.setInt(1, gameId);

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                User user = userDAO.findById(rs.getInt("users_id"));

                Review review = new Review(rs.getInt("id"), rs.getInt("rating"), rs.getString("content"), rs.getDate("date"), user);

                reviews.add(review);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addReview(Review review) {
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("insert into review (game_id, users_id, rating, content, date, is_validated) values (?, ?, ?, ?, now(), false)");

            sql.setInt(1, review.getGame().getId());
            sql.setInt(2, review.getUser().getId());
            sql.setInt(3, review.getRating());
            sql.setString(4, review.getContent());

            sql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Review> getReviewsNotValidated () {
        ArrayList<Review> reviews = new ArrayList<>();

        UserDAO userDAO = new UserDAO();
        GameDAO gameDAO = new GameDAO();

        try {
            PreparedStatement sql = Database.connexion.prepareStatement("select * from review where is_validated = false");

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                User user = userDAO.findById(rs.getInt("users_id"));
                Game game = gameDAO.findById(rs.getInt("game_id"));

                Review review = new Review(rs.getInt("rating"), rs.getString("content"), rs.getDate("date"), game, user);

                reviews.add(review);
            }
            return reviews;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void validateReview(int id) {
        try {
            PreparedStatement sql = Database.connexion.prepareStatement("update review set is_validated = true where id = ?");

            sql.setInt(1, id);

            sql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

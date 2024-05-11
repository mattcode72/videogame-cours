package servlet;

import bean.Review;
import dao.GameDAO;
import dao.ReviewDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin")
public class Admin extends HttpServlet {

    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        GameDAO gameDao = new GameDAO();
        ReviewDAO reviewDAO = new ReviewDAO();

        request.setAttribute("users", userDao.getAll());
        request.setAttribute("games", gameDao.getAll());
        request.setAttribute("reviews", reviewDAO.getReviewsNotValidated());

        request.getRequestDispatcher("/vue/admin/admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        GameDAO gameDAO = new GameDAO();
        ReviewDAO reviewDAO = new ReviewDAO();

        if (action.equals("deleteToGame")) {
            int gameId = Integer.parseInt(request.getParameter("gameId"));
            gameDAO.delete(gameId);
        } else if (action.equals("validateReview")) {
            System.out.println("gameId : " + request.getParameter("idGame"));
            System.out.println("userId : " + request.getParameter("userId"));
            int gameId = Integer.parseInt(request.getParameter("idGame"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            Review review = reviewDAO.getReviewByGameAndUserId(gameId, userId);
            reviewDAO.validateReview(review);
        }

        response.sendRedirect(request.getContextPath() + "/admin");
    }
}

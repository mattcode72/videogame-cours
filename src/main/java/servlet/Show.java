package servlet;

import java.io.IOException;

import bean.*;
import dao.MediaDAO;
import dao.ReviewDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.GameDAO;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Show
 */
@WebServlet("/show")
public class Show extends HttpServlet {
    MediaDAO mediaDAO = new MediaDAO();
    GameDAO gameDao = new GameDAO();
    ReviewDAO reviewDAO = new ReviewDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Show() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("game", gameDao.findById(id));
        request.setAttribute("images", mediaDAO.getMediasPageByGameId(id, 1));
        request.setAttribute("reviews", reviewDAO.getReviewsByGameId(id));

        request.getRequestDispatcher("vue/game/show.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("currentUser");

        String action = request.getParameter("action");
        if (action.equals("addToRating")) {
            Game game = gameDao.findById(Integer.parseInt(request.getParameter("gameId")));

            Review review = new Review(Integer.parseInt(request.getParameter("rating")), request.getParameter("comment"), game, user);

            reviewDAO.addReview(review);

            response.sendRedirect("show?id=" + Integer.parseInt(request.getParameter("gameId")));
        }

    }
}

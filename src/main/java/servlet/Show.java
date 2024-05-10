package servlet;

import java.io.IOException;

import bean.Developer;
import bean.GameDeveloper;
import bean.User;
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
        int userId = user.getId();

        String action = request.getParameter("action");
        if (action.equals("addToRating")) {
            int gameId = Integer.parseInt(request.getParameter("gameId"));
            reviewDAO.addReview(gameId, userId, Integer.parseInt(request.getParameter("rating")), request.getParameter("comment"));

           response.sendRedirect("show?id=" + gameId);
        }

    }
}

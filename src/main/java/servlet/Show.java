package servlet;

import java.io.IOException;

import bean.Developer;
import bean.GameDeveloper;
import dao.MediaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.GameDAO;

/**
 * Servlet implementation class Show
 */
@WebServlet("/show")
public class Show extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
        MediaDAO mediaDAO = new MediaDAO();
        GameDAO gameDao = new GameDAO();

        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("game", gameDao.findById(id));

        request.setAttribute("images", mediaDAO.getMediasPageByGameId(id, 1));

        request.getRequestDispatcher("vue/game/show.jsp").forward(request, response);
    }
}

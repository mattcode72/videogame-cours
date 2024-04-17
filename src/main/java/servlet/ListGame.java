package servlet;

import java.io.IOException;

import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Game;
import bean.Order;
import dao.GameDAO;
//import dao.OrderDAO;

/**
 * Servlet implementation class ListGame
 */
@WebServlet("/games")
public class ListGame extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListGame() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GameDAO gameDao = new GameDAO();
        CategoryDAO categoryDao = new CategoryDAO();

        request.setAttribute("games", gameDao.getAll());
        request.setAttribute("categories", categoryDao.getAll());

        request.getRequestDispatcher("vue/game/list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDAO categoryDao = new CategoryDAO();
        GameDAO gameDao = new GameDAO();

        int idCategory = Integer.parseInt(request.getParameter("filterCategory"));
        int idPlatform = Integer.parseInt(request.getParameter("filterPlatform"));

        request.setAttribute("games", gameDao.getGamesByFilter(idCategory, idPlatform));
        request.setAttribute("categories", categoryDao.getAll());

        request.getRequestDispatcher("vue/game/list.jsp").forward(request, response);
    }

}

package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.User;
import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Game;
import bean.Order;
//import dao.OrderDAO;

/**
 * Servlet implementation class ListGame
 */
@WebServlet("/games")
public class ListGame extends HttpServlet {
    private static final long serialVersionUID = 1L;

    GameDAO gameDao = new GameDAO();
    CategoryDAO categoryDao = new CategoryDAO();
    PlatformDAO platformDao = new PlatformDAO();
    LangDAO langDao = new LangDAO();
    OrderDAO orderDao = new OrderDAO();
    GameOrderDAO gameOrderDao = new GameOrderDAO();

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
        request.setAttribute("games", gameDao.getAll());
        request.setAttribute("categories", categoryDao.getAll());
        request.setAttribute("platforms", platformDao.getAll());
        request.setAttribute("langs", langDao.getAll());

        request.getRequestDispatcher("vue/game/list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        String idGameToAdd = request.getParameter("addToCart");

        User userConnected = (User) session.getAttribute("user");

        if (action.equals("addToCart")) {
            // On vérifie si une commande est déjà en cours
            if (orderDao.getCurrentOrder(userConnected) == null) {
                // Si ce n'est pas le cas, on crée une nouvelle commande
                orderDao.createOrder(userConnected);
            } else {
                Order currentOrder = orderDao.getCurrentOrder(userConnected);

                gameOrderDao.addGame(gameDao.findById(Integer.parseInt(idGameToAdd)), currentOrder);
            }
        } else {

            String idCategory = request.getParameter("filterCategory");
            String idPlatform = request.getParameter("filterPlatform");
            String idLang = request.getParameter("filterLang");
            String search = request.getParameter("filterName");


            request.setAttribute("games", gameDao.getGamesByFilter(idCategory, idPlatform, idLang, search));
            request.setAttribute("categories", categoryDao.getAll());
            request.setAttribute("platforms", platformDao.getAll());
            request.setAttribute("langs", langDao.getAll());
        }

        request.getRequestDispatcher("vue/game/list.jsp").forward(request, response);
    }

}

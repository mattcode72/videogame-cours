package servlet;

import java.io.IOException;
import java.util.Objects;

import bean.Game;
import bean.Order;
import bean.User;
import dao.GameDAO;
import dao.GameOrderDAO;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



/**
 * Servlet implementation class Payment
 */
@WebServlet("/cart")
public class Cart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    GameOrderDAO gameOrderDao = new GameOrderDAO();
    OrderDAO orderDao = new OrderDAO();
    GameDAO gameDao = new GameDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // On redirige l'user vers la page de connexion s'il n'est pas connecté
        if (session.getAttribute("currentUser") == null) {
            response.sendRedirect("login");
            return;
        }

        Order currentOrder = orderDao.getCurrentOrder((User) session.getAttribute("currentUser"));

        request.setAttribute("items", gameOrderDao.getGamesInCart(currentOrder));

        request.getRequestDispatcher("vue/cart/show.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Order currentOrder = orderDao.getCurrentOrder((User) session.getAttribute("currentUser"));


        if (request.getParameter("deleteItem") != null) {
            Game gameToRemove = gameDao.findById(Integer.parseInt(request.getParameter("deleteItem")));
            gameOrderDao.removeGame(gameToRemove, currentOrder);
        } else if (Objects.equals(request.getParameter("confirmCart"), "true")) {
            orderDao.confirmCart(currentOrder);
        } else if (request.getParameter("more") != null) {
            Game gameToAddQuantity = gameDao.findById(Integer.parseInt(request.getParameter("more")));
            gameOrderDao.addGameQuantity(gameToAddQuantity, currentOrder);
        } else if (request.getParameter("less") != null) {
            Game gameToRemoveQuantity = gameDao.findById(Integer.parseInt(request.getParameter("less")));
            gameOrderDao.removeGameQuantity(gameToRemoveQuantity, currentOrder);
        }

        doGet(request, response);
    }
}

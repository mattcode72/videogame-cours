package servlet;

import java.io.IOException;

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

        System.out.println("id game : " + request.getAttribute("deleteItem"));

        if (request.getAttribute("deleteItem") != null) {
            Game gameToRemove = gameDao.findById((int) request.getAttribute("deleteItem"));
            System.out.println("ici servlet");
            gameOrderDao.removeGame(gameToRemove, currentOrder);
        }

        request.getRequestDispatcher("vue/cart/show.jsp").forward(request, response);
    }
}
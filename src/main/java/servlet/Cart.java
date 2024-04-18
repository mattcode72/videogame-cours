package servlet;

import java.io.IOException;

import bean.Order;
import bean.User;
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
        request.getRequestDispatcher("vue/cart/show.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Order currentOrder = orderDao.getCurrentOrder((User) session.getAttribute("currentUser"));

        request.setAttribute("items", gameOrderDao.getGamesInCart(currentOrder));
    }
}

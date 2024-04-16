package servlet;

import java.io.IOException;

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

        request.setAttribute("games", gameDao.getAll());

//        System.out.println("games : " + gameDao.getAll());

        request.getRequestDispatcher("vue/game/list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession  session = request.getSession(true);
        //PANIER
        //Verifie si le panier n'existe pas
//        if(session.getAttribute("order")==null) {
//            OrderDAO order_temp=new OrderDAO();
//            session.setAttribute( "order", order_temp );
//        }
//
//        if (request.getParameter("addOrder") != null ) {
//            //Recup panier depuis la session - ligne 56
//            OrderDAO orderDetails= (OrderDAO) session.getAttribute("order");
//
//            try {
//                GameDAO gameDao = new GameDAO();
//                Game game = gameDao.findById(Integer.parseInt(request.getParameter("addOrder")));
//                Order order = new Order(game,1);
//                orderDetails.addGame(order);
//                session.setAttribute("order", orderDetails );
//
//            } catch (NumberFormatException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        //FIN PANIER
        doGet(request, response);
    }

}

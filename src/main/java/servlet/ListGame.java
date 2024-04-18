package servlet;

import java.io.IOException;
import java.util.ArrayList;

import dao.CategoryDAO;
import dao.LangDAO;
import dao.PlatformDAO;
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

    GameDAO gameDao = new GameDAO();
    CategoryDAO categoryDao = new CategoryDAO();
    PlatformDAO platformDao = new PlatformDAO();
    LangDAO langDao = new LangDAO();

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

        if (action.equals("addToCart")) {
            if(session.getAttribute("panier")==null) {
                CartDAO cart_temp =new CartDAO();
                session.setAttribute( "cart", cart_temp );
            }

            if (request.getParameter("addPanier") != null ) {
                //Recup panier depuis la session - ligne 56
                CartDAO cartDetails= (CartDAO) session.getAttribute("cart");

                try {
                    GameDAO gameDao = new GameDAO();
                    Game game = gameDAO.findById(Integer.parseInt(request.getParameter("addPanier")));
                    Cart cart = new Cart(game,1);
                    cartDetails.ajouterArticle(cart);
                    session.setAttribute("cart", cartDetails );

                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //FIN PANIER
            doGet(request, response);
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

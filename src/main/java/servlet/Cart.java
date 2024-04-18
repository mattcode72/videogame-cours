package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.CartDAO;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/cart")
public class Payment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
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
        HttpSession session = request.getSession( true );
        CarteDAO cart=(CartDAO) session.getAttribute("cart");

        //Ajout quantite
        if (request.getParameter("more") != null ) {
            int qte = Integer.parseInt(request.getParameter("more"));
            cart.addQte(qte);
        }

        //Baisser la quantite
        if (request.getParameter("less") != null ) {
            int qte = Integer.parseInt(request.getParameter("less"));
            cart.removeQte(qte);
        }

        //Supprimer un elt de mon panier
        if (request.getParameter("deleteItem") != null ) {
            int idArticleToDelete=Integer.valueOf(request.getParameter("deleteItem"));
            cart.deleteById(idArticleToDelete);
            session.setAttribute( "cart", cart );
            response.sendRedirect(request.getHeader("referer"));
        }else
            //Supprimer le panier
            if (request.getParameter("deleteCart") != null ) {
                session.removeAttribute("cart");
                response.sendRedirect(request.getContextPath());
            } else{
                doGet(request, response);
            }
    }
}

package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCrypt;

import bean.User;
import dao.UserDAO;

/**
 * Servlet implementation class Inscription
 */
@MultipartConfig
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("vue/user/inscription.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDao = new UserDAO();

        User user = new User();
        user.setPseudo(pseudo);
        user.setEmail(email);

        String hashPasword = BCrypt.hashpw(password, BCrypt.gensalt());

        user.setPassword(hashPasword);

        if(userDao.checkMailExist(email)) {
            request.setAttribute("msn", "Vous avez déjà un compte avec cette adresse e-mail.");
            request.setAttribute("msnType",  "KO");
            doGet(request, response);
        }else {

            if (userDao.ajoutUser(user)) {


                request.setAttribute("msn", "Le compte utilisateur a été créé avec succès.");
                request.setAttribute("msnType",  "OK");
                request.getRequestDispatcher("vue/user/login.jsp").forward(request,response);

            } else {
                request.setAttribute("msn", "Le compte d'utilisateur n'a pas pu être créé.");
                request.setAttribute("msnType",  "KO");
                request.getRequestDispatcher("vue/user/inscription.jsp").forward(request,response);
            }

        }
    }

}

package servlet;

import java.io.IOException;

import bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UserDAO;
import org.springframework.aop.scope.ScopedProxyUtils;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("vue/user/login.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entered_email = request.getParameter("email");
        String entered_pwd = request.getParameter("password");

        UserDAO usDao = new UserDAO();

        if (entered_email.isEmpty() || entered_pwd.isEmpty()) {
            request.setAttribute("erreur", "L'email ou/et le mot de passe ne doit pas être vide .");

            doGet(request, response);
        } else {
            User user = usDao.login(entered_email, entered_pwd);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUser", user);
                response.sendRedirect("/videogame_war_exploded/games");
            } else {
                request.setAttribute("erreur", "L'email ou le mot de passe incorrect.");

                doGet(request, response);
            }
        }
    }
}

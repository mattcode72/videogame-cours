package servlet;

import java.io.IOException;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;

import bean.User;
import dao.UserDAO;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/profil")
public class Profil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDao = new OrderDAO();

        request.setAttribute("orders", orderDao.getAllOrdersFinishedByUser((User) request.getSession().getAttribute("currentUser")));

        request.getRequestDispatcher("vue/user/profil.jsp").forward(request, response);
    }

    protected void doRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("vue/user/login.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        HttpSession  session = request.getSession(true);

        if (request.getParameter("formSubmited").equals("editAccount")) {

            User user = new User();
            user.setPseudo(request.getParameter("pseudo"));
            user.setEmail(request.getParameter("email"));
            user.setId(Integer.parseInt(request.getParameter("id")));


            if (userDao.edit(user)) {

                session.setAttribute("currentUser", userDao.findById(((User) session.getAttribute("currentUser")).getId()));
                request.setAttribute("msn", "Vos informations personnelles ont bien été mises à jour !");

                request.setAttribute("msnType",  "OK");
            } else {
                request.setAttribute("msn", "Oups, la mise à jour a échoué.");
                request.setAttribute("msnType",  "KO");
            }
            doGet(request, response);
        }
        if (request.getParameter("formSubmited").equals("suppAccount")) {
            if (userDao.deactivate(((User) session.getAttribute("currentUser")), request.getParameter("pwdForDeactivation"))) {
                request.setAttribute("message", "Votre compte a bien été désactivé !");
                request.setAttribute("msnType",  "OK");

                session.invalidate();
                response.sendRedirect("inscription");
            }else {

                request.setAttribute("msn", "Erreur de saisie!");
                request.setAttribute("msnType",  "KO");

                doGet(request, response);
            }
        }
        if (request.getParameter("formSubmited").equals("accountPassword")) {
            if (request.getParameter("newPassword").equals(request.getParameter("confPassword"))) {

                if (BCrypt.checkpw(request.getParameter("oldPassword"),
                        ((User) session.getAttribute("currentUser")).getPassword()))
                {
                    String hashNewPwd = BCrypt.hashpw(request.getParameter("newPassword"), BCrypt.gensalt());

                    userDao.updatePassword((User) session.getAttribute("currentUser"),hashNewPwd );
                    request.getSession().invalidate();
                    request.setAttribute("msn", "Success! ");
                    request.setAttribute("msnType",  "OK");
                }

                doRedirect(request, response);
            } else {
                request.setAttribute("msn", "Erreur, les 2 pwd ne correspondent pas!");
                request.setAttribute("msnType",  "KO");
            }
        }

        doGet(request, response);
    }

}

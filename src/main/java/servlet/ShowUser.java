package servlet;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/showUser")
public class ShowUser extends HttpServlet {

    public ShowUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("user", new UserDAO().findById(id));

        request.getRequestDispatcher("/vue/admin/showUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        String email = request.getParameter("email");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
        int id = Integer.parseInt(request.getParameter("id"));

        UserDAO userDao = new UserDAO();

        User user = new User(id, pseudo, email, isAdmin);

        userDao.updateUser(user);

        doGet(request, response);
    }
}

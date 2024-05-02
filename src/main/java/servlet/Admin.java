package servlet;

import dao.GameDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin")
public class Admin extends HttpServlet {

    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        GameDAO gameDao = new GameDAO();

        request.setAttribute("users", userDao.getAll());
        request.setAttribute("games", gameDao.getAll());

        request.getRequestDispatcher("/vue/admin/admin.jsp").forward(request, response);
    }
}

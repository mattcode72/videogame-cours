package servlet;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/addGame")
public class AddGame extends HttpServlet {

    public AddGame() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlatformDAO platformDAO = new PlatformDAO();
        LangDAO langDAO = new LangDAO();
        DeveloperDAO developerDAO = new DeveloperDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        GameModeDAO gameModeDAO = new GameModeDAO();

        request.setAttribute("platforms", platformDAO.getAll());
        request.setAttribute("langs", langDAO.getAll());
        request.setAttribute("developers", developerDAO.getAll());
        request.setAttribute("categories", categoryDAO.getAll());
        request.setAttribute("gameModes", gameModeDAO.getAll());

        request.getRequestDispatcher("/vue/admin/showGame.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

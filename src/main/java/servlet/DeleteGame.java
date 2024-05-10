package servlet;

import dao.GameDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteGame extends HttpServlet {

    public DeleteGame() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GameDAO gameDAO = new GameDAO();

        int gameId = Integer.parseInt(request.getParameter("deleteGame"));

        gameDAO.delete(gameId);

        response.sendRedirect(request.getContextPath() + "/admin");
    }
}

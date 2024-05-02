package servlet;

import bean.Game;
import bean.User;
import dao.GameDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/admin/showGame")
public class ShowGame extends HttpServlet {

    public ShowGame() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("game", new GameDAO().findById(id));

        request.getRequestDispatcher("/vue/admin/showGame.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int id = Integer.parseInt(request.getParameter("id"));

        // Parser la releaseDate
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = request.getParameter("releaseDate");
        Date releaseDate;
        try {
            releaseDate = format.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        GameDAO gameDAO = new GameDAO();

        Game game = new Game(id, name, description, releaseDate, price);

        gameDAO.updateGame(game);

        doGet(request, response);
    }
}

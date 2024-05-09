package servlet;

import bean.*;
import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/admin/addGame")
@MultipartConfig
public class AddGame extends HttpServlet {
    PlatformDAO platformDAO = new PlatformDAO();
    LangDAO langDAO = new LangDAO();
    DeveloperDAO developerDAO = new DeveloperDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    GameModeDAO gameModeDAO = new GameModeDAO();
    GameDAO gameDAO = new GameDAO();
    MediaDAO mediaDAO = new MediaDAO();

    public AddGame() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("platforms", platformDAO.getAll());
        request.setAttribute("langs", langDAO.getAll());
        request.setAttribute("developers", developerDAO.getAll());
        request.setAttribute("categories", categoryDAO.getAll());
        request.setAttribute("gamemodes", gameModeDAO.getAll());

        request.getRequestDispatcher("/vue/admin/addGame.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));

        String releaseDateString = request.getParameter("releaseDate");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseDate;
        try {
            releaseDate = formatter.parse(releaseDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String[] platforms = request.getParameterValues("platforms");
        String[] langs = request.getParameterValues("langs");
        String[] developers = request.getParameterValues("developers");
        String[] categories = request.getParameterValues("categories");
        String[] gamemodes = request.getParameterValues("gamemodes");

        ArrayList<Platform> platformsList = new ArrayList<>();
        ArrayList<Lang> langsList = new ArrayList<>();
        ArrayList<Developer> developersList = new ArrayList<>();
        ArrayList<Category> categoriesList = new ArrayList<>();
        ArrayList<GameMode> gameModesList = new ArrayList<>();

        for (String platform : platforms) {
            platformsList.add(platformDAO.findById(Integer.parseInt(platform)));
        }

        for (String lang : langs) {
            langsList.add(langDAO.findById(Integer.parseInt(lang)));
        }

        for (String developer : developers) {
            developersList.add(developerDAO.findById(Integer.parseInt(developer)));
        }

        for (String category : categories) {
            categoriesList.add(categoryDAO.findById(Integer.parseInt(category)));
        }

        for (String gamemode : gamemodes) {
            gameModesList.add(gameModeDAO.findById(Integer.parseInt(gamemode)));
        }

        Part filePart = request.getPart("thumbnail");
        String fileName = filePart.getSubmittedFileName();

        Media thumbnail = new Media(true, "/assets/img/minia/" + fileName, new MediaType(1, "image"));

        Game game = new Game(name, description, releaseDate, price, platformsList, langsList, developersList, categoriesList, gameModesList, thumbnail);

        int gameId = gameDAO.addGame(game);
        game.setId(gameId);


        filePart.write(getServletContext().getRealPath("assets/img/minia") + File.separator + fileName);

        // Add images
        for (Part part : request.getParts()) {
            if (!part.getName().equals("images")) {
                continue;
            }
            String imageName = part.getSubmittedFileName();
            System.out.println(imageName);
            part.write(getServletContext().getRealPath("assets/img/fiche") + File.separator + imageName);
            mediaDAO.addMedia(new Media(false, "/assets/img/fiche/" + imageName, game, new MediaType(1, "image")));
        }

        // Add video
        String video = request.getParameter("video");
        mediaDAO.addMedia(new Media(false, video, game, new MediaType(2, "video")));

        doGet(request, response);
    }
}

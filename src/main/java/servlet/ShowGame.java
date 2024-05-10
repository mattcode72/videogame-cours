package servlet;

import bean.*;
import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

@WebServlet("/admin/showGame")
public class ShowGame extends HttpServlet {

    GameDAO gameDAO = new GameDAO();
    PlatformDAO platformDAO = new PlatformDAO();
    LangDAO langDAO = new LangDAO();
    DeveloperDAO developerDAO = new DeveloperDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    GameModeDAO gameModeDAO = new GameModeDAO();

    public ShowGame() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("game", new GameDAO().findById(id));
        request.setAttribute("platforms", platformDAO.getAll());
        request.setAttribute("langs", langDAO.getAll());
        request.setAttribute("developers", developerDAO.getAll());
        request.setAttribute("categories", categoryDAO.getAll());
        request.setAttribute("gameModes", gameModeDAO.getAll());

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

        String[] platforms = request.getParameterValues("platforms");
        String[] langs = request.getParameterValues("langs");
        String[] developers = request.getParameterValues("developers");
        String[] categories = request.getParameterValues("categories");
        String[] gamemodes = request.getParameterValues("gamemodes");

        ArrayList<Platform> platformsList = new ArrayList<>();
        ArrayList<Platform> gamePlatforms = gameDAO.findById(id).getPlatforms();

        ArrayList<Lang> langsList = new ArrayList<>();
        ArrayList<Lang> gameLangs = gameDAO.findById(id).getLangs();

        ArrayList<Developer> developersList = new ArrayList<>();
        ArrayList<Developer> gameDevelopers = gameDAO.findById(id).getDevelopers();

        ArrayList<Category> categoriesList = new ArrayList<>();
        ArrayList<Category> gameCategories = gameDAO.findById(id).getCategories();

        ArrayList<GameMode> gameModesList = new ArrayList<>();
        ArrayList<GameMode> gameGameModes = gameDAO.findById(id).getGameModes();

        for (String platform : platforms) {
            platformsList.add(platformDAO.findById(Integer.parseInt(platform)));
        }

        Iterator<Platform> iteratorPlatform = gamePlatforms.iterator();
        while (iteratorPlatform.hasNext()) {
            Platform gamePlatform = iteratorPlatform.next();
            for (Platform platform : platformsList) {
                if (gamePlatform.getId() == platform.getId()) {
                    iteratorPlatform.remove();
                    break;
                }
            }
        }


        for (String lang : langs) {
            langsList.add(langDAO.findById(Integer.parseInt(lang)));
        }

        Iterator<Lang> iteratorLangs = gameLangs.iterator();
        while (iteratorLangs.hasNext()) {
            Lang gameLang = iteratorLangs.next();
            for (Lang lang : langsList) {
                if (gameLang.getId() == lang.getId()) {
                    iteratorLangs.remove();
                    break;
                }
            }
        }

        for (String developer : developers) {
            developersList.add(developerDAO.findById(Integer.parseInt(developer)));
        }

        Iterator<Developer> iteratorDevelopers = gameDevelopers.iterator();
        while (iteratorDevelopers.hasNext()) {
            Developer gameDeveloper = iteratorDevelopers.next();
            for (Developer developer : developersList) {
                if (gameDeveloper.getId() == developer.getId()) {
                    iteratorDevelopers.remove();
                    break;
                }
            }
        }

        for (String category : categories) {
            categoriesList.add(categoryDAO.findById(Integer.parseInt(category)));
        }

        Iterator<Category> iteratorCategories = gameCategories.iterator();
        while (iteratorCategories.hasNext()) {
            Category gameCategory = iteratorCategories.next();
            for (Category category : categoriesList) {
                if (gameCategory.getId() == category.getId()) {
                    iteratorCategories.remove();
                    break;
                }
            }
        }

        for (String gamemode : gamemodes) {
            gameModesList.add(gameModeDAO.findById(Integer.parseInt(gamemode)));
        }

        Iterator<GameMode> iteratorGameModes = gameGameModes.iterator();
        while (iteratorGameModes.hasNext()) {
            GameMode gameGameMode = iteratorGameModes.next();
            for (GameMode gameMode : gameModesList) {
                if (gameGameMode.getId() == gameMode.getId()) {
                    iteratorGameModes.remove();
                    break;
                }
            }
        }

        Game gameUpdate1 = new Game(id, name, description, releaseDate, price, gamePlatforms, gameLangs, gameDevelopers, gameCategories, gameGameModes);

        gameDAO.updateGame(gameUpdate1, "delete");

        Iterator<Platform> iteratorPlatform2 = platformsList.iterator();
        while (iteratorPlatform2.hasNext()) {
            Platform gamePlatform = iteratorPlatform2.next();
            for (Platform platform : gameDAO.findById(id).getPlatforms()) {
                if (gamePlatform.getId() == platform.getId()) {
                    iteratorPlatform2.remove();
                    break;
                }
            }
        }

        Iterator<Lang> iteratorLangs2 = langsList.iterator();
        while (iteratorLangs2.hasNext()) {
            Lang gameLang = iteratorLangs2.next();
            for (Lang lang : gameDAO.findById(id).getLangs()) {
                if (gameLang.getId() == lang.getId()) {
                    iteratorLangs2.remove();
                    break;
                }
            }
        }

        Iterator<Developer> iteratorDevelopers2 = developersList.iterator();
        while (iteratorDevelopers2.hasNext()) {
            Developer gameDeveloper = iteratorDevelopers2.next();
            for (Developer developer : gameDAO.findById(id).getDevelopers()) {
                if (gameDeveloper.getId() == developer.getId()) {
                    iteratorDevelopers2.remove();
                    break;
                }
            }
        }

        Iterator<Category> iteratorCategories2 = categoriesList.iterator();
        while (iteratorCategories2.hasNext()) {
            Category gameCategory = iteratorCategories2.next();
            for (Category category : gameDAO.findById(id).getCategories()) {
                if (gameCategory.getId() == category.getId()) {
                    iteratorCategories2.remove();
                    break;
                }
            }
        }

        Iterator<GameMode> iteratorGameModes2 = gameModesList.iterator();
        while (iteratorGameModes2.hasNext()) {
            GameMode gameGameMode = iteratorGameModes2.next();
            for (GameMode gameMode : gameDAO.findById(id).getGameModes()) {
                if (gameGameMode.getId() == gameMode.getId()) {
                    iteratorGameModes2.remove();
                    break;
                }
            }
        }

        Game gameUpdate2 = new Game(id, name, description, releaseDate, price, platformsList, langsList, developersList, categoriesList, gameModesList);

        gameDAO.updateGame(gameUpdate2, "add");

        doGet(request, response);
    }
}

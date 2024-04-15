package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connexion = null;

    public static void Connect() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/videogame";
            String user = "root";
            String mdp = "";
            connexion = DriverManager.getConnection(url, user, mdp);
            System.out.println("OK for connect");

        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.err.println(ex.getMessage());
        }
    }
}

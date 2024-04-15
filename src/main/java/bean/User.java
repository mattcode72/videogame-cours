package bean;

public class User {

    private int id;
    private String pseudo;
    private String password;
    private String email;
    private boolean isAdmin;

    public User() {
    }

    public User(String pseudo, String password, String email, boolean isAdmin) {
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public User(int id, String pseudo, String password, String email, boolean isAdmin) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

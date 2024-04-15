package bean;

public class GameMode {
    private int id;
    private String name;

    public GameMode() {
    }

    public GameMode(String name) {
        this.name = name;
    }

    public GameMode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package bean;

public class Platform {
    private int id;
    private String name;

    public Platform() {
    }

    public Platform(String name) {
        this.name = name;
    }

    public Platform(int id, String name) {
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

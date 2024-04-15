package bean;

public class Lang {
    private int id;
    private String name;

    public Lang() {
    }

    public Lang(String name) {
        this.name = name;
    }

    public Lang(int id, String name) {
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

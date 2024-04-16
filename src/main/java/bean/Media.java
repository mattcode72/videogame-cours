package bean;

public class Media {
    private int id;

    private String filename;
    private String path;

    private Game game;

    private MediaType mediaType;

    public Media() {
    }

    public Media(String filename, String path, Game game, MediaType mediaType) {
        this.filename = filename;
        this.path = path;
        this.game = game;
        this.mediaType = mediaType;
    }

    public Media(int id, String filename, String path, Game game, MediaType mediaType) {
        this.id = id;
        this.filename = filename;
        this.path = path;
        this.game = game;
        this.mediaType = mediaType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}

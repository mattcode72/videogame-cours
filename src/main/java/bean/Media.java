package bean;

public class Media {
    private int id;
    private String path;
    private Boolean isThumbnail;
    private Game game;
    private MediaType mediaType;

    public Media() {
    }

    public Media(Boolean isThumbnail, String path, Game game, MediaType mediaType) {
        this.isThumbnail = isThumbnail;
        this.path = path;
        this.game = game;
        this.mediaType = mediaType;
    }

    public Media(int id, Boolean isThumbnail, String path, Game game, MediaType mediaType) {
        this.id = id;
        this.isThumbnail = isThumbnail;
        this.path = path;
        this.game = game;
        this.mediaType = mediaType;
    }

    public Media(int id, Boolean isThumbnail, String path) {
        this.id = id;
        this.isThumbnail = isThumbnail;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getThumbnail() {
        return isThumbnail;
    }

    public void setThumbnail(Boolean isThumbnail) {
        this.isThumbnail = isThumbnail;
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

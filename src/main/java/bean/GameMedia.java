package bean;

public class GameMedia {
    private Game game;
    private Media media;

    public GameMedia() {
    }

    public GameMedia(Game game, Media media) {
        super();
        this.game = game;
        this.media = media;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}

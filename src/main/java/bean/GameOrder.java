package bean;

public class GameOrder {
    private Game game;
    private Order order;
    private int quantity;
    private Boolean isOrdered;

    public GameOrder() {
    }

    public GameOrder(Game game, Order order, int quantity, Boolean isOrdered) {
        this.game = game;
        this.order = order;
        this.quantity = quantity;
        this.isOrdered = isOrdered;
    }

    public GameOrder(Game game, Order order, int quantity) {
        this.game = game;
        this.order = order;
        this.quantity = quantity;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getIsOrdered() {
        return isOrdered;
    }

    public void setIsOrdered(Boolean isOrdered) {
        this.isOrdered = isOrdered;
    }
}

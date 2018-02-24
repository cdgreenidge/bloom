package old;

public class Order {
    int clientId;
    boolean isBuy;
    int quantity;
    double price;
    int orderId;

    int time;

    public Order(String input, int time) {
        String[] parts = input.split(" ");
        this.clientId = Integer.parseInt(parts[0].substring(1));
        this.isBuy = (parts[1].equals("B"));
        this.quantity = Integer.parseInt(parts[2]);
        this.price = Double.parseDouble(parts[3]);
        this.orderId = Integer.parseInt(parts[4]);
        this.time = time;
    }

    public Order(int clientId, boolean isBuy, int quantity, double price, int orderId, int time) {
        this.clientId = clientId;
        this.isBuy = isBuy;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;

        this.time = time;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("C%04d.%05d ", this.clientId, this.orderId));
        builder.append(this.quantity);
        builder.append(String.format(" %.2f", this.price));

        return String.format("C%04d.%05d %d %.2f", this.clientId, this.orderId, this.quantity, this.price);
        return builder.toString();
    }

    public String confirm() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("C%04d.%05d cnfrm ", this.clientId, this.orderId));
        builder.append(this.isBuy ? "B " : "S ");
        builder.append(this.quantity);
        builder.append(String.format(" %.2f", this.price));
        return builder.toString();
    }

    public int getClientId() {
        return clientId;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTime() {
        return time;
    }
}

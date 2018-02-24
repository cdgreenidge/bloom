import java.io.File;
import java.io.FileInputStream;
import java.util.Comparator;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

public class Solution {
    OrderBook book;

    public static class Order {
        int clientId;
        boolean isBuy;
        int quantity;
        double price;
        int orderId;

        int time;

        public Order(String input, int time_) {
            String[] parts = input.split(" ");
            clientId = Integer.parseInt(parts[0].substring(1));
            isBuy = (parts[1].equals("B"));
            quantity = Integer.parseInt(parts[2]);
            price = Double.parseDouble(parts[3]);
            orderId = Integer.parseInt(parts[4]);
            time = time_;
        }

        public String toString() {
            // StringBuilder builder = new StringBuilder();
            // builder.append(String.format("C%04d.%05d ", clientId, orderId));
            // builder.append(quantity);
            // builder.append(String.format(" %.2f", price));
            // return builder.toString();
            return String.format("C%04d.%05d %d %.2f", this.clientId, this.orderId, this.quantity, this.price);
        }

        public String confirm() {
             // StringBuilder builder = new StringBuilder();
             // builder.append(String.format("C%04d.%05d cnfrm ", clientId, orderId));
             // builder.append(isBuy ? "B " : "S ");
             // builder.append(quantity);
             // builder.append(String.format(" %.2f", price));
             // return builder.toString();
            if (isBuy) {
                return String.format("C%04d.%05d cnfrm B %d %.2f", this.clientId, this.orderId, this.quantity,
                        this.price);
            } else {
                return String.format("C%04d.%05d cnfrm S %d %.2f", this.clientId, this.orderId, this.quantity,
                        this.price);
            }
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

    public static class BidComparator implements Comparator<Order> {

        public int compare(Order o1, Order o2) {
            if (o1.getPrice() == o2.getPrice()) {
                return o1.getTime() - o2.getTime();
            } else {
                return o1.getPrice() > o2.getPrice() ? -1 : +1;
            }
        }

    }

    public static class AskComparator implements Comparator<Order> {

        public int compare(Order o1, Order o2) {
            if (o1.getPrice() == o2.getPrice()) {
                return o1.getTime() - o2.getTime();
            } else {
                return o1.getPrice() < o2.getPrice() ? -1 : +1;
            }
        }
    }

    public static class OrderBook {
        PriorityQueue<Order> bids;
        PriorityQueue<Order> asks;

        int time;

        public OrderBook() {
            bids = new PriorityQueue<>(new BidComparator());
            asks = new PriorityQueue<>(new AskComparator());
            time = 0;
        }

        public void addOrder(String orderStr) {
            Order order = new Order(orderStr, time);
            if (order.isBuy()) {
                bids.add(order);
            } else {
                asks.add(order);
            }
             // System.out.println(order.confirm());
            time++;
        }

        public void dump() {
            System.out.println("bid:");
            while (!bids.isEmpty()) {
                // System.out.println(bids.poll().toString());
                bids.poll().toString();
            }
            System.out.println("ask:");
            while (!asks.isEmpty()) {
                asks.poll().toString();
            }
        }
    }

    public static void main(String[] args) {
//        try {
//            TimeUnit.SECONDS.sleep(15);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
        try {
            FileInputStream is = new FileInputStream(new File(args[0]));
            System.setIn(is);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        OrderBook book = new OrderBook();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            book.addOrder(scan.nextLine());
        }
        System.out.print("\n");
        book.dump();
    }
}

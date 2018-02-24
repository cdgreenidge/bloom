package old;

import java.util.PriorityQueue;

public class OrderBook {
    PriorityQueue<Order> bids;
    PriorityQueue<Order> asks;

    int time;

    public OrderBook() {
        bids = new PriorityQueue<>(new BidComparator());
        asks = new PriorityQueue<>(new AskComparator());
        time = 0;
    }

    public void addOrder(String orderStr) {
        Order order = new Order(orderStr, this.time);
        if (order.isBuy()) {
            bids.add(order);
        } else {
            asks.add(order);
        }
        System.out.println(order.confirm());
        this.time++;
    }

    public String dump() {
        StringBuilder builder = new StringBuilder();
        builder.append("bid:\n");
        while (!bids.isEmpty()) {
            builder.append(bids.poll().toString() + "\n");
        }
        builder.append("ask:\n");
        while (!asks.isEmpty()) {
            builder.append(asks.poll().toString() + "\n");
        }
        return builder.toString();
    }
}

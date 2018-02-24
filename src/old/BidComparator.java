package old;

import java.util.Comparator;

public class BidComparator implements Comparator<Order> {

    public int compare(Order o1, Order o2) {
        if (o1.getPrice() == o2.getPrice()) {
            return o1.getTime() - o2.getTime();
        } else {
            return o1.getPrice() < o2.getPrice() ? -1 : +1;
        }
    }

}
package order;

import java.util.ArrayList;
import java.util.List;


public class Order {
    private double totalPrice = 0;
    List<OrderProduct> orderProductList = new ArrayList<>();
    public  double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice() {
        totalPrice = 0;
        for(OrderProduct p:orderProductList) {
            totalPrice+=p.getPrice()*p.getNum();
        }
    }
    public void setOrderProductList(OrderProduct orderProduct) {
        orderProductList.add(orderProduct);
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }
}

package service.Impl;

import order.Order;
import order.OrderProduct;
import product.Product;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    Order order = new Order();
    @Override
    public void addOrder(Product product,int num) {
        OrderProduct orderProduct = new OrderProduct(product.getId(),product.getName(), num, product.getPrice(),product.getType());
        order.setOrderProductList(orderProduct);
        order.setTotalPrice();

    }

    @Override
    public void printOrder() {
        List<OrderProduct> orderProduct = order.getOrderProductList();
        for(OrderProduct p:orderProduct){
            System.out.println("商品名称："+p.getName()+"  购买数量："+p.getNum()+"  商品单价："+p.getPrice());
        }
        System.out.println("总计："+order.getTotalPrice());
    }

    public List<OrderProduct> getOrder() {
        return order.getOrderProductList();
    }
}

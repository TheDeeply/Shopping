package service;

import order.OrderProduct;
import product.Product;

import java.util.List;

public interface OrderService {
    void addOrder(Product product, int num);
    void printOrder();
    List<OrderProduct> getOrder();
}

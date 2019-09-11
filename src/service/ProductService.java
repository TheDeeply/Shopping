package service;

import product.Product;

import java.sql.SQLException;
import java.util.List;

abstract public interface ProductService {
    List<Product> search(String info);
    void buy(OrderService orderService) throws SQLException;
}

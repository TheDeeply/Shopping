package service;

import product.Product;

import java.sql.SQLException;
import java.util.List;

public interface PhoneService {
    List<Product> search(String info);
    void buy(OrderService orderService) throws SQLException;
    void setPhone() throws SQLException;
    void buyById(OrderService orderService,Integer id,Integer num) throws SQLException;
}

package service;

import product.Product;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    List<Product> search(String info);
//    boolean search(String info);
    void buyById(OrderService orderService, Integer id, Integer num) throws SQLException;
    void buy(OrderService orderService) throws SQLException;
    void setBook() throws SQLException;
}

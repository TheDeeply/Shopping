package service.Impl;

import product.Book;
import product.Product;
import service.BookService;
import service.OrderService;
import user.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BookServiceImpl extends ProductServiceImpl implements BookService {
    @Override
    public List<Product> search(String searchInfo) {
        List<Product> books = new ArrayList<>();
        String pattern = ".*"+searchInfo+".*";
        boolean nameMatch ;
        boolean pressMatch ;
        boolean authorMatch ;
        boolean typeMatch;
        for(Book p: Database.bookList) {
                nameMatch = Pattern.matches(pattern,p.getName());
                pressMatch = Pattern.matches(pattern,p.getPress());
                authorMatch = Pattern.matches(pattern,p.getAuthor());
                typeMatch = Pattern.matches(pattern,p.getType());
                if(nameMatch||pressMatch||authorMatch||typeMatch) {
                    //p.show();
                    books.add(p);
                }
        }
        return books;
    }

//    public boolean search(String info) {
//        boolean is = false;
//        String pattern = ".*"+info+".*";
//        boolean nameMatch ;
//        boolean pressMatch ;
//        boolean authorMatch ;
//        for(Book p: Database.bookList) {
//                nameMatch = Pattern.matches(pattern,p.getName());
//                pressMatch = Pattern.matches(pattern,p.getPress());
//                authorMatch = Pattern.matches(pattern,p.getAuthor());
//                if(nameMatch||pressMatch||authorMatch) {
//                    p.show();
//                    is = true;
//                }
//        }
//        return is;
//    }
public void buyById(OrderService orderService,Integer id,Integer num) throws SQLException {
    for (Book p : Database.bookList) {
        if (p.getId() == id) {
            Integer sum = p.getNum() - num;
            p.setNum(sum);
            Statement st = Database.con.createStatement();
            String sql = "update book set storage = ";

            sql += sum.toString();
            sql += " where id =";
            sql += id.toString();
            st.executeUpdate(sql);
            orderService.addOrder(p,num);
        }
    }
}


        public void buy(OrderService orderService) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean isbreak = true;
        boolean is = false;
        while(isbreak) {
            System.out.println("请输入商品id：");
            Integer id = sc.nextInt();
            is = false;
            for (Book p:Database.bookList) {
                if(p.getId()==id) {
                    is = true;
                    System.out.println("商品信息如下：");
                    p.show();
                    System.out.println("请输入购买数量：");
                    int num = sc.nextInt();
                    if(num>p.getNum()) {
                        System.out.println("库存不足");
                        break;
                    }
                    Integer sum = p.getNum()-num;
                    p.setNum(sum);
                    Statement st = Database.con.createStatement();
                    String sql = "update book set storage = ";

                    sql += sum.toString();
                    sql += " where id =";
                    sql += id.toString();
                    st.executeUpdate(sql);


                    orderService.addOrder(p,num);
                    System.out.println("购买成功"+"\n"+"是否继续：Y/N");
                    String  choice = sc.next();
                    if("Y".equals(choice)) isbreak = true;
                    else isbreak = false;
                }
            }
            if(!is) System.out.println("输入错误");
        }
    }

    @Override
    public void setBook() throws SQLException {
        Statement st = Database.con.createStatement();
        String sql = "select * from book";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()) {
            Book book = new Book(rs.getInt("id"),rs.getString("name"),rs.getDouble("price"),rs.getInt("storage"),rs.getString("press"),rs.getString("author"),rs.getString("type"));
            Database.bookList.add(book);
        }
    }
}

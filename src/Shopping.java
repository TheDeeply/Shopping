import product.Book;
import product.Phone;
import service.*;
import service.Impl.*;
import user.Database;

import java.sql.SQLException;
import java.util.Scanner;

public class Shopping {
    static Scanner sc = new Scanner(System.in);
    static BookService bookService = new BookServiceImpl();
    static PhoneService phoneService = new PhoneServiceImpl();

    static void showList() throws SQLException {
        System.out.println("图书：");
        for(Book p: Database.bookList) {
            p.show();
        }
        System.out.println("手机：");
        for (Phone p:Database.phoneList) {
            p.show();
        }
    }

    public static void main(String[] args) throws SQLException {



        boolean isbreak = true;
        while(isbreak) {
            System.out.print("请输入账号：");
            String username = sc.nextLine();
            System.out.print("请输入密码：");
            String pwd = sc.nextLine();
            UserService userService = new UserServiceImpl();
            OrderService orderService = new OrderServiceImpl();
            ProductService productService = new ProductServiceImpl();
            while(userService.login(username,pwd)) {
                System.out.println("1:浏览商品    2:搜索商品    3:购买     4:查看购物单    5:退出");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: Shopping.showList();break;
                    case 2:
                            System.out.println("请输入搜索关键字：");
                            String info = sc.next();
                            bookService.search(info);
                            phoneService.search(info);
                            break;
                    case 3: {
                        System.out.println("请输入想购买的商品种类：");
                        System.out.println("1：手机    2：图书");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:phoneService.buy(orderService);break;
                            case 2:bookService.buy(orderService);break;
                        }
                    };break;
                    case 4: {
                        System.out.println("用户："+Database.user.getusername());
                        orderService.printOrder();
                    }break;
                    case 5: return;
                }
            }
        }

    }
}

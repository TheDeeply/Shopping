package user;

import product.Book;
import product.Phone;
import product.Product;
import service.BookService;
import service.Impl.BookServiceImpl;
import service.Impl.PhoneServiceImpl;
import service.PhoneService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<Product> productList = new ArrayList<>();
    public static List<Phone> phoneList = new ArrayList<>();
    public static List<Book> bookList  = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();
    public static Connection con = null;
    public static User user = new User();
    public static boolean loginState = false;
    static BookService bookService = new BookServiceImpl();
    static PhoneService phoneService = new PhoneServiceImpl();
    static {


        try{
            //加载MySql的驱动类
            Class.forName("com.mysql.jdbc.Driver") ;
        }catch(ClassNotFoundException e){
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace() ;
        }
        String url = "jdbc:mysql://localhost:3306/shopping?serverTimezone=UTC" ;
        String user_name = "root" ;
        String password = "" ;

        try{
            con = DriverManager.getConnection(url , user_name , password ) ;
        }catch(SQLException se){
            System.out.println("数据库连接失败！");
            se.printStackTrace() ;
        }


        try {
            Statement st = Database.con.createStatement();
            String sql = "select * from user";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                User user = new User(rs.getString("username"),rs.getString("pwd"),rs.getInt("user_id"));
                Database.userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            bookService.setBook();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            phoneService.setPhone();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

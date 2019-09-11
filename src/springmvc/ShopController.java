package springmvc;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import product.Book;
import product.Phone;
import service.BookService;
import service.Impl.BookServiceImpl;
import service.Impl.OrderServiceImpl;
import service.Impl.PhoneServiceImpl;
import service.OrderService;
import service.PhoneService;
import user.Database;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@Controller
public class ShopController {
    static BookService bookService = new BookServiceImpl();
    static PhoneService phoneService = new PhoneServiceImpl();
    static OrderService orderService = new OrderServiceImpl();

    @RequestMapping("/showList")
    @ResponseBody
    public String showBook(){
        String book = JSON.toJSON(Database.bookList).toString();
        String phone = JSON.toJSON(Database.phoneList).toString();
        String product = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+ book.substring(0,book.length()-1)+","+phone.substring(1,phone.length()) +"}";
        System.out.println(product);
        return product;
    }

    @RequestMapping(value = "/showProduct",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String showProduct(HttpServletRequest request) throws UnsupportedEncodingException {
        String Id = request.getParameter("id");
        if(Id==null) return null;
        int id = Integer.parseInt(Id);
        String head = "<div style=\"padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;\">";
        for(Book b:Database.bookList) {
            if(b.getId()==id) {

                String con = head + "id："+b.getId()+"<br>商品名称："+b.getName()+"<br>商品类型："+b.getType()+"<br>出版社："+b.getPress()+"<br>作者："+b.getAuthor()+"<br>单价："+b.getPrice()+"<br>库存："+b.getNum();
                System.out.println(con);
                return con;
            }
        }
        for(Phone b:Database.phoneList) {
            if(b.getId()==id) {
                String con = head+"id："+b.getId()+"<br>商品名称："+b.getName()+"<br>商品类型："+b.getType()+"<br>系统："+b.getSystem()+"<br>品牌："+b.getBrand()+"<br>颜色："+b.getColor()+"<br>单价："+b.getPrice()+"<br>库存："+b.getNum();
                System.out.println(con);
                return con;
            }
        }
        return null;
    }

    @RequestMapping("/buyById")
    @ResponseBody
    public String buyById(HttpServletRequest request) throws SQLException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer num = Integer.parseInt(request.getParameter("num"));
        System.out.println(num);
        bookService.buyById(orderService,id,num);
        phoneService.buyById(orderService,id,num);
        return "ok";
    }

    @RequestMapping("/search")
    @ResponseBody
    public String search(HttpServletRequest request) {
        String searchInfo = request.getParameter("searchInfo");
        System.out.println(searchInfo);
        String result1 = JSON.toJSON(bookService.search(searchInfo)).toString();
        String result2 = JSON.toJSON(phoneService.search(searchInfo)).toString();
        String Res = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+result1.substring(0,result1.length()-1)+result2.substring(1,result2.length())+"}";
        return Res;
    }

    @RequestMapping("/showOrder")
    @ResponseBody
    public String showOrder() {
        String order = JSON.toJSON(orderService.getOrder()).toString();
        String result = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+order+"}";
        return result;
    }
}

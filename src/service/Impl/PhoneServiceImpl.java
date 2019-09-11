package service.Impl;

import product.Phone;
import product.Product;
import service.OrderService;
import service.PhoneService;
import user.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PhoneServiceImpl implements PhoneService {


    public List<Product> search(String info) {
        List<Product> phones = new ArrayList<>();
        String pattern = ".*"+info+".*";
        boolean nameMatch ;
        boolean brandMatch ;
        boolean systemMatch ;
        boolean colorMatch;
        boolean typeMatch;
        for(Phone p: Database.phoneList) {
            nameMatch = Pattern.matches(pattern,p.getName());
            brandMatch = Pattern.matches(pattern,p.getBrand());
            systemMatch = Pattern.matches(pattern,p.getSystem());
            colorMatch = Pattern.matches(pattern,p.getColor());
            typeMatch = Pattern.matches(pattern,p.getType());
            if(nameMatch||brandMatch||systemMatch||colorMatch||typeMatch) {
                //p.show();
                phones.add(p);
            }
        }
        return phones;
    }

    @Override
    public void buy(OrderService orderService) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean isbreak = true;
        boolean is = false;
        while(isbreak) {
            System.out.println("请输入商品id：");
            Integer id = sc.nextInt();
            is = false;
            for (Phone p: Database.phoneList) {
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
                    String sql = "update phone set storage = ";

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

    public void buyById(OrderService orderService,Integer id,Integer num) throws SQLException {
        for (Phone p : Database.phoneList) {
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

    @Override
    public void setPhone() throws SQLException {
        Statement st = Database.con.createStatement();
        String sql = "select * from phone";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()) {
            Phone phone = new Phone(rs.getInt("id"),rs.getString("name"),rs.getDouble("price"),rs.getInt("storage"),rs.getString("system"),rs.getString("brand"),rs.getString("color"),rs.getString("type"));
            Database.phoneList.add(phone);
        }
    }
}

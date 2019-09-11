package service.Impl;

import product.Product;
import service.OrderService;
import service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    public List<Product> search(String searchInfo){
       return null;
    }

    @Override
    public void buy(OrderService orderService) throws SQLException {

    }

    //    public void buy(OrderService orderService) {
//        Scanner sc = new Scanner(System.in);
//        boolean isbreak = true;
//        while(isbreak) {
//            System.out.println("请输入商品id：");
//            int id = sc.nextInt();
//            for (Product p:Database.productList) {
//                if(p.getId()==id) {
//                    System.out.println("商品信息如下：");
//                    p.show();
//                    System.out.println("请输入购买数量：");
//                    int num = sc.nextInt();
//                    if(num>p.getNum()) {
//                        System.out.println("库存不足");
//                        break;
//                    }
//                    p.setNum(p.getNum()-num);
//                    orderService.addOrder(p,num);
//                    System.out.println("购买成功"+"\n"+"是否继续：Y/N");
//                    String  choice = sc.next();
//                    if("Y".equals(choice)) isbreak = true;
//                    else isbreak = false;
//                }
//            }
//        }
//    }
}

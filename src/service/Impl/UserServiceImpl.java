package service.Impl;

import order.Order;
import service.UserService;
import user.Database;
import user.User;

public class UserServiceImpl implements UserService {

    @Override
    public boolean login(String name, String pwd) {
        boolean isname = true;
        for(User u: Database.userList) {
            if(!name.equals(u.getusername())) {
                isname = false;
            }else {
                if(!pwd.equals(u.getPwd())) {
                    System.out.println("密码错误");
                    isname = true;
                    return false;
                }else {
                    Order order = new Order();
                    Database.user.setusername(name);
                    Database.user.setId(u.getId());
                    return true;
                }
            }
        }
        if(isname==false) System.out.println("用户名不存在");
        return false;
    }
}

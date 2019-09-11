package springmvc.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import user.Database;
import user.User;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {


    @RequestMapping("/login")
    public String register(){
        return "login";
    }


    @RequestMapping("/main")
    public String themain(){
        return "main";
    }


    @RequestMapping("/loginCheck")
    @ResponseBody
    public String loginCheck(HttpServletRequest request){
        String loginname = request.getParameter("loginname");
        String password = request.getParameter("password");
        for(User u: Database.userList) {
            System.out.println(u.getusername());
            if(u.getusername().equals(loginname)&&u.getPwd().equals(password)) return "true";
        }
        return "false";
    }


}

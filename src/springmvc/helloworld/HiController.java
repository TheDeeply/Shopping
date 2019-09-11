package springmvc.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hi")
public class HiController {

    @RequestMapping("/say")
    public String say(Model model) {
        model.addAttribute("name","wormday");
        model.addAttribute("url","http://www.cnblogs.com/wormday/p/8435617.html");
        return "say";
    }
}
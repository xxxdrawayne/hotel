package hotel.com.jd.controller;

import hotel.com.jd.domain.Manager;
import hotel.com.jd.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;

@Controller("/main")
public class MainController {
    @Autowired
    @Qualifier("managerService")
    private ManagerService managerService;
    @RequestMapping("login")
    public ModelAndView login(String loginName, String password, ModelAndView mv, HttpSession session){
        Manager manager = managerService.login(loginName,password);
        if(manager != null)
        {
            // 登录成功，将user对象设置到HttpSession作用范围域
            session.setAttribute("manager", manager);
           mv.setView(new RedirectView("/index.html"));
        }
        else
        {
            // 登录失败，设置失败提示信息，并跳转到登录页面
            mv.setView(new RedirectView("/login.html"));
        }
        return mv;
    }

}

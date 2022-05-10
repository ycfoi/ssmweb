package com.atguigu.web.controller;

import com.atguigu.web.pojo.User;
import com.atguigu.web.service.UserService;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author shkstart
 * @create 2022-03-13 16:01
 */

@Controller
@RequestMapping("/user")
public class UserController  {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loginsend",method = RequestMethod.POST)
    public  String login (String username, String password, HttpSession session, Model model) throws ServletException, IOException {
        //调用userService的方法查询是否存在此用户
        User user=userService.login(new User(null,username,password,null));
        //判断user是否为空
        if(user!=null){
            //将user存入session
            session.setAttribute("user",user);
            //转发到登录成功页面
            return "/user/login_success";
        }else{
            //将错误信息存入到request中
            model.addAttribute("msg","用户名或密码错误");
           model.addAttribute("username",username);
            //转发到登录页面
            return "/user/login";
        }
    }
    //注销用户
    @RequestMapping("/logout")
    public  String logout (HttpSession session,HttpServletRequest request) throws ServletException, IOException {
        //使得session失效
        session.invalidate();
      return  "redirect:/client/index";
    }
   @RequestMapping("/registsend")
    public  String  regist(HttpSession session,User user,String code,Model model) throws IOException, ServletException {


        String kap=  (String)session.getAttribute(KAPTCHA_SESSION_KEY );

        session.removeAttribute(KAPTCHA_SESSION_KEY);

        if(kap!=null&&kap.equalsIgnoreCase(code)){


            if(userService.existUsername(user.getUsername())){
                //7.1设置返回参数
                System.out.println("用户已经存在");
                model.addAttribute("msg","用户已存在");
                model.addAttribute("username",user.getUsername());
                model.addAttribute("email",user.getEmail());
                //7.2转发到注册页面
                return "/user/regist";
            }else{
                //7.1.添加新用户
                userService.registUser(user);
                System.out.println("用户注册成功");
                //7.2.重定向到登录页面(注意此中只能使用重定向，不能使用转发)，如果使用转发会导致表单重复提交
                return   "redirect:/user/regist_success";
            }
        }else{
            //设置返回参数
            System.out.println("验证码错误");
            model.addAttribute("msg","验证码错误");
            model.addAttribute("username",user.getUsername());
            model.addAttribute("email",user.getEmail());
           return "/user/regist";
        }
    }
    @RequestMapping("/ajaxUsername")
    @ResponseBody
    public Map ajaxUsername(String username) throws IOException {


        //2.判断用户名是否可用
        Boolean exist = userService.existUsername(username);

        //4.创建map对象
        Map<String ,Object> map=new HashMap<>();
        //5.将结果放入map中
        map.put("exist",exist);
        //6.将map转化为json格式的字符串

        //7.将数据回传到客户端
       return  map;

    }

}

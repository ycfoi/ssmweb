package com.atguigu.web.controller;


import com.atguigu.web.pojo.*;
import com.atguigu.web.service.OrderService;
import com.atguigu.web.utils.PaseUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2022-03-16 13:44
 */
@Controller
@RequestMapping("/order")
public class OrderController  {
      @Autowired
      private OrderService orderService;
    @RequestMapping("/createorder")
    public String createOrder(HttpServletRequest request) throws ServletException, IOException {
       //获得购物车
        Cart cart=(Cart)request.getSession().getAttribute("cart");
        //获得用户
        User user=(User) request.getSession().getAttribute("user");
        //判断用户是否为空
        if(user==null){
            return "/user/login";
        }else{
            //获取订单id，用户回显页面
            String orderId = orderService.createOrder(cart,user.getId());
            //保存id到session域中
            request.getSession().setAttribute("orderId",orderId);
            return "/cart/checkout";
        }


    }

    @RequestMapping("/sendOrder")
    public String sendOrder(String orderId,HttpServletRequest request,Integer pageNo) throws ServletException, IOException {


        //通过id改变订单状态
        orderService.sendOrder(orderId);
        return "redirect:/order/page?pageNo="+pageNo;

    }
    @RequestMapping("/showOrderDetail")
    public String    showOrderDetail(String orderId, HttpSession session) throws ServletException, IOException {
        //查询对应订单的商品项详情
         List<OrderItem>orderItems  = orderService.showOrderDetail(orderId);
         session.setAttribute("orderItems",orderItems);
        return "/order/order_item";
    }

    @RequestMapping("/showMyOrder")
    public String showMyOrders(HttpServletRequest req) throws ServletException, IOException {
        //获取用户信息
        User user=(User) req.getSession().getAttribute("user");
        if(user==null){
            return "/user/login";
        }
        //查看用户的订单
       List<Order> order = orderService.showMyOrder(user.getId());
        req.getSession().setAttribute("myorder",order);
      return "/order/order";
    }

@RequestMapping("/receiverOrder")
public String   receiverOrder(HttpServletRequest req) throws IOException {
    String id = req.getParameter("orderId");
    orderService.receiverOrder(id);
    return "redirect:/order/showMyOrder";


}
     @RequestMapping("/page")
    public String  page(HttpServletRequest req) throws ServletException, IOException {
        Integer pageNo= PaseUtils.paserInt(req.getParameter("pageNo"),1);
        Integer pageSize=PaseUtils.paserInt(req.getParameter("pageSize"), 4);
        PageInfo pages=orderService.pages(pageNo,pageSize);
//        pages.setUrl("orderServlet?action=page");
        req.setAttribute("url","order/page");
        req.setAttribute("page",pages);
       return "/manager/order_manager";
    }

    }

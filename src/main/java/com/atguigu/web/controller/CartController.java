package com.atguigu.web.controller;
import com.atguigu.web.pojo.Book;
import com.atguigu.web.pojo.Cart;
import com.atguigu.web.pojo.CartItem;
import com.atguigu.web.service.BookService;
import com.atguigu.web.utils.PaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart
 * @create 2022-03-15 16:07
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
  private BookService bookService;
    //删除图书
    @RequestMapping("/delete")
    public String delete(HttpServletRequest req) throws IOException {
       //获取图书id
       int id=PaseUtils.paserInt(req.getParameter("id"),0);
       //获取购物车
       Cart cart=(Cart)req.getSession().getAttribute("cart");
       //判断购物车是否为空
       if(cart!=null){
           cart.delete(id);
       }
        return "redirect:"+req.getHeader("Referer");

    }
    //清除购物车
    @RequestMapping("/clear")
    public String clear(HttpServletRequest req) throws IOException {
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();

        }
        return "redirect:"+req.getHeader("Re ferer");
    }
    //修改购物车数量
    @RequestMapping("/update")
    public String updateCount(HttpServletRequest req) throws IOException {
       //获取商品项中的id
       int id=PaseUtils.paserInt(req.getParameter("id"),0);
       //获取数量
       int count=PaseUtils.paserInt(req.getParameter("count"),0);
      //获取购物车
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        //判断购物车是否为空
        if(cart!=null){
            cart.update(id,count);

        }
//        Referer: http://localhost:8080/ssm/client/index
        return "redirect:"+req.getHeader("Referer");
    }


    //异步发送请求接受方法
    @RequestMapping("/ajaxaddItem")
    @ResponseBody
    public Map ajaxAddItem(HttpServletRequest req) throws ServletException, IOException {
        //获取id
       int id= PaseUtils.paserInt(req.getParameter("id"),0);
       //查询对应图书
        Book book = bookService.queryBookandOne(id);

        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart==null){
            cart =new Cart();
            cart.addItem(new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice()));
            req.getSession().setAttribute("cart",cart);
        }else {
            cart.addItem(new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice()));
        }
        req.getSession().setAttribute("lastName",book.getName());
//        //创建gson对象
//        Gson gson=new Gson();
        //构建map集合
        Map<String ,Object> map=new HashMap<>();
        //将参数放入集合中
        map.put("totalCount",cart.getTotalCount());
        map.put("lastname",book.getName());
//        String json=gson.toJson(map);
        //回传给客户端
        return map;
    }

}

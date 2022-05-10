package com.atguigu.web.dao;


import com.atguigu.web.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-03-16 9:37
 */
public interface OrderDao {
    public int saveOrder(Order order);
    public List<Order> queryOrders();
    public void changeOrderStatus(@Param("orderId") String orderId,@Param("status") Integer status);
       public List<Order>   queryOrdersByUserId(Integer userId);
//     public  List<Order> queryBookBypage(int begin, Integer pageSize);
//    Integer querygetString();
}

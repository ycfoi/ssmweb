package com.atguigu.web.service;

import com.atguigu.web.pojo.Cart;
import com.atguigu.web.pojo.Order;
import com.atguigu.web.pojo.OrderItem;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-03-16 13:05
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
    public List<Order> showAllOrders();
    public  void sendOrder(String orderId);
    public List<Order> showMyOrder(Integer userId);
    public List<OrderItem> showOrderDetail(String orderId);
    public void receiverOrder(String orderId);
    public PageInfo pages(Integer pageNo, Integer pageSize);
}

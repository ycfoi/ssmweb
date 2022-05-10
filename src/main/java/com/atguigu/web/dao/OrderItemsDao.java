package com.atguigu.web.dao;

import com.atguigu.web.pojo.OrderItem;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-03-16 9:41
 */
public interface OrderItemsDao {
  public int saveOrderItem(OrderItem orederItem);
  public List<OrderItem> queryOrderItemsByOrderId(String orderId);

}

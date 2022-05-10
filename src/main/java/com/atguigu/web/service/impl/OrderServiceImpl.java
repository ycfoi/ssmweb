package com.atguigu.web.service.impl;

import com.atguigu.web.dao.BookDao;
import com.atguigu.web.dao.OrderDao;
import com.atguigu.web.dao.OrderItemsDao;

import com.atguigu.web.pojo.*;
import com.atguigu.web.service.OrderService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2022-03-16 13:10
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private OrderItemsDao orderItemsDao;

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成订单号
        String orderId=System.currentTimeMillis()+""+userId;
        //保存订单
        orderDao.saveOrder(new Order(orderId,new Date(),cart.getTotalPrice(),0,userId));

        for(Map.Entry<Integer, CartItem>items:cart.getItems().entrySet()){
            CartItem value = items.getValue();
            //保存商品项
            orderItemsDao.saveOrderItem(new OrderItem(null,value.getName(),value.getCount(),value.getPrice(),value.getTotalPrice(),orderId));
            //查询对应图书
            Book book = bookDao.queryBookById(value.getId());
            //修改图书信息
            book.setSales(book.getSales()+value.getCount());
            book.setStock(book.getStock()-value.getCount());
            //保存到数据库中
            bookDao.updateBook(book);
        }
        //清空购物车
          cart.clear();
        return orderId;
    }
     //查询所有订单
    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    //改变订单状态
    @Override
    public void sendOrder(String orderId) {
           orderDao.changeOrderStatus(orderId,1);
    }
  //查询用户的所有订单
    @Override
    public List<Order> showMyOrder(Integer userId) {

        return orderDao.queryOrdersByUserId(userId);
    }
    //查询对应订单的详情
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemsDao.queryOrderItemsByOrderId(orderId);
    }
     //改变订单状态
    @Override
    public void receiverOrder(String orderId) {
     orderDao.changeOrderStatus(orderId,2);
    }
   //分页方法
    @Override
    public PageInfo pages(Integer pageNo, Integer pageSize) {
//        Page<Order> page = new Page<>();
//
//        page.setPageSize(pageSize);
//
//        Integer pageTotal=orderDao.querygetString();
//        page.setPageTotal(pageTotal);
//        Integer pageTotalCount=pageTotal/pageSize;
//        if(pageTotal%pageSize>0){
//            pageTotalCount++;
//        }
//
//        page.setPageTotalCount(pageTotalCount);
//        page.setPageNo(pageNo);
//        int begin=(page.getPageNo()-1)*pageSize;
        PageHelper.startPage(pageNo,pageSize);
        List<Order> items=orderDao.queryOrders();

        PageInfo pageInfo=new PageInfo(items,5);

        return pageInfo;
    }
}

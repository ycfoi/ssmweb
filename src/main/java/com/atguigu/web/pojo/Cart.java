package com.atguigu.web.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart
 * @create 2022-03-15 14:59
 */
public class Cart {
//    private  Integer total;
//    private BigDecimal totalPrice;
    private Map<Integer,CartItem> items=new HashMap<>();


    public Integer getTotalCount() {
        Integer total=0;
        //获取每一个item的数量，计算出总数
        for(CartItem item:items.values()){
            total+= item.getCount();
        }
        return total;
    }

    public BigDecimal getTotalPrice() {

        BigDecimal totalPrice=new BigDecimal(0);
        //遍历每一个item，计算出总金额
        for(CartItem cartItem:items.values()){
            totalPrice=totalPrice.add(cartItem.getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
    //添加商品项
    public void addItem(CartItem item){
        //获得指定的商品项
        CartItem cartItem =items.get(item.getId());
        //判断商品项是否为空
        if(cartItem==null){
            //将商品项放入集合中
            items.put(item.getId(),item);
        }else{
            //设置商品项的总数
            cartItem.setCount(cartItem.getCount()+1);
            //设置总金额
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }
    ///删除
    public void delete(Integer id){
        //判断商品项是否为空
        if(items.get(id)!=null){
            //移除商品项
            items.remove(id);
        }

    }
    //清空购物车
    public void clear(){
        items.clear();
    }
    //修改购物车
    public void update(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }
}

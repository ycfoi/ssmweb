package com.atguigu.web.service;

import com.atguigu.web.pojo.User;

/**
 * @author shkstart
 * @create 2022-03-11 13:47
 */
public interface UserService {
     public void registUser(User user);
     public User login(User user);
     public Boolean existUsername(String name);


}

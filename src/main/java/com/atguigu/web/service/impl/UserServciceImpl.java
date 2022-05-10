package com.atguigu.web.service.impl;

import com.atguigu.web.dao.UserDao;
import com.atguigu.web.pojo.User;
import com.atguigu.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shkstart
 * @create 2022-03-11 13:51
 */
@Service
public class UserServciceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    //添加用户
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    //根据用户名与密码查询用户
    @Override
    public User login(User user) {
        return userDao.queryBynameAndPassword(user.getUsername(),user.getPassword());
    }
  //判断用户名是否可用
    @Override
    public Boolean existUsername(String name) {
        if(userDao.queryByname(name)!=null){
            return true;
        }
        return false;
    }
}

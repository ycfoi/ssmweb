package com.atguigu.web.dao;

import com.atguigu.web.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

/**
 * @author shkstart
 * @create 2022-03-11 13:08
 */
public interface UserDao {
    /**
     *
     * @param name   用户名
     * @param password  密码
     * @return    查询结果为null表示不存在，否则为存在
     */
    public User queryBynameAndPassword(@Param("username") String name ,@Param("password") String password);

    /**
     * 通过用户名查询用户是否存在
     * @param username 用户名
     * @return  查询结果为null表示不存在，否则为存在
     */
    public User queryByname(String username);

    /**
     *
     * @param user  通过对象将数据保存到数据库中
     * @return
     */
    public int saveUser(User user);

}

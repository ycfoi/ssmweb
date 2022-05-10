package com.atguigu.test;

import com.atguigu.web.dao.BookDao;
import com.atguigu.web.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shkstart
 * @create 2022-05-02 20:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class test {
    @Autowired
    public BookService bookService;
    @Test
    public void tes1(){
        System.out.println(bookService);
    }
}

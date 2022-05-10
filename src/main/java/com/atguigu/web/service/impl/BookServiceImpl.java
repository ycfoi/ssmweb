package com.atguigu.web.service.impl;

import com.atguigu.web.dao.BookDao;

import com.atguigu.web.pojo.Book;

import com.atguigu.web.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-03-13 19:01
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;
    //调用BookDao添加图书
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }
    //修改图书
    @Override
    public void updateBook(Book book) {
    bookDao.updateBook(book);
    }
    //删除图书
    @Override
    public void deleteBook(Integer id) {
      bookDao.deleteBookById(id);
    }
   //通过id进行查询图书
    @Override
    public Book queryBookandOne(Integer id) {
        return bookDao.queryBookById(id);
    }
    //查询所有图书
    @Override
    public List<Book> queryBook() {
        return bookDao.queryBooks();
    }
    //分页查询图书
    @Override

    public PageInfo page(Integer pageNo) {
        PageHelper.startPage(pageNo,4);
      //查询图书信息
      List<Book> items=bookDao.queryBookBypage();
      PageInfo pageInfo=new PageInfo<>(items,5);
      return pageInfo;
    }

    /**
     * 通过价格查询图书
     * @param pageNo    分页的Id
     * @param pageSize   分页的最大数
     * @param min         最小价格
     * @param max        最大的价格
     * @return
     */
    @Override
    public PageInfo pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max) {

        PageHelper.startPage(pageNo,pageSize);
        List<Book> items=bookDao.queryBookBypageAndPrice(min,max);
        PageInfo pageInfo=new PageInfo(items,5);
        return pageInfo;
    }
}

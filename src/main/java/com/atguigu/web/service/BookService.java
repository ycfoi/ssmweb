package com.atguigu.web.service;

import com.atguigu.web.pojo.Book;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-03-13 18:58
 */

public interface BookService  {
    public  void addBook(Book book);
    public void updateBook(Book book);
    public  void deleteBook(Integer id);
    public  Book queryBookandOne(Integer id);
    public  List<Book>  queryBook();

    PageInfo page(Integer pageNo);

    PageInfo pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max);
}

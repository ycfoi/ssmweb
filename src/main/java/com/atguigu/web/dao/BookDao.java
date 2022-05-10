package com.atguigu.web.dao;

import com.atguigu.web.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-03-13 18:28
 */
public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

  Integer querygetString();

    List<Book> queryBookBypage();

    Integer querygetStringByPrice(@Param("min") Integer min, @Param("max") Integer max);

    List<Book> queryBookBypageAndPrice( @Param("min")  Integer min,  @Param("max") Integer max);
}

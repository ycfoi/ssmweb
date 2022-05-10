package com.atguigu.web.controller;
import com.atguigu.web.pojo.Book;
import com.atguigu.web.service.BookService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;

import java.io.IOException;


/**
 * @author shkstart
 * @create 2022-03-13 19:14
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

//    public String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //查询所有图书
//        List<Book> books=bookService.queryBook();
//        //将图书信息保存到books中
//        req.setAttribute("book",books);
//        //转发到图书管理页面
//       return   "manager/book_manager";
//    }
    //添加图书的操作(使用ajax解决)
    @RequestMapping("/add")
    public String add(Book book,Integer pageNo) throws IOException {

        if(pageNo==null){
            pageNo=0;
        }
                pageNo+=1;
        //调用方法添加图书
        bookService.addBook(book);
        //重定向到管理图书的page方法
         return "redirect:/book/page?pageNo="+pageNo;
    }
    //删除图书
    @RequestMapping("/delete")
    public String delete(Integer id,Integer pageNo) throws IOException {
      if(id!=null){
    //调用方法删除图书
    bookService.deleteBook(id);
 }
        //重定向到管理图书的page方法
         return "redirect:/book/page?pageNo="+pageNo;

    }
    //在编辑图书界面回显图书信息
    @RequestMapping("/getBook")
   public String getBook(Integer id, Model model) throws ServletException, IOException {
        if(id==null){
            id=0;
        }
        //查询对应的图书信息
       Book book = bookService.queryBookandOne(id);
       model.addAttribute("book",book);
       //转发到图书编辑页面
      return "/manager/book_edit";
   }
   //修改图书的方法
    @RequestMapping("/update")
   public String update(Book book,Integer pageNo) throws IOException {

       bookService.updateBook(book);
       //重定向到管理图书的page方法
        return "redirect:/book/page?pageNo="+pageNo;
   }
   //图书分页操作
    @RequestMapping("/page")
   public String page(Integer pageNo,Model model)  {
       if(pageNo==null){
           pageNo=1;
       }
       //创建图书分页对象pages
        PageInfo pages=bookService.page(pageNo);
//        //设置page的参数
//      pages.(setUrl"manager/bookServlet?action=page");
      // 保存分页对象到request中
       model.addAttribute("page",pages);
       model.addAttribute("url","book/page");
        //转发到管理图书页面
        return "/manager/book_manager";
   }
}

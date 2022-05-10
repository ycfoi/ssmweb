package com.atguigu.web.controller;

import com.atguigu.web.pojo.Page;
import com.atguigu.web.service.BookService;
import com.atguigu.web.utils.PaseUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shkstart
 * @create 2022-03-14 15:58
 */
@Controller
@RequestMapping(value = "/client")
public class ClientController  {
    @Autowired
    private BookService bookService;

     @RequestMapping(value = "/index")
    public String page(HttpServletRequest req){
         //获取分页序号
        Integer pageNo= PaseUtils.paserInt(req.getParameter("pageNo"),1);
        PageInfo pages=bookService.page(pageNo);
        //保存pages
        req.setAttribute("page",pages);

        req.setAttribute("url","client/index");
        return "client/index";
    }
    @RequestMapping(value = "/pageByPrice/{min}/{max}",method = RequestMethod.GET)
    public String pageByPrice(HttpServletRequest req, @PathVariable("min") String min,@PathVariable("max") String max) {
        //同上
        Integer pageNo= PaseUtils.paserInt(req.getParameter("pageNo"),1);
        Integer pageSize=PaseUtils.paserInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
         //获取价格区间
        Integer minInteger=PaseUtils.paserInt(min,0);
        Integer maxInteger=PaseUtils.paserInt(max,Integer.MAX_VALUE);
        //根据价格查询图书
        PageInfo pages=bookService.pageByPrice(pageNo,pageSize,minInteger,maxInteger);
        //设置访问路径，当点击第一次后下一次点击分页只会使用此方法
        StringBuilder str=new StringBuilder("client/pageByPrice");
        if(min!=null){
            str.append("/").append(min);
        }
        if(max!=null){
            str.append("/").append(max);
        }
//        pages.setUrl(str.toString());
        req.setAttribute("page",pages);
        req.setAttribute("url",str);
        req.setAttribute("min",min);
        req.setAttribute("max",max);
        return "client/index";
    }
}

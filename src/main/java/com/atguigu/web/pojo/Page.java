package com.atguigu.web.pojo;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-03-14 9:12
 */
public class Page<T> {
    public static final Integer PAGE_SIZE =4;
    private Integer pageNo;                      //当前页号
    private Integer pageTotal;                   //总记录数
    private Integer pageSize= PAGE_SIZE;         //每页最大显示几条数据
    private Integer pageTotalCount;             //总页数
    private List<T> items;                   //相关数据源
    private String url;                     //对应的分页地址

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo>pageTotalCount){
           pageNo=pageTotalCount;
        }
        if (pageNo<1){
          pageNo=1;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="page_nav">

    <c:if test="${requestScope.page.pageNum>1}">

        <a href="${requestScope.url}?pageNo=1">首页</a>

        <a href="${requestScope.url}?pageNo=${requestScope.page.pageNum-1}">上一页</a>

    </c:if>

   <!--连续页数-->
    <c:forEach items="${requestScope.page.navigatepageNums}" var="i">
        <c:if test="${requestScope.page.pageNum==i}">
            【${i}】
        </c:if>
        <c:if test="${requestScope.page.pageNum!=i}">
            <a href="${requestScope.url}?pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>


    <c:if test="${requestScope.page.pageNum<requestScope.page.pages}">
        <a href="${requestScope.url}?pageNo=${requestScope.page.pageNum+1}">下一页</a>
        <a href="${requestScope.url}?pageNo=${requestScope.page.pages}">末页</a>
    </c:if>
   <%-- 显示分页信息---%>
    共${requestScope.page.pages}页，共${requestScope.page.total}条记录
<%--   跳转指定页数--%>
    到第<input value="${requestScope.page.pageNum}" name="pn" id="pn_input"/>页
    <input  id="btn" type="button" value="确定">

    <script type="text/javascript">
        $('#btn').click(function(){
            var pageNo=$('#pn_input').val();
            var pageTotal=${requestScope.page.pages};
            if(pageNo>pageTotal){
                pageNo=${requestScope.page.pages}
            }else if(pageNo<0){
                pageNo=1;
            }
            location.href="${pageScope.basepath}${requestScope.url}?pageNo="+pageNo
        })
    </script>
</div>

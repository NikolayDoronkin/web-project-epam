package com.epam.web.pagination;

import com.epam.web.entity.Entity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PageHandler {

    public PageHandler() {
        pageResult = new PageResult();
    }

    private PageResult pageResult;

    public PageHandler(PageResult pageResult) {
        this.pageResult = pageResult;
    }

    public PageResult getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult pageResult) {
        this.pageResult = pageResult;
    }

    public HttpServletRequest updateRequest(HttpServletRequest request, List<? extends Entity> contentList, String attributeContentName, String command) {
        String currentPage = request.getParameter("current");
        int pages = 0;
        if (currentPage != null && Integer.parseInt(currentPage) != 0) {
            pages = Integer.parseInt(currentPage) - 1;
        }

        request.setAttribute("ERROR", false);

        Page page = new Page(contentList.size(), pages);
        List<Integer> paginationList = pageResult.createPaginationBar(page);

        if(pages > contentList.size() / 6){
            request.setAttribute("ERROR", true);
        }

        request.setAttribute("current", pages);
        request.setAttribute("curPage", pages + 1);
        request.setAttribute("allPages", pageResult.getTotalPages());

        if (pages == 0) {
            request.setAttribute("begin", (0));
            request.setAttribute("end", 5 * (pages + 1));
        } else {
            request.setAttribute("begin", ((pages * 6)));
            request.setAttribute("end", 6 * (pages + 1) - 1);
        }
        request.setAttribute("command", command);
        request.setAttribute(attributeContentName, contentList);
        request.setAttribute("pagination", paginationList);
        return request;
    }

}

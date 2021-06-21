package com.epam.web.pagination;

import java.util.*;

public class PageResult {

    private static final int MAX_SIZE = 6;
    private List<Integer> paginationBar;
    private int totalPages;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Integer> getPaginationBar() {
        return paginationBar;
    }

    public void setPaginationBar(List<Integer> paginationBar) {
        this.paginationBar = paginationBar;
    }

    public List<Integer> createPaginationBar(Page page) {

        int currentPage = page.getCurrentPage();
        int totalData = page.getTotalData();

        int totalPages = totalData / MAX_SIZE;

        if (totalData % MAX_SIZE != 0) {
            totalPages++;
        }

        int counter = 1;
        while (counter <= totalPages) {
            if (currentPage < MAX_SIZE * counter) {
                break;
            }
            counter++;
        }
        int size = counter * MAX_SIZE;

        if (size > totalData && counter != 1) {
            size = totalData;
        }else if(size > totalPages){
            size = totalPages;
        }


        List<Integer> paginationList = new ArrayList<>();

        int index = (currentPage - currentPage % MAX_SIZE) + 1;

        for (; index <= size; index++) {
            paginationList.add(index);
        }

        setPaginationBar(paginationList);
        setTotalPages(totalPages);

        return paginationList;

    }

}

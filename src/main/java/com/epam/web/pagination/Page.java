package com.epam.web.pagination;

public class Page {

    private int totalData;
    private int currentPage;


    public Page(int totalData, int currentPage) {
        this.totalData = totalData;
        this.currentPage = currentPage;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

}

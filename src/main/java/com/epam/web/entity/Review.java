package com.epam.web.entity;

public class Review implements Entity{
    private Long orderID;
    private Long mark;
    private String comment;

    public Review(Long orderID, Long mark, String comment) {
        this.orderID = orderID;
        this.mark = mark;
        this.comment = comment;
    }

    public void setID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getMark() {
        return mark;
    }

    public void setMark(Long mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public Long getID() {
        return orderID;
    }
}

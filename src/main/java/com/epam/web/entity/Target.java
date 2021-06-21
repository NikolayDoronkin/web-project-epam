package com.epam.web.entity;

import com.epam.web.entity.enums.PaymentType;
import com.epam.web.entity.enums.StatusType;

import java.util.Date;

public class Target implements Entity{

    private Long ID;
    private Long userID;
    private long cost;
    private Date time;
    private StatusType status;
    private PaymentType payment;

    public Target() {
    }

    public Target(Long ID, Long userID, long cost, Date time, StatusType status, PaymentType payment) {
        this.ID = ID;
        this.userID = userID;
        this.cost = cost;
        this.time = time;
        this.status = status;
        this.payment = payment;
    }

    public Target(StatusType status, PaymentType payment) {
        this.status = status;
        this.payment = payment;
    }

    public Target(Long ID, Long userID) {
        this.ID = ID;
        this.userID = userID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public PaymentType getPayment() {
        return payment;
    }

    public void setPayment(PaymentType payment) {
        this.payment = payment;
    }

    @Override
    public Long getID() {
        return ID;
    }
}

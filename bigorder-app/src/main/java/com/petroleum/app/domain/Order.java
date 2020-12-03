package com.petroleum.app.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    private String orderId;

    private Long userId;

    private Long merchantId;

    private Short orderType;

    private BigDecimal orderAmt;

    private Integer orderStatus;

    private Integer orderResult;

    private Short orderIsPostage;

    private BigDecimal orderPostageAmt;

    private Integer scoreReturn;

    private String mark;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Short getOrderType() {
        return orderType;
    }

    public void setOrderType(Short orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderResult() {
        return orderResult;
    }

    public void setOrderResult(Integer orderResult) {
        this.orderResult = orderResult;
    }

    public Short getOrderIsPostage() {
        return orderIsPostage;
    }

    public void setOrderIsPostage(Short orderIsPostage) {
        this.orderIsPostage = orderIsPostage;
    }

    public BigDecimal getOrderPostageAmt() {
        return orderPostageAmt;
    }

    public void setOrderPostageAmt(BigDecimal orderPostageAmt) {
        this.orderPostageAmt = orderPostageAmt;
    }

    public Integer getScoreReturn() {
        return scoreReturn;
    }

    public void setScoreReturn(Integer scoreReturn) {
        this.scoreReturn = scoreReturn;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Order other = (Order) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getOrderAmt() == null ? other.getOrderAmt() == null : this.getOrderAmt().equals(other.getOrderAmt()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getOrderResult() == null ? other.getOrderResult() == null : this.getOrderResult().equals(other.getOrderResult()))
            && (this.getOrderIsPostage() == null ? other.getOrderIsPostage() == null : this.getOrderIsPostage().equals(other.getOrderIsPostage()))
            && (this.getOrderPostageAmt() == null ? other.getOrderPostageAmt() == null : this.getOrderPostageAmt().equals(other.getOrderPostageAmt()))
            && (this.getScoreReturn() == null ? other.getScoreReturn() == null : this.getScoreReturn().equals(other.getScoreReturn()))
            && (this.getMark() == null ? other.getMark() == null : this.getMark().equals(other.getMark()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getOrderAmt() == null) ? 0 : getOrderAmt().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getOrderResult() == null) ? 0 : getOrderResult().hashCode());
        result = prime * result + ((getOrderIsPostage() == null) ? 0 : getOrderIsPostage().hashCode());
        result = prime * result + ((getOrderPostageAmt() == null) ? 0 : getOrderPostageAmt().hashCode());
        result = prime * result + ((getScoreReturn() == null) ? 0 : getScoreReturn().hashCode());
        result = prime * result + ((getMark() == null) ? 0 : getMark().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}
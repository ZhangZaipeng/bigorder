package com.petroleum.app.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderPay implements Serializable {
    private Long payId;

    private String orderId;

    private Long userId;

    private Long merchantId;

    private BigDecimal payScoreAmt;

    private BigDecimal payActualAmt;

    private Date payTime;

    private String payOpt;

    private String payBusId;

    private String payBackMsg;

    private Short payFinished;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

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

    public BigDecimal getPayScoreAmt() {
        return payScoreAmt;
    }

    public void setPayScoreAmt(BigDecimal payScoreAmt) {
        this.payScoreAmt = payScoreAmt;
    }

    public BigDecimal getPayActualAmt() {
        return payActualAmt;
    }

    public void setPayActualAmt(BigDecimal payActualAmt) {
        this.payActualAmt = payActualAmt;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayOpt() {
        return payOpt;
    }

    public void setPayOpt(String payOpt) {
        this.payOpt = payOpt;
    }

    public String getPayBusId() {
        return payBusId;
    }

    public void setPayBusId(String payBusId) {
        this.payBusId = payBusId;
    }

    public String getPayBackMsg() {
        return payBackMsg;
    }

    public void setPayBackMsg(String payBackMsg) {
        this.payBackMsg = payBackMsg;
    }

    public Short getPayFinished() {
        return payFinished;
    }

    public void setPayFinished(Short payFinished) {
        this.payFinished = payFinished;
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
        OrderPay other = (OrderPay) that;
        return (this.getPayId() == null ? other.getPayId() == null : this.getPayId().equals(other.getPayId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getPayScoreAmt() == null ? other.getPayScoreAmt() == null : this.getPayScoreAmt().equals(other.getPayScoreAmt()))
            && (this.getPayActualAmt() == null ? other.getPayActualAmt() == null : this.getPayActualAmt().equals(other.getPayActualAmt()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getPayOpt() == null ? other.getPayOpt() == null : this.getPayOpt().equals(other.getPayOpt()))
            && (this.getPayBusId() == null ? other.getPayBusId() == null : this.getPayBusId().equals(other.getPayBusId()))
            && (this.getPayBackMsg() == null ? other.getPayBackMsg() == null : this.getPayBackMsg().equals(other.getPayBackMsg()))
            && (this.getPayFinished() == null ? other.getPayFinished() == null : this.getPayFinished().equals(other.getPayFinished()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPayId() == null) ? 0 : getPayId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getPayScoreAmt() == null) ? 0 : getPayScoreAmt().hashCode());
        result = prime * result + ((getPayActualAmt() == null) ? 0 : getPayActualAmt().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getPayOpt() == null) ? 0 : getPayOpt().hashCode());
        result = prime * result + ((getPayBusId() == null) ? 0 : getPayBusId().hashCode());
        result = prime * result + ((getPayBackMsg() == null) ? 0 : getPayBackMsg().hashCode());
        result = prime * result + ((getPayFinished() == null) ? 0 : getPayFinished().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}
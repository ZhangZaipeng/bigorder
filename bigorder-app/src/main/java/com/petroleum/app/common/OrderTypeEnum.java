package com.petroleum.app.common;

public enum OrderTypeEnum {

    SCORE((short) 1, "积分订单", "SC"),

    AUCTION((short) 2, "拍卖订单", "AT"),

    RECHARGE((short) 3, "充值订单", "RE"),

    WITHDRAW((short) 4, "提现订单", "WR"),

    SPECIAL((short) 5, "特价订单", "SP"),
    ;

    private short recordType;
    private String recordDesc;
    private String transNo;

    OrderTypeEnum(short recordType, String recordDesc, String transNo) {
        this.recordType = recordType;
        this.recordDesc = recordDesc;
        this.transNo = transNo;
    }

    public short getRecordType() {
        return recordType;
    }

    public void setRecordType(short recordType) {
        this.recordType = recordType;
    }

    public String getRecordDesc() {
        return recordDesc;
    }

    public void setRecordDesc(String recordDesc) {
        this.recordDesc = recordDesc;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

}

package com.sbl.shipping.model;

import java.math.BigDecimal;

public class ShippingCostResponse {
    private BigDecimal cost;
    private BigDecimal discount;
    private String message;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}

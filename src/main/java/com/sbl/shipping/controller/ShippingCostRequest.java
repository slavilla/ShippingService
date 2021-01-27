package com.sbl.shipping.controller;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ShippingCostRequest {
    @NotNull(message = "Weight is mandatory")
    private BigDecimal weight;

    @NotNull(message = "Height is mandatory")
    private BigDecimal height;

    @NotNull(message = "Width is mandatory")
    private BigDecimal width;

    @NotNull(message = "Length is mandatory")
    private BigDecimal length;

    private String voucher;

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }
}

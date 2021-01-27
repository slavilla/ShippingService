package com.sbl.shipping.builder;

import com.sbl.shipping.service.ShippingCostCalculator;

import java.math.BigDecimal;

public class ParcelDetailsBuilder {
    private BigDecimal weight;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;

    public ParcelDetailsBuilder setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public ParcelDetailsBuilder setLength(BigDecimal length) {
        this.length = length;
        return this;
    }

    public ParcelDetailsBuilder setWidth(BigDecimal width) {
        this.width = width;
        return this;
    }

    public ParcelDetailsBuilder setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public ShippingCostCalculator.ParcelDetails createParcelDetails() {
        return new ShippingCostCalculator.ParcelDetails(weight, length, width, height);
    }
}
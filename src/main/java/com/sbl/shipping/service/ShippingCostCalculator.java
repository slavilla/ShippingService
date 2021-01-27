package com.sbl.shipping.service;

import java.math.BigDecimal;
import java.util.Optional;

public interface ShippingCostCalculator {
    Result calculate(ParcelDetails parcelDetails);

    class Result {
        private Optional<BigDecimal> shippingCost;
        private String message;

        public Result(BigDecimal shippingCost) {
            this.shippingCost = Optional.ofNullable(shippingCost);
            this.message = "";
        }

        public Result(BigDecimal shippingCost, String message) {
            this.shippingCost = Optional.ofNullable(shippingCost);
            this.message = message;
        }

        public Result() {
        }

        public Optional<BigDecimal> getShippingCost() {
            return shippingCost;
        }

        public void setShippingCost(BigDecimal shippingCost) {
            this.shippingCost = Optional.ofNullable(shippingCost);
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    class ParcelDetails {
        private final BigDecimal weight;
        private final BigDecimal length;
        private final BigDecimal width;
        private final BigDecimal height;

        public ParcelDetails(BigDecimal weight, BigDecimal length, BigDecimal width, BigDecimal height) {
            this.weight = weight;
            this.length = length;
            this.width = width;
            this.height = height;
        }

        public BigDecimal getWeight() {
            return weight;
        }

        public BigDecimal getLength() {
            return length;
        }

        public BigDecimal getWidth() {
            return width;
        }

        public BigDecimal getHeight() {
            return height;
        }
    }
}

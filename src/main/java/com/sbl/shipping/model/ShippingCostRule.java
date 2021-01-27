package com.sbl.shipping.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@IdClass(ShippingCostRuleId.class)
public class ShippingCostRule implements Serializable {
    public enum Type {VOLUME, WEIGHT}

    @Id
    private String name;

    @Id
    @Enumerated(EnumType.STRING)
    private Type type;

    @Id
    private int priority;

    @Column
    BigDecimal upperLimit;

    @Column
    BigDecimal lowerLimit;

    @Column
    private BigDecimal baseCost;

    public ShippingCostRule() {
    }

    public ShippingCostRule(String name,
                            Type type,
                            int priority,
                            BigDecimal lowerLimit,
                            BigDecimal upperLimit,
                            BigDecimal baseCost) {
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.baseCost = baseCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public BigDecimal getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(BigDecimal upperLimit) {
        this.upperLimit = upperLimit;
    }

    public BigDecimal getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(BigDecimal lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(BigDecimal baseCost) {
        this.baseCost = baseCost;
    }
}

package com.sbl.shipping.model;

import java.io.Serializable;

public class ShippingCostRuleId implements Serializable {
    private String name;
    private ShippingCostRule.Type type;
    private int priority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShippingCostRule.Type getType() {
        return type;
    }

    public void setType(ShippingCostRule.Type type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

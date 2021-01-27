package com.sbl.shipping.repository;

import com.sbl.shipping.model.ShippingCostRule;
import com.sbl.shipping.model.ShippingCostRuleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingCostRuleRepository extends JpaRepository<ShippingCostRule, ShippingCostRuleId> {
}

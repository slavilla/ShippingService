package com.sbl.shipping.service.impl;

import com.sbl.shipping.model.ShippingCostRule;
import com.sbl.shipping.repository.ShippingCostRuleRepository;
import com.sbl.shipping.service.ShippingCostCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingCostCalculatorImpl implements ShippingCostCalculator {
    private final ShippingCostRuleRepository shippingCostRuleRepository;

    @Autowired
    public ShippingCostCalculatorImpl(ShippingCostRuleRepository shippingCostRuleRepository) {
        this.shippingCostRuleRepository = shippingCostRuleRepository;
    }

    @Override
    public Result calculate(ParcelDetails parcelDetails) {
        BigDecimal height = parcelDetails.getHeight();
        BigDecimal length = parcelDetails.getLength();
        BigDecimal weight = parcelDetails.getWeight();
        BigDecimal width = parcelDetails.getWidth();

        // get all rules
        List<ShippingCostRule> shippingCostRules = shippingCostRuleRepository.findAll();

        // sort by priority
        List<ShippingCostRule> sortedRules = shippingCostRules
                .stream()
                .sorted(Comparator.comparing(ShippingCostRule::getPriority))
                .collect(Collectors.toList());

        // get volume
        BigDecimal volume = height.multiply(width).multiply(length);

        Result result = null;

        for (ShippingCostRule shippingCostRule : sortedRules) {
            ShippingCostRule.Type type = shippingCostRule.getType();
            BigDecimal lowerLimit = shippingCostRule.getLowerLimit();
            BigDecimal upperLimit = shippingCostRule.getUpperLimit();
            BigDecimal baseCost = shippingCostRule.getBaseCost();

            switch (type) {
                case VOLUME:
                    if (volume.compareTo(lowerLimit) >= 0 && (upperLimit == null || volume.compareTo(upperLimit) < 0)) {
                        result = new Result(baseCost.multiply(volume));
                    }
                    break;

                case WEIGHT:
                    if (weight.compareTo(lowerLimit) >= 0) {

                        if (upperLimit != null && weight.compareTo(upperLimit) < 0) {
                            result = new Result(baseCost.multiply(weight));

                        } else if (weight.compareTo(lowerLimit) >= 0 && upperLimit == null) {
                            result = new Result(null, "Weight can't exceed " + lowerLimit + "kg");
                        }
                    }
                    break;
            }

            if (result != null) {
                break;
            }
        }

        return result;
    }
}

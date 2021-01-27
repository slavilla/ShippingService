package com.sbl.shipping.service;

import com.sbl.shipping.builder.ParcelDetailsBuilder;
import com.sbl.shipping.model.ShippingCostRule;
import com.sbl.shipping.repository.ShippingCostRuleRepository;
import com.sbl.shipping.service.ShippingCostCalculator.ParcelDetails;
import com.sbl.shipping.service.ShippingCostCalculator.Result;
import com.sbl.shipping.service.impl.ShippingCostCalculatorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.sbl.shipping.model.ShippingCostRule.Type.VOLUME;
import static com.sbl.shipping.model.ShippingCostRule.Type.WEIGHT;

public class ShippingCostCalculatorImplTest {
    @Mock
    private ShippingCostRuleRepository shippingCostRuleRepository;
    private ShippingCostCalculatorImpl shippingCostCalculator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        shippingCostCalculator = new ShippingCostCalculatorImpl(shippingCostRuleRepository);
    }

    @Test
    public void calculate() {
        List<ShippingCostRule> rules = new ArrayList<>();

        ShippingCostRule rule = new ShippingCostRule("Reject", WEIGHT, 1, new BigDecimal("50"), null, null);
        rules.add(rule);

        rule = new ShippingCostRule("Heavy Parcel", WEIGHT, 2, new BigDecimal("10"), new BigDecimal("50"), new BigDecimal("20"));
        rules.add(rule);

        rule = new ShippingCostRule("Small Parcel", VOLUME, 3, new BigDecimal("0"), new BigDecimal("1500"), new BigDecimal("0.03"));
        rules.add(rule);

        rule = new ShippingCostRule("Medium Parcel", VOLUME, 4, new BigDecimal("1500"), new BigDecimal("2500"), new BigDecimal("0.04"));
        rules.add(rule);

        rule = new ShippingCostRule("Large Parcel", VOLUME, 5, new BigDecimal("2500"), null, new BigDecimal("0.05"));
        rules.add(rule);

        Mockito.when(shippingCostRuleRepository.findAll()).thenReturn(rules);

        ParcelDetailsBuilder parcelDetailsBuilder = new ParcelDetailsBuilder();

        // test rejected parcel
        ParcelDetails parcelDetails = parcelDetailsBuilder
                .setWeight(new BigDecimal("51"))
                .setHeight(new BigDecimal("2"))
                .setLength(new BigDecimal("3"))
                .setWidth(new BigDecimal("4"))
                .createParcelDetails();
        Result result = shippingCostCalculator.calculate(parcelDetails);
        Assert.assertNull(result.getShippingCost().orElse(null));

        // test heavy parcel
        parcelDetails = parcelDetailsBuilder
                .setWeight(new BigDecimal("15"))
                .setHeight(new BigDecimal("2"))
                .setLength(new BigDecimal("3"))
                .setWidth(new BigDecimal("4"))
                .createParcelDetails();
        result = shippingCostCalculator.calculate(parcelDetails);
        BigDecimal shippingCost = result.getShippingCost().orElse(null);
        Assert.assertEquals(0, new BigDecimal("300").compareTo(shippingCost));

        // test small parcel
        parcelDetails = parcelDetailsBuilder
                .setWeight(new BigDecimal("8"))
                .setHeight(new BigDecimal("14"))
                .setLength(new BigDecimal("10"))
                .setWidth(new BigDecimal("10"))
                .createParcelDetails();
        result = shippingCostCalculator.calculate(parcelDetails);
        shippingCost = result.getShippingCost().orElse(null);
        Assert.assertEquals(0, new BigDecimal("42").compareTo(shippingCost));

        // test medium parcel
        parcelDetails = parcelDetailsBuilder
                .setWeight(new BigDecimal("5"))
                .setHeight(new BigDecimal("10"))
                .setLength(new BigDecimal("24.5"))
                .setWidth(new BigDecimal("10"))
                .createParcelDetails();
        result = shippingCostCalculator.calculate(parcelDetails);
        shippingCost = result.getShippingCost().orElse(null);
        Assert.assertEquals(0, new BigDecimal("98").compareTo(shippingCost));

        // test large parcel
        parcelDetails = parcelDetailsBuilder
                .setWeight(new BigDecimal("5"))
                .setHeight(new BigDecimal("10"))
                .setLength(new BigDecimal("30"))
                .setWidth(new BigDecimal("10"))
                .createParcelDetails();
        result = shippingCostCalculator.calculate(parcelDetails);
        shippingCost = result.getShippingCost().orElse(null);
        Assert.assertEquals(0, new BigDecimal("150").compareTo(shippingCost));
    }
}
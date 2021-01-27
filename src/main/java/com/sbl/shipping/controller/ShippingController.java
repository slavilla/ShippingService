package com.sbl.shipping.controller;

import com.sbl.shipping.service.ParcelDetailsBuilder;
import com.sbl.shipping.service.ShippingCostCalculator;
import com.sbl.shipping.service.ShippingCostCalculator.ParcelDetails;
import com.sbl.shipping.service.ShippingCostCalculator.Result;
import com.sbl.shipping.service.VoucherService;
import com.sbl.shipping.service.VoucherService.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {
    private final ShippingCostCalculator shippingCostCalculator;
    private final VoucherService voucherService;

    @Autowired
    public ShippingController(ShippingCostCalculator shippingCostCalculator, VoucherService voucherService) {
        this.shippingCostCalculator = shippingCostCalculator;
        this.voucherService = voucherService;
    }

    /**
     * Calculates the shipping cost of a parcel.
     *
     * @param request Contains parcel details and voucher code.
     * @return Shipping cost data
     */
    @GetMapping("/cost")
    public ResponseEntity<ShippingCostResponse> calculateCost(@Valid ShippingCostRequest request) {
        BigDecimal weight = request.getWeight();
        BigDecimal height = request.getHeight();
        BigDecimal length = request.getLength();
        BigDecimal width = request.getWidth();
        String voucherCode = request.getVoucher();

        // calculate shipping cost
        ParcelDetails parcelDetails = new ParcelDetailsBuilder().setWeight(weight).setLength(length).setWidth(width).setHeight(height).createParcelDetails();
        Result result = shippingCostCalculator.calculate(parcelDetails);
        BigDecimal shippingCost = result.getShippingCost().orElse(null);

        // subtract voucher discount if present
        BigDecimal voucherDiscount = BigDecimal.ZERO;
        if (shippingCost != null && voucherCode != null && !voucherCode.isEmpty()) {
            voucherDiscount = voucherService.getVoucher(voucherCode).map(Voucher::getDiscount).orElse(BigDecimal.ZERO);
            shippingCost = shippingCost.subtract(voucherDiscount);
        }

        ShippingCostResponse shippingCostResponse = new ShippingCostResponse();
        shippingCostResponse.setCost(shippingCost);
        shippingCostResponse.setMessage(Optional.ofNullable(result.getMessage()).orElse(""));
        shippingCostResponse.setDiscount(voucherDiscount);

        return ResponseEntity.ok(shippingCostResponse);
    }
}

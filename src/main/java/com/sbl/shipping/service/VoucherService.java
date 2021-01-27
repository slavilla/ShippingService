package com.sbl.shipping.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface VoucherService {

    Optional<Voucher> getVoucher(String code);

    class Voucher {
        private BigDecimal discount;
        private Date expiry;

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }

        public Date getExpiry() {
            return expiry;
        }

        public void setExpiry(Date expiry) {
            this.expiry = expiry;
        }
    }
}

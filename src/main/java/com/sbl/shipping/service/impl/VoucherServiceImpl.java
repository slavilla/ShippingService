package com.sbl.shipping.service.impl;

import com.sbl.shipping.service.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {
    private static final Logger log = LoggerFactory.getLogger(VoucherServiceImpl.class);
    private final RestTemplate restTemplate;
    private final Environment env;

    @Autowired
    public VoucherServiceImpl(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.env = env;
    }

    @Override
    public Optional<Voucher> getVoucher(String code) {
        Voucher voucher = null;
        String voucherApi = env.getProperty("api.voucher");

        if (voucherApi == null) {
            log.error("Voucher API value not found");

        } else {
            String url = String.format(voucherApi, code);
            try {
                voucher = restTemplate.getForObject(url, Voucher.class);
            } catch (RestClientException e) {
                log.error(e.getMessage(), e);
            }
        }

        return Optional.ofNullable(voucher);
    }
}

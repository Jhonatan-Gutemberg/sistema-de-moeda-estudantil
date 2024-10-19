package com.coinsystem.system.service.Utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class CouponCodeGenerator {
    public static String generateCouponCode() {
        return RandomStringUtils.randomAlphanumeric(8).toUpperCase();
    }
}

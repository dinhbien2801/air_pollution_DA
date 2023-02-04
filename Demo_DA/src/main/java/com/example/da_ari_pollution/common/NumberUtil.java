//package com.example.da_ari_pollution.common;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//public class NumberUtil {
//    public static double roundDouble(double value, int places) {
//        if (places < 0) throw new IllegalArgumentException();
//
//        BigDecimal bd = BigDecimal.valueOf(value);
//        bd = bd.setScale(places, RoundingMode.HALF_UP);
//        return bd.doubleValue();
//    }
//}

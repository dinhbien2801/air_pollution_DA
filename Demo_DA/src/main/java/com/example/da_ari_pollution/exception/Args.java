//package com.example.da_ari_pollution.exception;
//
//import lombok.Getter;
//
//import java.util.stream.Stream;
//
//@Getter
//public class Args {
//    private String data;
//    private String dataTranslated;
//
//    private Args(String data) {
//        this.data = data;
//    }
//
//    public void setDataTranslated(String dataTranslated) {
//        this.dataTranslated = dataTranslated;
//    }
//
//    @Override
//    public String toString() {
//        return dataTranslated == null ? data : dataTranslated;
//    }
//
//    public static Args[] of(String... data) {
//        return Stream.of(data).map(Args::new).toArray(Args[]::new);
//    }
//
//}
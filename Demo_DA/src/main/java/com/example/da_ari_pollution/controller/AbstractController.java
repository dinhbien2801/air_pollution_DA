//package com.example.da_ari_pollution.controller;
//
//import com.example.da_ari_pollution.constant.ErrorCode;
//import com.example.da_ari_pollution.exception.BusinessException;
//import com.example.da_ari_pollution.model.response.TResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Locale;
//import java.util.Optional;
//
//public abstract class AbstractController<S> {
//
//    protected Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    protected <V> ResponseEntity<TResponse<V>> response(TResponse<V> response) {
//        if (response == null) {
//            throw new IllegalArgumentException("Please set responseBean.");
//        }
//
//        if (StringUtils.isEmpty(response.getCode())) {
//            response.setCode(ErrorCode.SUCCESS);
//        }
//        return new ResponseEntity<TResponse<V>>(response, HttpStatus.OK);
//    }
//
//    @Autowired
//    HttpServletRequest request;
//
//    @Autowired
//    private MessageSource messageSource;
//
//    @Autowired
//    protected S service;
//
//    protected <V> ResponseEntity<TResponse<V>> toResult(Optional<V> optional) {
//        if (!optional.isPresent())
//            throw new BusinessException.NotFoundEntityException();
//        return toResult(optional.get());
//    }
//
//    protected <V> ResponseEntity<TResponse<V>> toResult(V t) {
//        TResponse<V> TResponse = new TResponse<V>();
//        TResponse.setCode(ErrorCode.SUCCESS);
//        String lang = StringUtils.isEmpty(request.getParameter("lang")) ? "vi" : request.getParameter("lang");
//        TResponse.setMessage(messageSource.getMessage("err.00", null, null, new Locale(lang)));
//        TResponse.setData(t);
//        return response(TResponse);
//    }
//
//    protected <V> ResponseEntity<TResponse<List<V>>> toResult(List<V> v) {
//        TResponse<List<V>> TResponse = new TResponse<List<V>>();
//        TResponse.setCode(ErrorCode.SUCCESS);
//        String lang = StringUtils.isEmpty(request.getParameter("lang")) ? "vi" : request.getParameter("lang");
//        TResponse.setMessage(messageSource.getMessage("err.00", null, null, new Locale(lang)));
//        TResponse.setData(v);
//        return response(TResponse);
//    }
//
////    protected Pageable getPageable(int page, int size) {
////        if (ObjectUtils.isEmpty(page) || page < 1) {
////            page = 1;
////        }
////        if (ObjectUtils.isEmpty(size) || size < 1 || size > 1000) {
////            size= 10;
////        }
////        return PageRequest.of(page - 1, size);
////    }
//}
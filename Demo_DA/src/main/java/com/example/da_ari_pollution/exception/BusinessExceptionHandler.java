//package com.example.da_ari_pollution.exception;
//
//import com.example.da_ari_pollution.common.JsonMapper;
//import com.example.da_ari_pollution.constant.ErrorCode;
//import com.example.da_ari_pollution.model.response.TResponse;
//import com.example.da_ari_pollution.model.response.TResponseFail;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.json.UTF8StreamJsonParser;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.StringTrimmerEditor;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import java.net.SocketTimeoutException;
//import java.nio.file.AccessDeniedException;
//import java.util.Arrays;
//import java.util.Locale;
//import java.util.Optional;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Stream;
//
//@RestControllerAdvice
//@Slf4j
//public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private MessageSource messageSource;
//
//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder) {
//        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
//    }
//    /**
//     * Handle number format exception.
//     *
//     * @param request the request
//     * @param e       the e
//     * @return the response entity
//     */
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<TResponse<String>> handleMethodArgumentTypeMismatchException(
//            final HttpServletRequest request,
//            final MethodArgumentTypeMismatchException e) {
//        log.info(e.getMessage(), e);
//        String lang = StringUtils.isEmpty(request.getParameter("lang")) ? "vi"
//                : request.getParameter("lang");
//        Locale locale = new Locale(lang);
//
//        String errorMessage;
//        String inputValue = (String) e.getValue();
//        Pattern pattern = Pattern.compile("[0-9]+");
//        if (pattern.matcher(inputValue).matches()) {
//            errorMessage = messageSource.getMessage("err.33.args", new Object[]{e.getName()}, locale);
//        } else
//            errorMessage = messageSource.getMessage("err.01.args", new Object[]{e.getName()}, locale);
//
//
//        TResponse<String> response = getTResponse(request, ErrorCode.INVALID_INPUT_CODE, errorMessage);
//        return new ResponseEntity<>(response, null, HttpStatus.OK);
//    }
//    /**
//     * Handle number format exception.
//     *
//     * @param request the request
//     * @param e       the e
//     * @return the response entity
//     */
//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<TResponse<String>> handleNumberFormatException(
//            final HttpServletRequest request,
//            final Exception e) {
//        log.info(e.getMessage(), e);
//        if (e.getMessage().contains("SocketTimeoutException")) {
//            TResponse<String> response = getTResponse(request, ErrorCode.CONNECTION_TIMEOUT, ErrorCode.CONNECTION_TIMEOUT_DESCRIPTION);
//            return new ResponseEntity<>(response, null,
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        TResponse<String> response = getTResponse(request, ErrorCode.NUMBER_FORMAT_CODE, ErrorCode.NUMBER_FORMAT_MESSAGE);
//        return new ResponseEntity<>(response, null, HttpStatus.OK);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(
//            HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        log.info(e.getMessage(), e);
//
//        UTF8StreamJsonParser processor;
//
//        if (e.getCause().getClass().equals(JsonMappingException.class)) {
//            JsonMappingException cause = (JsonMappingException) e.getCause();
//            processor = (UTF8StreamJsonParser) cause.getProcessor();
//        } else {
//            JsonParseException cause = (JsonParseException) e.getCause();
//            processor = (UTF8StreamJsonParser) cause.getProcessor();
//        }
//
//        String exceptionFieldName = processor.getParsingContext().getCurrentName();
//        String lang = StringUtils.isEmpty(request.getParameter("lang")) ? "vi" : request.getParameter("lang");
//        Locale locale = new Locale(lang);
//
//        String errorMessage = messageSource.getMessage("err.34.args", new Object[]{exceptionFieldName}, locale);
//
//        TResponse<String> response = getTResponse(request, ErrorCode.INVALID_INPUT_CODE, errorMessage);
//        return new ResponseEntity<>(response, null, HttpStatus.OK);
//    }
//
//    /**
//     *
//     */
//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity<TResponse<String>> handleBusinessException(
//            final HttpServletRequest request,
//            final Exception e) {
//        log.info(e.getMessage(), e);
//
//        BusinessException vpException = (BusinessException) e;
//
//        String code = vpException.getCode();
//
//        String message = getMessageFromException(request, vpException);
//
//        TResponse<String> response = getTResponse(request, code, message);
//        return new ResponseEntity<>(response, null, HttpStatus.OK);
//    }
//
//
//    /**
//     *
//     */
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<TResponse<String>> handleException(final HttpServletRequest request,
//                                                             final Exception e) {
//        log.info(e.getMessage(), e);
//        TResponse<String> response = getTResponse(request, ErrorCode.UNKNOWN_ERROR, ErrorCode.UNKNOWN_ERROR_MESSAGE);
//        return new ResponseEntity<TResponse<String>>(response, null,
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    /**
//     *
//     */
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<TResponse<String>> handleRuntimeException(
//            final HttpServletRequest request,
//            final Exception e) {
//
//        log.info(e.getMessage(), e);
//        TResponse<String> response = getTResponse(request, ErrorCode.UNKNOWN_ERROR, ErrorCode.UNKNOWN_ERROR_MESSAGE);
//        return new ResponseEntity<TResponse<String>>(response, null,
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    /**
//     *
//     */
//    @ExceptionHandler(SocketTimeoutException.class)
//    public ResponseEntity<TResponse<String>> handleTimeoutException(
//            final HttpServletRequest request,
//            final Exception e) {
//
//        log.info(e.getMessage(), e);
//        TResponse<String> response = getTResponse(request, ErrorCode.CONNECTION_TIMEOUT, ErrorCode.CONNECTION_TIMEOUT_DESCRIPTION);
//        return new ResponseEntity<TResponse<String>>(response, null,
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    /**
//     *
//     */
//    @ExceptionHandler(IOException.class)
//    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE) // (1)
//    public ResponseEntity<TResponse<String>> handleIOException(final HttpServletRequest request,
//                                                               final Exception e) {
//
//        log.info(e.getMessage(), e);
//        TResponse<String> response = getTResponse(request, ErrorCode.CONNECTION_TIMEOUT, ErrorCode.CONNECTION_TIMEOUT_DESCRIPTION);
//        return new ResponseEntity<TResponse<String>>(response, null, HttpStatus.GATEWAY_TIMEOUT);
//    }
//
//    /**
//     *
//     */
//    @ExceptionHandler({AccessDeniedException.class})
//    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
//
//        log.info(ex.getMessage(), ex);
//
//        return new ResponseEntity<Object>(
//                "Access denied", new HttpHeaders(), HttpStatus.FORBIDDEN);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
//                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        log.info(ex.getMessage(), ex);
//        return super.handleExceptionInternal(ex, body, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
//            WebRequest request) {
//        String space = " ";
//        Optional<ObjectError> optError = ex.getBindingResult().getAllErrors().stream()
//                .filter(err -> !StringUtils.isEmpty(err.getDefaultMessage()) && !space
//                        .equals(err.getDefaultMessage().charAt(0)))
//                .findFirst();
//        if (!optError.isPresent() || !(optError.get() instanceof FieldError)) {
//            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
//        }
//
//        // Convert to FieldError
//        FieldError error = (FieldError) optError.get();
//        String fieldName = error.getField();
//        String statement =
//                StringUtils.isEmpty(error.getDefaultMessage()) ? "" : error.getDefaultMessage();
//
//        // Build Message
//        String finalMessage = getMessage(request, fieldName, statement);
//
//        // Build Response
//        TResponse<String> response = new TResponse<>();
//        response.setCode(ErrorCode.INVALID_INPUT_CODE);
//        response.setMessage(finalMessage);
//        return new ResponseEntity<>(response, null, HttpStatus.OK);
//    }
//
//    /**
//     * Get Error message
//     *
//     * @param request   contain `lang` param -> i18n
//     * @param fieldName field or multi-field. Eg: merchantName, menu.merchantName, ...
//     */
//    private String getMessage(WebRequest request, String fieldName, String messageTemplate) {
//        String message = messageTemplate;
//        String lang = StringUtils.isEmpty(request.getParameter("lang")) ? "vi"
//                : request.getParameter("lang");
//        String[] data = messageTemplate.split(";");
//        fieldName = lastField(fieldName);
//        fieldName = messageSource.getMessage(fieldName, null, fieldName, new Locale(lang));
//        Object[] params = new Object[]{fieldName};
//        if (data.length > 1) {
//            message = data[0];
//            Object[] extraParams = Arrays.stream(data, 1, data.length).toArray();
//            params = Stream.of(params, extraParams).flatMap(Stream::of).toArray(Object[]::new);
//        }
//        return messageSource.getMessage(message, params, message, new Locale(lang));
//    }
//
//    @ExceptionHandler({ConstraintViolationException.class})
//    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,
//                                                            WebRequest request) {
//        // Build Message
//        Optional<ConstraintViolation<?>> opt = ex.getConstraintViolations().stream().findFirst();
//        if (!opt.isPresent()) {
//            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
//        }
//
//        String field = opt.get().getPropertyPath().toString();
//        String message = getMessage(request, field, opt.get().getMessage());
//
//        TResponse<String> response = new TResponse<>();
//        response.setCode(ErrorCode.INVALID_INPUT_CODE);
//        response.setMessage(message);
//        return new ResponseEntity<>(response, null, HttpStatus.OK);
//    }
//    // -----------------------------------------------------------------------------------------------------------------
//
//    private String getMessageFromException(HttpServletRequest request, BusinessException vpException) {
//        Args[] args = vpException.getArgs();
//        String lang = StringUtils.isEmpty(request.getParameter("lang")) ? "vi"
//                : request.getParameter("lang");
//        Locale locale = new Locale(lang);
//        translate(args, locale); // Translate arguments in exception
//
//        // if data is existed, select the MessageSource which contains placeholder(s)
//        return ObjectUtils.isEmpty(args) ?
//                messageSource.getMessage(
//                        "err." + vpException.getCode(), null,
//                        vpException.getMessage(), locale
//                ) :
//                messageSource.getMessage(
//                        "err." + vpException.getCode() + ".args", args,
//                        vpException.getMessage(), locale
//                );
//    }
//
//    /**
//     * Replace placeholder `{}` by values in messages.properties
//     *
//     * @param str eg: `Hello {name_of_a}! My name is {name_of_b}.`
//     * @return `Hello Quy! My name is Kien` (in messages.properties: name_of_a=Quy, name_of_b=Kien)
//     */
//    private String translate(String str, Locale loc) {
//        if (StringUtils.isEmpty(str)) {
//            return "";
//        }
//
//        Pattern p = Pattern.compile("\\{([^}]*)\\}");
//        Matcher m = p.matcher(str);
//        while (m.find()) {
//            String ph = m.group(0);
//            String key = m.group(1);
//            String value = messageSource.getMessage(key, null, key, loc);
//            str = str.replace(ph, value);
//        }
//        return str;
//    }
//
//    private void translate(Args[] args, Locale loc) {
//        if (args == null) return;
//
//        for (Args arg : args) {
//            String dataTranslated = messageSource.getMessage(arg.getData(), null, arg.getData(), loc);
//            arg.setDataTranslated(dataTranslated);
//        }
//    }
//
//    /**
//     * Get last field. In case nested fields, Spring validator will return list nested fields ->
//     * only get last field.
//     *
//     * @param fields following the pattern: field_1.field_2.(...).field_n
//     * @return field_n
//     */
//    private String lastField(String fields) {
//        if (StringUtils.isEmpty(fields)) {
//            return "";
//        }
//
//        String[] list = fields.split("\\.");
//        return list[list.length - 1];
//    }
//
//    private TResponse<String> getTResponse(HttpServletRequest request, String code, String message) {
//        String requestId = (String) request.getSession().getAttribute("requestId");
//        if (StringUtils.isEmpty(requestId)) {
//            TResponse<String> response = new TResponse<>();
//            response.setCode(code);
//            response.setMessage(message);
//            logger.info(" response : {} \n", JsonMapper.writeValueAsString(response));
//            return response;
//        }
//        TResponseFail<String> responseFail = new TResponseFail<>();
//        responseFail.setCode(code);
//        responseFail.setMessage(message);
//        responseFail.setRequestId(requestId);
//        logger.info(" response : {} \n", JsonMapper.writeValueAsString(responseFail));
//        return responseFail;
//    }
//
//    private TResponse<String> getTResponse(WebRequest request, String code, String message) {
//        String requestId = (String) request.getSessionId();
//        if (StringUtils.isEmpty(requestId)) {
//            TResponse<String> response = new TResponse<>();
//            response.setCode(code);
//            response.setMessage(message);
//            logger.info(" response : {} \n", JsonMapper.writeValueAsString(response));
//            return response;
//        }
//        TResponseFail<String> responseFail = new TResponseFail<>();
//        responseFail.setCode(code);
//        responseFail.setMessage(message);
//        responseFail.setRequestId(requestId);
//        logger.info(" response : {} \n", JsonMapper.writeValueAsString(responseFail));
//        return responseFail;
//    }
//}
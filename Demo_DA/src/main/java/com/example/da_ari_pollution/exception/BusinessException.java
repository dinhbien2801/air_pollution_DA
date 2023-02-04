//package com.example.da_ari_pollution.exception;
//
//import com.example.da_ari_pollution.common.StringUtil;
//import com.example.da_ari_pollution.constant.ErrorCode;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class  BusinessException extends RuntimeException {
//
//    private static final long serialVersionUID = 3097485192046665776L;
//
//    private String code;
//
//    private Args[] args;
//
//    public BusinessException(String code) {
//        super();
//        this.code = code;
//    }
//
//    public BusinessException(String code, String message) {
//        super(message);
//        this.code = code;
//    }
//
//    public BusinessException(String code, String message, Args... args) {
//        super(args.length == 0 ? message : StringUtil.arrayConcat(args));
//        this.code = code;
//        this.args = args;
//    }
//
//    public static class NotFoundEntityException extends BusinessException {
//
//
//        private static final long serialVersionUID = 3005904315329242072L;
//
//        public NotFoundEntityException() {
//            super(ErrorCode.NOT_FOUND_ENTITY_CODE, ErrorCode.NOT_FOUND_ENTITY_MESSAGE);
//        }
//
//        public NotFoundEntityException(String message) {
//            super(ErrorCode.NOT_FOUND_ENTITY_CODE, message);
//        }
//
//        public NotFoundEntityException(Args... args) {
//            super(ErrorCode.NOT_FOUND_ENTITY_CODE, ErrorCode.NOT_FOUND_ENTITY_MESSAGE, args);
//        }
//
//        public NotFoundEntityException(String message, Args... args) {
//            super(ErrorCode.NOT_FOUND_ENTITY_CODE, message, args);
//        }
//
//    }
//
//    public static class DuplicatedEntityException extends BusinessException {
//
//        private static final long serialVersionUID = 6685802703164214179L;
//
//        public DuplicatedEntityException() {
//            super(ErrorCode.DUPLICATE_ENTITY_CODE, ErrorCode.DUPLICATE_ENTITY_MESSAGE);
//        }
//
//        public DuplicatedEntityException(String message) {
//            super(ErrorCode.DUPLICATE_ENTITY_CODE, message);
//        }
//
//        public DuplicatedEntityException(Args... args) {
//            super(ErrorCode.DUPLICATE_ENTITY_CODE, ErrorCode.DUPLICATE_ENTITY_MESSAGE, args);
//        }
//
//        public DuplicatedEntityException(String message, Args... args) {
//            super(ErrorCode.DUPLICATE_ENTITY_CODE, message, args);
//        }
//
//    }
//
//    public static class InvalidInputException extends BusinessException {
//
//        private static final long serialVersionUID = -7257749122280775815L;
//
//        public InvalidInputException() {
//            super(ErrorCode.INVALID_INPUT_CODE, ErrorCode.INVALID_INPUT_MESSAGE);
//        }
//
//        public InvalidInputException(String message) {
//            super(ErrorCode.INVALID_INPUT_CODE, message);
//        }
//
//        public InvalidInputException(Args... args) {
//            super(ErrorCode.INVALID_INPUT_CODE, ErrorCode.INVALID_INPUT_MESSAGE, args);
//        }
//
//        public InvalidInputException(String message, Args... args) {
//            super(ErrorCode.INVALID_INPUT_CODE, message, args);
//        }
//
//    }
//
//    public static class FailedToExecuteException extends BusinessException {
//
//        private static final long serialVersionUID = 7641702942202000228L;
//
//
//        public FailedToExecuteException() {
//            super(ErrorCode.FAILED_TO_EXECUTE, ErrorCode.FAILED_TO_EXECUTE_DESCRIPTION);
//        }
//
//        public FailedToExecuteException(String message) {
//            super(ErrorCode.FAILED_TO_EXECUTE, message);
//        }
//
//        public FailedToExecuteException(Args... args) {
//            super(ErrorCode.FAILED_TO_EXECUTE, ErrorCode.FAILED_TO_EXECUTE_DESCRIPTION, args);
//        }
//
//        public FailedToExecuteException(String message, Args... args) {
//            super(ErrorCode.FAILED_TO_EXECUTE, message, args);
//        }
//
//    }
//}

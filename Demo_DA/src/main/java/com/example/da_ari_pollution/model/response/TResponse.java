package com.example.da_ari_pollution.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TResponse<T> implements Serializable {

    private static final long serialVersionUID = 2182083386459295890L;

    private String code;

    private String message;

    private T data;

    public TResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

//    public Collection<Object> getData() {
//    }

//    public long getCode() {
//    }
//
//    public long getMessage() {
//    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
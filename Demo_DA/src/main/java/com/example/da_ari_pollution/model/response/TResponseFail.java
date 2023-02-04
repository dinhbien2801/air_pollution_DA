package com.example.da_ari_pollution.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TResponseFail<T> extends TResponse implements Serializable {


    private static final long serialVersionUID = -6698339246183948893L;

//    @ApiModelProperty(notes = "Mã định danh của request trả về", example = "22e80e12-6983-4222-82a1-e10c71f85489")
    protected String requestId;

}
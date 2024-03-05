package com.fazo.esm.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorData {


    private String msg;

    private Integer code;

    public ErrorData(String msg) {
        this.msg = msg;
        this.code = null;
    }


}

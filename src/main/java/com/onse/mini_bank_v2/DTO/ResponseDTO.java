package com.onse.mini_bank_v2.DTO;

import lombok.Builder;
import lombok.Data;

@Data
public class ResponseDTO<Body> {
    private String message;
    private Body body;
}

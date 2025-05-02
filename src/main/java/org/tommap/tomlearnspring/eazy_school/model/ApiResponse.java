package org.tommap.tomlearnspring.eazy_school.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    @Builder
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Builder
    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

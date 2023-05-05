package com.github.mopai.jkit.lang.enums;

public enum TrimMode {
    LEFT(-1),
    RIGHT(1),
    ALL(0);
    private final Integer code;

    TrimMode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

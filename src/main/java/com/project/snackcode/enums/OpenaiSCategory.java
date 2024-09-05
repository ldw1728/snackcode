package com.project.snackcode.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum OpenaiSCategory {

    DEFI("definition"),
    GRAM("grammar"),
    TECH("technology"),
    //TREN("trend"),
    NEWS("recent news or blog post with source"),
    CMSS("common sense");

    private String value;

    OpenaiSCategory(String value) {
        this.value = value;
    }

    public static String getStringValues() {
        List<String> list = Arrays.stream(OpenaiSCategory.values()).map(cate -> cate.value).collect(Collectors.toList());
        return String.join(", ", list);
    }
}

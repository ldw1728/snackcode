package com.project.snackcode.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum OpenaiMCategory {

    LANG("programmingLanguages"),
    ITWD("trend ItWords"),
    ARCH("Architecture"),
    FRMW("framework"),
    LIBR("library"),
    INFO("Employment infomation for developer");

    private String value;

    OpenaiMCategory(String value) {
        this.value = value;
    }

    public static String getStringValues() {
        List<String> list = Arrays.stream(OpenaiMCategory.values()).map(cate -> cate.value).collect(Collectors.toList());
        return String.join(", ", list);
    }
}

package com.project.snackcode.enums;

import lombok.Getter;
import org.apache.commons.lang3.EnumUtils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum OpenaiMCategory {

    LANG("programmingLanguages"),
    ITWD("ItWords"),
    ARCH("Architecture"),
    FRMW("framework"),
    LIBR("library and API");
    //INFO("Employment infomation for developer");

    private String value;

    OpenaiMCategory(String value) {
        this.value = value;
    }

    public static String getStringValues() {
        List<String> list = Arrays.stream(OpenaiMCategory.values()).map(cate -> cate.value).collect(Collectors.toList());
        return String.join(", ", list);
    }

    public static OpenaiMCategory getRandomCate() {
        OpenaiMCategory[] enumArr = OpenaiMCategory.values();
        SecureRandom random = new SecureRandom();
        return List.of(enumArr).get(random.nextInt(enumArr.length));
    }
}

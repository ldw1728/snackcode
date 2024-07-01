package com.project.snackcode.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


/**
 *  개인정보 암호화
 *  추후에 작업
 */
@Converter
public class EncryptConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return null;
    }
}

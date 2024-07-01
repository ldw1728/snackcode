package com.project.snackcode.converter;

import com.project.snackcode.enums.EnumCodes;
import jakarta.persistence.AttributeConverter;

/**
 * enum < -- > DBData
 * @param <T>
 */
public class EnumCodeConverter<T extends EnumCodes> implements AttributeConverter<T, String> {

    private Class<T> tmplEnum;

    public EnumCodeConverter(Class<T> tmplEnum) {
        this.tmplEnum = tmplEnum;
    }

    @Override
    public String convertToDatabaseColumn(T enumCode) {
        if(enumCode == null){
            return null;
        }

        return enumCode.getCode();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        T[] enumConstants = tmplEnum.getEnumConstants();
        for(T enumConstant : enumConstants) {
            if( enumConstant.getCode().equals(dbData) ) {
                return enumConstant;
            }
        }
        return null;
    }
}

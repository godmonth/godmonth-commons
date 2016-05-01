package com.godmonth.util.jackson.converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER })
@JsonSerialize(converter = UnixTimestampConverterSerializer.class)
@JsonDeserialize(converter = UnixTimestampConverterDeserializer.class)
public @interface JsonUnixTimestampConverter {

}

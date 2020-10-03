package com.retrofit.forecast.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Supplier;

@Slf4j
@Component
public class JsonUtils {

    private static final Supplier<ObjectMapper> OBJECT_MAPPER = () -> {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION,true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    };

    public static String getJsonStringFromPojo(final Object jsonObj) {
        String out = null;
        try{
            out = OBJECT_MAPPER.get().writeValueAsString(jsonObj);
        } catch (IOException e) {
            System.out.println(String.format("json object : {}, error : {}",jsonObj,e,e));
            out = null;
        }
        return out;
    }

}

package com.volunteer.volunteer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 用于处理json数据和对象之间的转换工具，使用Mapper工具实在是太强了
 */
public class JsonUtil {
    public static<T> String objectToJson(T object)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
    public static<T> T jsonToObject(String json,Class<T> clazz)throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json,clazz);
    }
}

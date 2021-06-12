/**
 * © 2014 Peace Soft Solution
 */
package com.ducterry.base.ducterrybase.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;


public abstract class JSONFactory {

    public static String toString(Object value) {
        GsonBuilder localGsonBuilder = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL);
        return localGsonBuilder.disableHtmlEscaping().create().toJson(value);
    }

    public static String prettyPrint(Object value) {
        ObjectMapper objectMapper = new ObjectMapper();
        String objectString;
        objectString = "{\n}";
        try {
            objectString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (IOException e) {
        }
        return objectString;
    }

    public static Gson create() {
        return new GsonBuilder()
                .registerTypeAdapter(Long.class, new BadLongDeserializer())
                .disableHtmlEscaping().create();
    }

    public static String toJson(Object object) {
        return create().toJson(object);
    }

    public static String toJsonJackson(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> T fromJSON(String json, Class<T> paramClass) throws JsonSyntaxException {
        return create().fromJson(json, paramClass);
    }
}

class BadLongDeserializer implements JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        try {
            String str = element.getAsString();
            str = str.replaceAll(",", "");
            str = str.split("\\.")[0];
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            throw new JsonParseException(e);
        }
    }

}


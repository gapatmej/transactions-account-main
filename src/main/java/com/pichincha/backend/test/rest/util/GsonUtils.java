package com.pichincha.backend.test.rest.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtils {

    private static Gson gson = new Gson();

    public static <T> T jsonToEntity (String json,Class<T> entity){
        T a = gson.fromJson(json, (Type) entity);
        return a;
    }

    public static String entityToJson (Object entity){
        return gson.toJson(entity);
    }

}

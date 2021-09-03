package com.pichincha.backend.test.rest.util;

import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HeaderUtil {

    private static String applicationName ;


    public static void setApplicationName(String applicationName){
        HeaderUtil.applicationName = applicationName;
    }

    private HeaderUtil() {
    }

    /**
     * <p>createAlert.</p>
     *
     * @param message a {@link String} object.
     * @param param a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createAlert( String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + applicationName + "-alert", message);
        try {
            headers.add("X-" + applicationName + "-params", URLEncoder.encode(param, StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {

        }
        return headers;
    }

    /**
     * <p>createEntityCreationAlert.</p>
     *
     * @param entityName a {@link String} object.
     * @param param a {@link String} object.
     * @return a {@link HttpHeaders} object.
     */
    public static HttpHeaders createEntityCreationAlert(String entityName, Object param) {
        String message = "A new " + entityName + " is created with identifier " + param;
        return createAlert(message, param.toString());
    }

}

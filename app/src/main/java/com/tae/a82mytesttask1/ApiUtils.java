package com.tae.a82mytesttask1;

public class ApiUtils {

    public static final String API_URL = "https://spring-boot-mysql-server-part0.herokuapp.com/";

    public ApiUtils() {
    }

    public static MarkInterface getMarkInterface(){
        return RetrofitClient.getMark(API_URL).create(MarkInterface.class);
    }

}
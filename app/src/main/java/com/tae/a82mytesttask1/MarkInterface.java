// нужно вызывать сервер перед запуском приложения, например через постман
// "https://spring-boot-mysql-server-part0.herokuapp.com/"

package com.tae.a82mytesttask1;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MarkInterface {

    @GET("/api/books/")
    Call<ArrayList<Mark>> getMarks();

    @GET("/api/books/{id}")
    Call<Mark> getMark(@Path("id") int id);

    @POST("/api/books/create")
    Call<Mark> addMark(@Body Mark mark);

    @PUT("/api/books/{id}")
    Call<Mark> updateMark(@Path("id") int id, @Body Mark mark);

    @DELETE("/api/books/{id}")
    Call<Mark> deleteMark(@Path("id") int id);
}

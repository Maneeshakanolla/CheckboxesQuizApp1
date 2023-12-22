package com.example.quizappchekbox2.services;



import com.example.quizappchekbox2.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionApi {
    @GET("myquizeapiexample2.php")
    Call<QuestionList> getQuestion();
}

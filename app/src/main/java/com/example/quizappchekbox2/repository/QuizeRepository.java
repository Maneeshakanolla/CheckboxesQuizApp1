package com.example.quizappchekbox2.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.quizappchekbox2.model.QuestionList;
import com.example.quizappchekbox2.services.QuestionApi;
import com.example.quizappchekbox2.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuizeRepository {

    QuestionApi questionApi;

    public QuizeRepository() {

        this.questionApi = new RetrofitInstance().getRetrofitInstance();
    }

    public LiveData<QuestionList> getQuestionFromApi() {
        MutableLiveData<QuestionList> data = new MutableLiveData<>();

        Call<QuestionList> response = questionApi.getQuestion();

        response.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {


                QuestionList questionList = response.body();

                data.setValue(questionList);


            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {

            }
        });

        return data;

    }




}

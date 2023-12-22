package com.example.quizappchekbox2.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizappchekbox2.model.QuestionList;
import com.example.quizappchekbox2.repository.QuizeRepository;


public class QuizeViewModel extends ViewModel {

    QuizeRepository repository = new QuizeRepository();


    LiveData<QuestionList> questionListLiveData;

    public QuizeViewModel(){
        questionListLiveData = repository.getQuestionFromApi();
    }

    public LiveData<QuestionList> getQuestionListLiveData() {
        return questionListLiveData;
    }
}

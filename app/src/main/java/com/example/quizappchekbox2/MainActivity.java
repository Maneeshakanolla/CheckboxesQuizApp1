package com.example.quizappchekbox2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.quizappchekbox2.databinding.ActivityMainBinding;
import com.example.quizappchekbox2.model.Question;
import com.example.quizappchekbox2.model.QuestionList;
import com.example.quizappchekbox2.viewModel.QuizeViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;


    QuizeViewModel quizViewModel;

    List<Question> quizquestionList;

    static int result = 0;
    static int totalQuestions = 0;
    int correctAnswers = 0;

    int i = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        quizViewModel = new ViewModelProvider(this).get(QuizeViewModel.class);


        result = 0;

        totalQuestions = 0;

        DisplayFirstQuestion();
        activityMainBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNextQuest();
            }
        });

    }

    //fetches questions and displays the  first question
    private void DisplayFirstQuestion() {

        quizViewModel.getQuestionListLiveData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questions) {


                quizquestionList = questions;
                totalQuestions = quizquestionList.size();


                activityMainBinding.txtQuestion.setText("Question 1" + questions.get(0).getQuestion());
                activityMainBinding.check1.setText(questions.get(0).getOption1());
                activityMainBinding.check2.setText(questions.get(0).getOption2());
                activityMainBinding.check3.setText(questions.get(0).getOption3());
                activityMainBinding.check4.setText(questions.get(0).getOption4());


            }
        });
    }
    //here handles the next button and checks answers ,display the next question
    private void DisplayNextQuest(){
        if(activityMainBinding.btnNext.getText().toString().equals("Finish")){
            Intent intent=new Intent(MainActivity.this, MainActivityResult.class);
            startActivity(intent);
            finish();
        }
        int selectedOptionsCount = getSelectedOptionsCount();


     
            List<CheckBox> selectedCheckboxes = getSelectedCheckboxes();
        // if condition for selecting two check boxes
           if ( selectedOptionsCount>1 && selectedOptionsCount<3) {

              if (quizquestionList.size() -1>0) {
                  totalQuestions = quizquestionList.size();

                    String correctOption1 = quizquestionList.get(i).getCorrectOption1();
                    String correctOption2 = quizquestionList.get(i).getCorrectOption2();

                    if (areOptionsCorrect(selectedCheckboxes,correctOption1,correctOption2) ){
                        result++;
                        activityMainBinding.txtResult.setText("correct answer: " + result);

                    }
                  if (i == 0) {
                      i++;
                  }

                  displayQuestion();
                  if (i == (quizquestionList.size() - 1)) {
                      activityMainBinding.btnNext.setText("Finish");
                  }
                  clearAllCheckboxes();
                  i++;

              }

                 //result++;
                // activityMainBinding.txtResult.setText("correct answer: " + result);


                else {
                    Toast.makeText(this, "Please select exactly two answers", Toast.LENGTH_SHORT).show();
                }
           
           }


        


        }
    //updates the specific index of the questions
    private void displayQuestion() {
        Question currentQuiz = quizquestionList.get(i);
        activityMainBinding.txtQuestion.setText(String.format("Question" + ((i + 1)) + ":" + currentQuiz.getQuestion()));
        activityMainBinding.check1.setText(currentQuiz.getOption1());
        activityMainBinding.check2.setText(currentQuiz.getOption2());
        activityMainBinding.check3.setText(currentQuiz.getOption3());
        activityMainBinding.check4.setText(currentQuiz.getOption4());
    }
    //count the number of selected checkboxes
    private int getSelectedOptionsCount() {
        int count = 0;
        if (activityMainBinding.check1.isChecked()) {
            count++;
        }
        if (activityMainBinding.check2.isChecked()) {
            count++;
        }

        if (activityMainBinding.check3.isChecked()) {
            count++;
        }
        if (activityMainBinding.check4.isChecked()) {
            count++;
        }
        return count;
    }

    private List<CheckBox> getSelectedCheckboxes() {
        List<CheckBox> selectedCheckboxes = new ArrayList<>();
        if (activityMainBinding.check1.isChecked()) {
            selectedCheckboxes.add(activityMainBinding.check1);
        }
       else if (activityMainBinding.check2.isChecked()) {
            selectedCheckboxes.add(activityMainBinding.check2);
        }
       else  if (activityMainBinding.check3.isChecked()) {
            selectedCheckboxes.add(activityMainBinding.check3);
        }
       else  if (activityMainBinding.check4.isChecked()) {
            selectedCheckboxes.add(activityMainBinding.check4);
        }
        return selectedCheckboxes;
    }
    //clear all check boxes
    private void clearAllCheckboxes() {
        activityMainBinding.check1.setChecked(false);
        activityMainBinding.check2.setChecked(false);
        activityMainBinding.check3.setChecked(false);
        activityMainBinding.check4.setChecked(false);
    }
    //here checks if the selected options match the correct answers
    private boolean areOptionsCorrect(List<CheckBox> selectedCheckboxes, String correctOption1, String correctOption2) {
        if (selectedCheckboxes.size() != 2) {
            return false;
        }

        String selectedOption1 = selectedCheckboxes.get(0).getText().toString();
        String selectedOption2 = selectedCheckboxes.get(1).getText().toString();

        boolean resultData = (correctOption1.equals(selectedOption1) && correctOption2.equals(selectedOption2)) ||
                (correctOption1.equals(selectedOption2) && correctOption2.equals(selectedOption1));

        return resultData;


    }

}
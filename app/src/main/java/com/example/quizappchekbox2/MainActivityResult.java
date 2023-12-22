package com.example.quizappchekbox2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.quizappchekbox2.databinding.ActivityMainResultBinding;

public class MainActivityResult extends AppCompatActivity {
    ActivityMainResultBinding resultBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_result);

        resultBinding= DataBindingUtil.setContentView(this,R.layout.activity_main_result);

        resultBinding.txtAnswer.setText("Your Score is : " + MainActivity.result + "/" + MainActivity.totalQuestions);


        resultBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(MainActivityResult.this,MainActivity.class);

                startActivity(i);

            }

        });


    }
}
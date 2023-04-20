package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private TextView status, user, correctAnswers, average;
    private AppCompatButton finish, retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        status = findViewById(R.id.status);
        user = findViewById(R.id.user);
        correctAnswers = findViewById(R.id.correctAnswers);
        average = findViewById(R.id.average);
        finish = findViewById(R.id.finish);
        retry = findViewById(R.id.retry);

        final String userName = getIntent().getStringExtra("userName");
        final int correctCount = Integer.parseInt(getIntent().getStringExtra("correctAnswers"));
        final int totalCount = Integer.parseInt(getIntent().getStringExtra("totalQuestions"));

        double avg = (double) correctCount/ (double) totalCount * 100.0;

        if (avg >= 75) {
            status.setText("Good job");
        } else {
            status.setText("Let's try again");
        }

        user.setText(userName);
        correctAnswers.setText(correctCount + " / " + totalCount);
        average.setText("Your average is " + avg + "%");

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(ResultsActivity.this, QuizActivity.class);
                quizIntent.putExtra("userName", userName);
                startActivity(quizIntent);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
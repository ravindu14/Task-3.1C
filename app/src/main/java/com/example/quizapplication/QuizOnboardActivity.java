package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuizOnboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_onboard);

        Button quizStartButton = findViewById(R.id.start_quiz_button);
        EditText nameInput = findViewById(R.id.name_input);

        quizStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameInput.getText().toString().equals("")) {
                    nameInput.setError("Please enter your name");
                    return;
                } else {
                    Intent quizIntent = new Intent(QuizOnboardActivity.this, QuizActivity.class);
                    quizIntent.putExtra("userName", nameInput.getText().toString());
                    startActivity(quizIntent);
                }
            }
        });
    }
}
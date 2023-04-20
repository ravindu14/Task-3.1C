package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView title, questionCount, question;
    private AppCompatButton option1, option2, option3, option4, submit, next;
    private List<QuestionsList> questionsList;
    private int currentQuestionPosition = 0;
    private String selectedAnswerByUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final String userName = getIntent().getStringExtra("userName");

        title = findViewById(R.id.quizTitle);
        title.setText("Welcome "+ userName);

        questionCount = findViewById(R.id.questionCount);
        question = findViewById(R.id.questionContainer);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submit = findViewById(R.id.submitButton);
        next = findViewById(R.id.nextButton);

        next.setVisibility(View.INVISIBLE);

        questionsList = QuestionsStore.getQuestions();

        questionCount.setText((currentQuestionPosition+1)+ " / "+ questionsList.size());
        question.setText(questionsList.get(currentQuestionPosition).getQuestion());

        option1.setText(questionsList.get(currentQuestionPosition).getOption1());
        option2.setText(questionsList.get(currentQuestionPosition).getOption2());
        option3.setText(questionsList.get(currentQuestionPosition).getOption3());
        option4.setText(questionsList.get(currentQuestionPosition).getOption4());



        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOptionViews();
                option1.setBackgroundResource(R.drawable.round_back_white10_2);
                questionsList.get(currentQuestionPosition).setUserSelectedAnswer(questionsList.get(currentQuestionPosition).getOption1());
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOptionViews();
                option2.setBackgroundResource(R.drawable.round_back_white10_2);
                questionsList.get(currentQuestionPosition).setUserSelectedAnswer(questionsList.get(currentQuestionPosition).getOption2());
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOptionViews();
                option3.setBackgroundResource(R.drawable.round_back_white10_2);
                questionsList.get(currentQuestionPosition).setUserSelectedAnswer(questionsList.get(currentQuestionPosition).getOption3());
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOptionViews();
                option4.setBackgroundResource(R.drawable.round_back_white10_2);
                questionsList.get(currentQuestionPosition).setUserSelectedAnswer(questionsList.get(currentQuestionPosition).getOption4());
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableOptions();

                final String userSelected = questionsList.get(currentQuestionPosition).getUserSelectedAnswer();

                if(option1.getText().toString().equals(userSelected)) {
                    option1.setBackgroundResource(R.drawable.round_back_red10_2);
                    option1.setTextColor(Color.WHITE);
                } else if (option2.getText().toString().equals(userSelected)) {
                    option2.setBackgroundResource(R.drawable.round_back_red10_2);
                    option2.setTextColor(Color.WHITE);
                } else if (option3.getText().toString().equals(userSelected)) {
                    option3.setBackgroundResource(R.drawable.round_back_red10_2);
                    option3.setTextColor(Color.WHITE);
                } else if (option4.getText().toString().equals(userSelected)) {
                    option4.setBackgroundResource(R.drawable.round_back_red10_2);
                    option4.setTextColor(Color.WHITE);
                }

                revealAnswer();

                if(currentQuestionPosition == questionsList.size() - 1) {
                    next.setText("View Results");
                }

                submit.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableOptions();
                resetOptionViews();
                submit.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);

                if(currentQuestionPosition < questionsList.size() - 1) {
                    currentQuestionPosition++;

                    questionCount.setText((currentQuestionPosition+1)+ " / "+ questionsList.size());
                    question.setText(questionsList.get(currentQuestionPosition).getQuestion());

                    option1.setText(questionsList.get(currentQuestionPosition).getOption1());
                    option2.setText(questionsList.get(currentQuestionPosition).getOption2());
                    option3.setText(questionsList.get(currentQuestionPosition).getOption3());
                    option4.setText(questionsList.get(currentQuestionPosition).getOption4());
                } else {
                    String correctAnswers = String.valueOf(getCorrectAnswers());
                    String totalCount = String.valueOf(questionsList.size());

                    System.out.println(correctAnswers);

                    Intent resultsIntent = new Intent(QuizActivity.this, ResultsActivity.class);
                    resultsIntent.putExtra("userName", userName);
                    resultsIntent.putExtra("correctAnswers", correctAnswers);
                    resultsIntent.putExtra("totalQuestions", totalCount);
                    startActivity(resultsIntent);
                }
            }
        });
    }

    private void enableOptions() {
        option1.setClickable(true);
        option2.setClickable(true);
        option3.setClickable(true);
        option4.setClickable(true);
    }

    private void disableOptions() {
        option1.setClickable(false);
        option2.setClickable(false);
        option3.setClickable(false);
        option4.setClickable(false);
    }

    private void resetOptionViews() {
        option1.setBackgroundResource(R.drawable.round_back_white_stroke2);
        option1.setTextColor(Color.parseColor("#010C29"));

        option2.setBackgroundResource(R.drawable.round_back_white_stroke2);
        option2.setTextColor(Color.parseColor("#010C29"));

        option3.setBackgroundResource(R.drawable.round_back_white_stroke2);
        option3.setTextColor(Color.parseColor("#010C29"));

        option4.setBackgroundResource(R.drawable.round_back_white_stroke2);
        option4.setTextColor(Color.parseColor("#010C29"));
    }

    private int getCorrectAnswers() {
        int correctAnswers = 0;

        for(int i=0; i< questionsList.size(); i++) {
            final String userSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String correctAnswer = questionsList.get(i).getAnswer();

            if(userSelectedAnswer.equals(correctAnswer)) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    private int getInCorrectAnswers() {
        int inCorrectAnswers = 0;

        for(int i=0; i< questionsList.size(); i++) {
            final String userSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String correctAnswer = questionsList.get(i).getAnswer();

            if(!userSelectedAnswer.equals(correctAnswer)) {
                inCorrectAnswers++;
            }
        }

        return inCorrectAnswers;
    }

    private void revealAnswer() {
        final String correctAnswer = questionsList.get(currentQuestionPosition).getAnswer();

        if(option1.getText().toString().equals(correctAnswer)) {
            option1.setBackgroundResource(R.drawable.round_back_green10_2);
            option1.setTextColor(Color.WHITE);
        } else if (option2.getText().toString().equals(correctAnswer)) {
            option2.setBackgroundResource(R.drawable.round_back_green10_2);
            option2.setTextColor(Color.WHITE);
        } else if (option3.getText().toString().equals(correctAnswer)) {
            option3.setBackgroundResource(R.drawable.round_back_green10_2);
            option3.setTextColor(Color.WHITE);
        } else if (option4.getText().toString().equals(correctAnswer)) {
            option4.setBackgroundResource(R.drawable.round_back_green10_2);
            option4.setTextColor(Color.WHITE);
        }
    }
}
package com.example.quizapplication;

import java.util.ArrayList;
import java.util.List;

public class QuestionsStore {
    private static List<QuestionsList> questions() {

        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Select best answer to explain what is android", "Web browser", "Operating System", "Web server", "Mobile Application", "Operating System", "");
        final QuestionsList question2 = new QuestionsList("What is an Activity in android", "Android Class", "Android Package", "Single Screen in App", "Android library", "Single Screen in App", "");
        final QuestionsList question3 = new QuestionsList("Which virtual machine is used by android operating system?", "Dalvic OS", "JVM", "LVM", "Simple Virtual Machine", "Dalvic OS", "");
        final QuestionsList question4 = new QuestionsList("Identify the language which android is based on", "Python", "C++", "Java", "PHP", "Java", "");
        final QuestionsList question5 = new QuestionsList("The full form of the APK is", "Android Page Kit", "Android Phone Kit", "Android Package Kit", "Android Photo Kit", "Android Package Kit", "");

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);

        return questionsList;
    }

    public static List<QuestionsList> getQuestions() {
        return questions();
    }
}

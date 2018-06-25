package com.example.pavelorekhov.quizapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "QuizActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button nextButton = findViewById(R.id.next_button);
        final Button prevButton = findViewById(R.id.prev_button);
        final Button trueButton = findViewById(R.id.true_button);
        final Button falseButton = findViewById(R.id.false_button);
        final TextView textView = findViewById(R.id.text_view);

        textView.setText(QuestionModel.questions[QuestionModel.getCurrentQuestionPos()].getText());

        nextButton.setOnClickListener(view -> {
            textView.setText(QuestionModel.questions[QuestionModel.moveToNextQuestionPos()].getText());
        });

        prevButton.setOnClickListener(view -> {
            textView.setText(QuestionModel.questions[QuestionModel.moveToPrevQuestionPos()].getText());
        });

        trueButton.setOnClickListener(view -> {
            QuestionModel.Question curQ = QuestionModel.questions[QuestionModel.getCurrentQuestionPos()];
            if (curQ.isAnswer()) {
                Toast.makeText(this, "Correcto!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrecto!", Toast.LENGTH_SHORT).show();
            }
        });

        falseButton.setOnClickListener(view -> {
            QuestionModel.Question curQ = QuestionModel.questions[QuestionModel.getCurrentQuestionPos()];
            if (curQ.isAnswer()) {
                Toast.makeText(this, "Incorrecto!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Correcto!", Toast.LENGTH_SHORT).show();
            }
        });

        Log.d(TAG, "Everything is done");
    }
}

package com.example.pavelorekhov.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "QuizActivity";
    static final String ANSWER = "answer";
    static final int CHEAT_REQUEST_CODE = 0;

    private boolean isCheater = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Capitals");

        final Button nextButton = findViewById(R.id.next_button);
        final Button prevButton = findViewById(R.id.prev_button);
        final Button trueButton = findViewById(R.id.true_button);
        final Button falseButton = findViewById(R.id.false_button);
        final Button cheatButton = findViewById(R.id.cheat_button);
        final TextView textView = findViewById(R.id.text_view);

        textView.setText(QuestionModel.questions[QuestionModel.getCurrentQuestionPos()].getText());

        nextButton.setOnClickListener(view -> {
            isCheater = false;
            textView.setText(QuestionModel.questions[QuestionModel.moveToNextQuestionPos()].getText());
        });

        prevButton.setOnClickListener(view -> {
            isCheater = false;
            textView.setText(QuestionModel.questions[QuestionModel.moveToPrevQuestionPos()].getText());
        });

        trueButton.setOnClickListener(view -> {
            QuestionModel.Question curQ = QuestionModel.questions[QuestionModel.getCurrentQuestionPos()];
            if (curQ.isAnswerTrue()) {
                if (isCheater) {
                    Toast.makeText(this, "You fucking cheater!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Correcto!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Incorrecto!", Toast.LENGTH_SHORT).show();
            }
        });

        falseButton.setOnClickListener(view -> {
            QuestionModel.Question curQ = QuestionModel.questions[QuestionModel.getCurrentQuestionPos()];
            if (curQ.isAnswerTrue()) {
                Toast.makeText(this, "Incorrecto!", Toast.LENGTH_SHORT).show();
            } else {
                if (isCheater) {
                    Toast.makeText(this, "You fucking cheater!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Correcto!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cheatButton.setOnClickListener(view -> {
            Intent intentWithExtras = new Intent(this, CheatActivity.class);
            intentWithExtras.putExtra(ANSWER, true);
            startActivityForResult(intentWithExtras, CHEAT_REQUEST_CODE);
        });

        Log.d(TAG, "Everything is done");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHEAT_REQUEST_CODE) {
            if (data == null) {
                return;
            }
            isCheater = data.getBooleanExtra(CheatActivity.DID_USER_CHEAT, false);
        }
    }
}

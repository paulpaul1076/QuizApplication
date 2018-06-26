package com.example.pavelorekhov.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "QuizActivity";
    static final String CHEAT_POS = "CHEAT_POS";
    static final String ANSWER = "answer";
    static final int CHEAT_REQUEST_CODE = 0;

    private Set<Integer> cheatedPositions = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Capitals");

        if (savedInstanceState != null) {
            ArrayList<Integer> arrayList = savedInstanceState.getIntegerArrayList(CHEAT_POS);
            if (arrayList != null) {
                cheatedPositions = new HashSet<>(arrayList);
            }
        }

        final Button nextButton = findViewById(R.id.next_button);
        final Button prevButton = findViewById(R.id.prev_button);
        final Button trueButton = findViewById(R.id.true_button);
        final Button falseButton = findViewById(R.id.false_button);
        final Button cheatButton = findViewById(R.id.cheat_button);
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
            if (curQ.isAnswerTrue()) {
                if (cheatedPositions.contains(QuestionModel.getCurrentQuestionPos())) {
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
                if (cheatedPositions.contains(QuestionModel.getCurrentQuestionPos())) {
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Integer> arrayList = new ArrayList<>(cheatedPositions);
        outState.putIntegerArrayList(CHEAT_POS, arrayList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHEAT_REQUEST_CODE) {
            if (data == null) {
                return;
            }
            if (data.getBooleanExtra(CheatActivity.DID_USER_CHEAT, false)) {
                cheatedPositions.add(QuestionModel.getCurrentQuestionPos());
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}

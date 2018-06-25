package com.example.pavelorekhov.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String DID_USER_CHEAT = "didUserCheat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setTitle(R.string.cheat_activity_title);

        final Button showAnswerButton = findViewById(R.id.show_answer_button);
        final Button backButton = findViewById(R.id.back_button);
        final TextView textView = findViewById(R.id.answer_text_view);

        showAnswerButton.setOnClickListener(view -> {
            textView.setText(String.valueOf(QuestionModel.questions[QuestionModel.getCurrentQuestionPos()].isAnswerTrue()));
            Intent data = new Intent();
            data.putExtra(DID_USER_CHEAT, true);
            setResult(Activity.RESULT_OK, data);
        });

        backButton.setOnClickListener(view -> {
            this.finish();
        });
    }
}

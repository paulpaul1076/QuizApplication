package com.example.pavelorekhov.quizapplication;

import android.support.annotation.StringRes;

public class QuestionModel {
    public static Question[] questions = {
            new Question(R.string.q1, true),
            new Question(R.string.q2, false),
            new Question(R.string.q3, true),
            new Question(R.string.q4, true),
            new Question(R.string.q5, false),
            new Question(R.string.q6, true)
    };
    private static int currentQuestionPos = 0;

    public static int getCurrentQuestionPos() {
        return currentQuestionPos;
    }

    public static int moveToNextQuestionPos() {
        currentQuestionPos = (currentQuestionPos + 1) % questions.length;
        return currentQuestionPos;
    }

    public static int moveToPrevQuestionPos() {
        if (currentQuestionPos == 0) {
            currentQuestionPos = questions.length - 1;
        } else {
            currentQuestionPos = currentQuestionPos - 1;
        }
        return currentQuestionPos;
    }

    public static class Question {
        private @StringRes
        int text;
        private boolean answer;

        public Question(@StringRes int text, boolean answer) {
            this.text = text;
            this.answer = answer;
        }

        public @StringRes
        int getText() {
            return text;
        }

        public boolean isAnswerTrue() {
            return answer;
        }
    }
}

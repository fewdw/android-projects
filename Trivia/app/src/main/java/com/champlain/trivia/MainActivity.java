package com.champlain.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.champlain.trivia.data.AnswerListAsyncResponse;
import com.champlain.trivia.data.Repository;
import com.champlain.trivia.databinding.ActivityMainBinding;
import com.champlain.trivia.model.Question;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String MESSAGE_ID = "message";
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    private ActivityMainBinding binding;
    private int currentQuestionIndex = 1;
    List<Question> questionsList;
    int questionCounter = 0;
    boolean submitted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        questionsList = new Repository().getQuestions(questionArrayList ->{
                //Log.d("main", "oncreate: " + questionArrayList.size());
                binding.questionTextView.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
            updateCounter(questionArrayList);
        });

        binding.buttonNext.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionsList.size();
            updateQuestion();

        });

        binding.buttonPrev.setOnClickListener(view -> {
            if(currentQuestionIndex>1){
                currentQuestionIndex = (currentQuestionIndex - 1) % questionsList.size();
                updateQuestion();
            }
        });

        binding.buttonTrue.setOnClickListener(view -> {
            CheckAnswer(true);
            updateQuestion();
        });
        binding.buttonFalse.setOnClickListener(view -> {
            CheckAnswer(false);
            updateQuestion();
        });

        //SUBMIT BUTTON
        binding.buttonScore.setOnClickListener(view -> {
            if(submitted==false){
                submitted = true;
                for (int i = 0; i < currentQuestionIndex; i++) {
                    if(questionsList.get(i).isAnswered() == true){
                        questionCounter++;
                    }
                }
                binding.questionTextView.setText("Score: "+(questionCounter)+"/"+(currentQuestionIndex));

                currentQuestionIndex = 0;
                questionCounter = 0;
                
                binding.buttonScore.setText("Restart?");
            }else {
                restartQuiz();
            }

        });

        binding.saveButton.setOnClickListener(view -> {
            int score = currentQuestionIndex;
            int exitScore = 0;
            SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("questionIndex",score);

            for (int i = 0; i < currentQuestionIndex; i++) {
                if(questionsList.get(i).isAnswered() == true){
                    exitScore++;
                }
            }
            editor.putInt("score",exitScore);
            editor.apply();
        });

        binding.restoreButton.setOnClickListener(view -> {
            SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
            int value = getSharedData.getInt("questionIndex",1);
            int value2 = getSharedData.getInt("score",0);
            currentQuestionIndex = value;
            questionCounter = value2;
            updateQuestion();
            value = 0;
        });
    }

    private void restartQuiz() {
        currentQuestionIndex = 1;
        updateQuestion();
        binding.buttonScore.setText("Score");
        submitted = false;
        Collections.shuffle(questionsList);
        for (int i = 0; i < questionsList.size(); i++) {
            questionsList.get(i).setAnswered(false);
        }
    }

    private void CheckAnswer(boolean userChoseCorrect) {
        boolean answer = questionsList.get(currentQuestionIndex).isAnswerTrue();
        int snackMessageId = 0;
        if(userChoseCorrect == answer){
            snackMessageId = R.string.correct_answer;
            questionsList.get(currentQuestionIndex).setAnswered(true);
            fadeAnimation();
        }else{
         snackMessageId = R.string.incorrect;
            questionsList.get(currentQuestionIndex).setAnswered(false);
         shakeAnimation();
        }
        Snackbar.make(binding.cardView, snackMessageId,Snackbar.LENGTH_SHORT).show();
    }

    private void updateCounter(ArrayList<Question> questionArrayList) {
        binding.textViewOutOf.setText(String.format("Question: %d/%d", currentQuestionIndex, questionArrayList.size()));
    }

    private void updateQuestion() {
        String question = questionsList.get(currentQuestionIndex).getAnswer();
        binding.questionTextView.setText(question);
        updateCounter((ArrayList<Question>)questionsList);
    }

    private void fadeAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatCount(Animation.REVERSE);

        binding.cardView.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.GREEN);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void shakeAnimation(){
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake_animation);
        binding.cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    public void onClick(View view) {}
}
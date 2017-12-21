package com.warpgatetechnologies.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean mTimerStarted;
    private CountDownTimer mCountDownTimer;
    private TextView mTextViewCountdown, mTextViewRunningScore, mTextViewEquation;
    private TextView mTextViewStatus, mTextViewAnswer1, mTextViewAnswer2, mTextViewAnswer3, mTextViewAnswer4;
    private Button mButtonPlay;
    private String mEquation;
    private int mAnswer;
    private Random mRandom = new Random();
    private int mWins, mLosses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimerStarted = false;
        mWins = 0;
        mLosses = 0;
        mTextViewCountdown = findViewById(R.id.txvTimer);
        mButtonPlay = findViewById(R.id.btnPlay);
        mTextViewStatus = findViewById(R.id.textviewGameStatus);
        mTextViewAnswer1 = findViewById(R.id.txvAnswer1);
        mTextViewAnswer2 = findViewById(R.id.txvAnsewr2);
        mTextViewAnswer3 = findViewById(R.id.txvAnswer3);
        mTextViewAnswer4 = findViewById(R.id.txvAnsewr4);
        mTextViewEquation = findViewById(R.id.txvEquation);
        mTextViewRunningScore = findViewById(R.id.txvScore);
        mTextViewStatus = findViewById(R.id.textviewGameStatus);

        mButtonPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleTimer();
            }
        });
        

        mTextViewAnswer1.setOnClickListener(this);
        mTextViewAnswer2.setOnClickListener(this);
        mTextViewAnswer3.setOnClickListener(this);
        mTextViewAnswer4.setOnClickListener(this);


    }


    public void toggleTimer(){
        if (!mTimerStarted) {
            mTimerStarted = true;

            mButtonPlay.setVisibility(View.INVISIBLE);

            setAnswers();

            mCountDownTimer = new CountDownTimer(30000 + 100, 1000) {
                @Override
                public void onTick(long millisecondsTillDone) {

                    int progress = (int) millisecondsTillDone / 1000;
                    String currentTime = progress + "s";
                    mTextViewCountdown.setText(currentTime);

                }

                @Override
                public void onFinish() {
                    mButtonPlay.setVisibility(View.VISIBLE);
                    mTimerStarted = false;
                    mTextViewCountdown.setText(R.string.default_timer);
                    mTextViewStatus.setText("Your Score : " + mTextViewRunningScore.getText().toString());
                    mTextViewRunningScore.setText("0/0");
                    mWins = 0;
                    mLosses = 0;
                }
            }.start();
        }
    }

    private void setAnswers() {

        int insertToTextView;

        randomEquation(randomNum(10), randomNum(10));
        mTextViewEquation.setText(mEquation);


        insertToTextView = randomNum(4);

        String sAnswer = Integer.toString(mAnswer);
        switch (insertToTextView){
            case 1:
                mTextViewAnswer1.setText(sAnswer);
                setWrongAnswers(1);
                break;
            case 2:
                mTextViewAnswer2.setText(sAnswer);
                setWrongAnswers(2);
                break;
            case 3:
                mTextViewAnswer3.setText(sAnswer);
                setWrongAnswers(3);
                break;
            case 4:
                mTextViewAnswer4.setText(sAnswer);
                setWrongAnswers(4);
                break;
        }

    }

    private void setWrongAnswers(int i) {
        switch (i) {
            case 1:
                mTextViewAnswer2.setText(toString(randomNum(50)));
                mTextViewAnswer3.setText(toString(randomNum(50)));
                mTextViewAnswer4.setText(toString(randomNum(50)));
                break;
            case 2:
                mTextViewAnswer1.setText(toString(randomNum(50)));
                mTextViewAnswer3.setText(toString(randomNum(50)));
                mTextViewAnswer4.setText(toString(randomNum(50)));
                break;
            case 3:
                mTextViewAnswer2.setText(toString(randomNum(50)));
                mTextViewAnswer1.setText(toString(randomNum(50)));
                mTextViewAnswer4.setText(toString(randomNum(50)));
                break;
            case 4:
                mTextViewAnswer2.setText(toString(randomNum(50)));
                mTextViewAnswer3.setText(toString(randomNum(50)));
                mTextViewAnswer1.setText(toString(randomNum(50)));
                break;
        }
    }

    private String toString(int i) {
        return Integer.toString(i);
    }

    private int randomNum(int max){

        return (int) (Math.random() * max + 1);
    }

    private void randomEquation(int left, int right){

        int num = mRandom.nextInt(3) +1;


        mEquation = "";
        mAnswer = 0;

        switch (num){
            case 1:
                mEquation = left + " + " + right;
                mAnswer = left + right;
                break;
            case 2:
                if (left < right){
                    int temp = left;
                    left = right;
                    right = temp;
                }
                mEquation = left + " - " + right;
                mAnswer = left - right;
                break;
            case 3:
                mEquation = left + " * " + right;
                mAnswer = left * right;
                break;
            case 4:
                if (left < right){
                    int temp = left;
                    left = right;
                    right = temp;
                }

                mEquation = left + "/" + right;
                mAnswer = left / right;
                break;
        }

    }


    @Override
    public void onClick(View view) {
        if (mTimerStarted) {
            switch (view.getId()) {
                case R.id.txvAnswer1:
                    if (mTextViewAnswer1.getText().toString().equals(Integer.toString(mAnswer))) {
                        mWins += 1;
                        mLosses += 1;
                        mTextViewStatus.setText("Correct!");
                    } else {
                        mLosses += 1;
                        mTextViewStatus.setText("Wrong!");
                    }
                    mTextViewRunningScore.setText(mWins + "/" + mLosses);
                    break;
                case R.id.txvAnsewr2:
                    if (mTextViewAnswer2.getText().toString().equals(Integer.toString(mAnswer))) {
                        mWins += 1;
                        mLosses += 1;
                        mTextViewStatus.setText("Correct!");
                    } else {
                        mLosses += 1;
                        mTextViewStatus.setText("Wrong!");
                    }
                    mTextViewRunningScore.setText(mWins + "/" + mLosses);
                    break;
                case R.id.txvAnswer3:
                    if (mTextViewAnswer3.getText().toString().equals(Integer.toString(mAnswer))) {
                        mWins += 1;
                        mLosses += 1;
                        mTextViewStatus.setText("Correct!");
                    } else {
                        mLosses += 1;
                        mTextViewStatus.setText("Wrong!");
                    }
                    mTextViewRunningScore.setText(mWins + "/" + mLosses);
                    break;
                case R.id.txvAnsewr4:
                    if (mTextViewAnswer4.getText().toString().equals(Integer.toString(mAnswer))) {
                        mWins += 1;
                        mLosses += 1;
                        mTextViewStatus.setText("Correct!");
                    } else {
                        mLosses += 1;
                        mTextViewStatus.setText("Wrong!");
                    }
                    mTextViewRunningScore.setText(mWins + "/" + mLosses);
                    break;
            }
            setAnswers();
        }
    }
}

package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {



    // TODO: Declare member variables here:
            Button mTrueButton, mFalseButton;
            TextView mQuestionTextView, mScoreTextView;
            ProgressBar mProgressBar;

            int mIndex;
            int mQuestion;
            //to track user progress, use variable mscore,
            int mScore;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here and make sure it's rounded up to nearest integer by using math.ceil(), use (int) to convert Math.ceil to int because it gives double

    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is where to checked saved app info prior to rotation
        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
        }else{
            mScore = 0;
            mIndex = 0;
        }

        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
        mScoreTextView = (TextView)findViewById(R.id.score);
        //using this code to make sure we update Score text view when screen is rotated and don't get an error message about null variable not assigned called mScoreTextView
        mScoreTextView.setText("Score " + mScore +"/"+mQuestionBank.length);


       mQuestion = mQuestionBank[mIndex].getQuestionID();

        mQuestionTextView.setText(mQuestion);

        //Create a Listener
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Quizzler", "Button Pressed");
                //Toast.makeText(getApplicationContext(),"True Pressed",Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                updateQuestion();
            }
        };

        mTrueButton.setOnClickListener(myListener);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To implement toast message
                //Toast myToast = Toast.makeText(getApplicationContext(),"False Pressed", Toast.LENGTH_SHORT);
                //Display toast to user
                //myToast.show();
                checkAnswer(false);
                updateQuestion();
            }
        });


    }

    private void updateQuestion(){
        mIndex = (mIndex + 1) % mQuestionBank.length;

        if(mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You scored " + mScore + " points");

            alert.setPositiveButton("Click to finish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //to close our application after click on click to finish
                    finish();
                }
            });
            //to display our alert on screen
            alert.show();
        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);

        //HOW  progress fills everytime user presses a button,
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);

        mScoreTextView.setText("Score " + mScore +"/"+mQuestionBank.length);
    }

    private void checkAnswer(boolean userSelection){
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();

        if(userSelection == correctAnswer){
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            mScore = mScore + 1;
        }else {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
    }
}

package com.londonappbrewery.quizzler;

/**
 * Created by user on 9/17/2017.
 */

public class TrueFalse {
    private int mQuestionID;
    private boolean mAnswer;

    //constructor (looks like a method but name looks like name of class
    public TrueFalse(int questionResourceID, boolean trueOrFalse){
        mQuestionID = questionResourceID;
        mAnswer = trueOrFalse;
    }
    //Right click here and click generate then getter/setter

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}

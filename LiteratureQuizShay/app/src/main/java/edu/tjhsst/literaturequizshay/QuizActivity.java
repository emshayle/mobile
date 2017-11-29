package edu.tjhsst.literaturequizshay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ValueEventListener;

//https://code.tutsplus.com/tutorials/firebase-for-android-file-storage--cms-27376

public class QuizActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;

    private Button mTRUE;
    private Button mFALSE;
    private Button mCheatButton;
    private Button mResultsButton;
    private TextView mQuestionTextView;
    private TextView mScoreView;
    private boolean mConsideredCheating;

    private Question[]mQuestionBank = new Question[]{
            new Question(R.string.question1,false),
            new Question(R.string.question2,true),
            new Question(R.string.question3,true),
            new Question(R.string.question4,true),
            new Question(R.string.question5,false),
            new Question(R.string.question6,false),
            new Question(R.string.question7,true),
            new Question(R.string.question8,false),
            new Question(R.string.question9,false),
            new Question(R.string.question10,true)
    };

    private int mCurrentIndex = 0;
    private int score=0;
    private boolean wasClicked=false;
    private int highscore;

    private void updateQuestion()
    {
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());
    }

    private void checkAnswer(boolean userPressed)
    {
        int messageRedId=0; boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        wasClicked = getIntent().getBooleanExtra("clicked",false);
        if (wasClicked) {
            score -= 50;
            wasClicked=false;
        }
        if (userPressed == answerIsTrue) {
            messageRedId = R.string.correct_toast;
            score = score + 100;
        }
        else {
            messageRedId = R.string.incorrect_toast;
            score = score - 100;
        }
        Toast.makeText(QuizActivity.this, messageRedId, Toast.LENGTH_SHORT).show();
        mScoreView.setText(R.string.score );
        mScoreView.append(" " + score);

    }

    private void closingResults()
    {
        mFALSE.setEnabled(false);
        mTRUE.setEnabled(false);
        mFALSE.setBackgroundColor(getResources().getColor(R.color.disabled));
        mTRUE.setBackgroundColor(getResources().getColor(R.color.disabled));
        mCheatButton.setVisibility(View.GONE);
        mResultsButton.setVisibility(View.VISIBLE);

        myRef.setValue(score);
        if (score > highscore)
            highscore=score;
            //ALREADY IN FIREBASE
        else
        {
            myRef.setValue(highscore);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle(R.string.quiz_title);

        highscore=-100;

        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("hs");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                highscore = dataSnapshot.getValue(Integer.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mTRUE=(Button)findViewById(R.id.TRUE);
        mTRUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                //mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                if (mCurrentIndex<mQuestionBank.length-1) {
                    mCurrentIndex += 1;
                    updateQuestion();
                }
                else {
                    closingResults();
                }
                //mReturnButton.setVisibility(View.GONE);
            }
        });

        mFALSE=(Button)findViewById(R.id.FALSE);
        mFALSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                if (mCurrentIndex<mQuestionBank.length-1) {
                    mCurrentIndex += 1;
                    updateQuestion();
                }
                else {
                    closingResults();
                }
            }
        });

        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConsideredCheating=true;
                final Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answer = mQuestionBank[mCurrentIndex].isAnswerTrue();
                i.putExtra("label", answer);
                startActivity(i);
            }
        });
        mConsideredCheating=false;
        mResultsButton=(Button)findViewById(R.id.results_button);
        mResultsButton.setVisibility(View.GONE);
        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                i.putExtra("cons", mConsideredCheating);
                i.putExtra("score", score);
                i.putExtra("hscore", highscore);

                startActivity(i);
                finish();
            }
        });

        mScoreView = (TextView)findViewById(R.id.score_view);
        if (savedInstanceState!=null)
            mCurrentIndex=savedInstanceState.getInt("KEY_INDEX", mCurrentIndex);
        mScoreView.setText(R.string.score );
        mScoreView.append(" 0");

        updateQuestion();
    }
}

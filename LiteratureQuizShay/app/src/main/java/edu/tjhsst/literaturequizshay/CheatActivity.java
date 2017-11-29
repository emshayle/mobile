package edu.tjhsst.literaturequizshay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private Button mCheatButton;
    private Button mBackButton;
    private Button mReturnButton;
    private TextView mAnswerTrue;
    private boolean correct_Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setTitle(R.string.cheat_title);

        correct_Answer = getIntent().getBooleanExtra("label", false);
        mAnswerTrue = (TextView)findViewById(R.id.answer_text_view);
        mCheatButton=(Button)findViewById(R.id.show_answer_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correct_Answer)
                    mAnswerTrue.setText(R.string.true_button);
                else
                    mAnswerTrue.setText(R.string.false_button);
                mBackButton.setEnabled(false);
                mBackButton.setBackgroundColor(getResources().getColor(R.color.disabled));
                mReturnButton.setVisibility(View.VISIBLE);
                mReturnButton.setEnabled(true);
            }
        });
        final Intent in = new Intent(CheatActivity.this, QuizActivity.class);

        mReturnButton=(Button)findViewById(R.id.return_button);
        mReturnButton.setEnabled(false);
        mReturnButton.setVisibility(View.INVISIBLE);
        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in.putExtra("clicked",true);
                //startActivity(in);
                finish();
            }
        });

        mBackButton=(Button)findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in.putExtra("clicked",false);
                //startActivity(in);
                finish();

            }
        });
    }
}

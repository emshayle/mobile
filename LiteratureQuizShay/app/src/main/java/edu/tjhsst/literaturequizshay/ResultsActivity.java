package edu.tjhsst.literaturequizshay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {

    private Button mEXIT;
    private Button mRESTART;
    private TextView mResultsHighscore;
    private TextView mResultsScore;
    private TextView mResultsOutput;
    private boolean mConsidered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setTitle(R.string.results_title);

        mConsidered = getIntent().getBooleanExtra("cons", false);
        int score = getIntent().getIntExtra("score", 0);
        int hscore = getIntent().getIntExtra("hscore", 0);

        mResultsHighscore = (TextView)findViewById(R.id.high_score);
        mResultsHighscore.setText(R.string.highScore);
        mResultsHighscore.append(" " + hscore);

        mResultsScore = (TextView)findViewById(R.id.results_score);
        mResultsOutput= (TextView)findViewById(R.id.results_output);

        mEXIT=(Button)findViewById(R.id.EXIT);
        mEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast goodbye = Toast.makeText(ResultsActivity.this, R.string.leaving_toast, Toast.LENGTH_SHORT);
                goodbye.setGravity(Gravity.CENTER, 0, 0);
                finish();
                goodbye.show();

            }
        });

        mRESTART=(Button)findViewById(R.id.AGAIN);
        mRESTART.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultsActivity.this, QuizActivity.class);
                i.putExtra("score", true);
                startActivity(i);
                finish();
            }
        });

        if(mConsidered){
            mResultsOutput.setText(R.string.consequenceBad);
            mResultsScore.setText(R.string.finalScore );
            mResultsScore.append("-9999");
            mRESTART.setEnabled(false);
            ((ViewGroup)mRESTART.getParent()).removeView(mRESTART);

        }
        else {
            mResultsOutput.setText(R.string.consequenceGood);
            mResultsScore.setText(R.string.finalScore);
            mResultsScore.append(" " + score);
        }


    }
}

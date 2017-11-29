package com.example.a2019kle.elephants;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mOneText;
    private TextView mTwoText;
    private EditText mEditText;
    private Button mEONE;
    private Button mETWO;
    private Button mSubs;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private int count;
    private Elephant e1;
    private Elephant e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.question);
        mOneText = (TextView) findViewById(R.id.oneData);
        mTwoText = (TextView) findViewById(R.id.twoData);
        mEditText = (EditText) findViewById(R.id.input);
        mEONE = (Button) findViewById(R.id.eleone);
        mETWO = (Button) findViewById(R.id.eletwo);
        mSubs = (Button) findViewById(R.id.submit);

        final Elephant[] e = new Elephant[3];
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("yourElephant");



        count = 1;
        mTextView.setText(R.string.q1);

        mSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditText.getText().length() != 0) {
                    if (count == 1) {
                        mTextView.setText(R.string.q2);
                        count = count + 1;
                        e1 = new Elephant("" + mEditText.getText());
                        myRef.child("oneEleph").setValue(e1);
                        mEONE.setText(e1.getName());
                        e[1] = e1;
                        mOneText.setText(e[1].toString());
                        mEONE.setEnabled(true);
                        mEditText.setText("");
                    } else if (count == 2) {
                        mTextView.setText(R.string.q3);
                        count = count + 1;
                        e2 = new Elephant("" + mEditText.getText());
                        myRef.child("twoElph").setValue(e2);
                        mEditText.setEnabled(false);
                        mETWO.setText(e2.getName());
                        e[2] = e2;
                        mTwoText.setText(e[2].toString());
                        mETWO.setEnabled(true);
                        mEditText.setText(R.string.disabled);
                        mSubs.setText(R.string.reset);
                    } else {
                        mTextView.setText(R.string.q1);
                        count = 1;
                        mEONE.setEnabled(false);
                        mETWO.setEnabled(false);
                        mEditText.setEnabled(true);
                        mSubs.setText(R.string.submit);
                        mEditText.setText("");
                    }
                }
            }
        });

        mEONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                e[1].evolve();
                mOneText.setText(e[1].toString());
                myRef.child("oneEleph").setValue(e1);

            }
        });

        mETWO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                e[2].evolve();
                mTwoText.setText(e[2].toString());
                myRef.child("twoElph").setValue(e2);

            }
        });
    }
}

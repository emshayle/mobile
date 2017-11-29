package com.example.a2019kle.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private TextView mTextView;
    private EditText mEditText;
    private Button mButton;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.view);
        mEditText = (EditText) findViewById(R.id.edit);
        mButton = (Button) findViewById(R.id.butt);

        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("message");

       // myRef.setValue("Hello");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.setValue("" + mEditText.getText()); //data in
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //called once on initial & whenever updated

                value = dataSnapshot.getValue(String.class);
                mTextView.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

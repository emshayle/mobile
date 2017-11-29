package edu.tjhsst.myfirstapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mButton1;
    private EditText name;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText) findViewById(R.id.name_field);
        result=(TextView) findViewById(R.id.message);

        mButton1=(Button)findViewById(R.id.button1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int random = (int)(Math.random()*10)+1;
                if (random%2==0)
                    result.setText("Happy Wednesday " + name.getText());
                else
                    result.setText("UNhappy Wednesday " + name.getText());
            }
        });
    }
}

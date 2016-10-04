package com.example.himanshu.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {
TextView textView_statsResult;
    String str;
    float temp;
    float result;
    int str1;
    ProgressBar progressBar_statsResult;
    Button button_tryAgain, button_quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        textView_statsResult=(TextView)findViewById(R.id.textView_statsResult);
        progressBar_statsResult=(ProgressBar)findViewById(R.id.progressBar);
        progressBar_statsResult.setMax(100);
        if (getIntent().getExtras() != null) {
            str1 =  getIntent().getExtras().getInt(TriviaActivity.ANSWERS);
            Log.d("demo","Total number of correct answers ----->"+str1);
        }



        result= (float) ((str1*100.0)/16);
        textView_statsResult.setText(String.valueOf(result));
        progressBar_statsResult.setProgress((int)result);

        button_quit=(Button)findViewById(R.id.button_quit);
        button_tryAgain=(Button)findViewById(R.id.button_tryAgain);

        button_tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StatsActivity.this,TriviaActivity.class);
                startActivity(i);
            }
        });

        button_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        });


        }
}

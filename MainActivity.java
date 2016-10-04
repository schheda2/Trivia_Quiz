package com.example.himanshu.trivia;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements GetQuestionsAsyncTask.IData{

    TextView textView_loading;
    ImageView main_image;
    MainActivity context;
    Button button_exit, button_start;

    public   ArrayList<Questions> OriginalQuestionsList = new ArrayList<>();
    public static final String QUESTIONS_LIST="questions_list";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //textView_loading=(TextView)findViewById(R.id.textView_loading);

        //main_image.setImageDrawable();
        setContentView(R.layout.activity_main);
        main_image=(ImageView)findViewById(R.id.imageView_trivia);
        new GetQuestionsAsyncTask(this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
        button_start=(Button)findViewById(R.id.button_start);
        button_exit=(Button)findViewById(R.id.button_exit);
        button_start.setEnabled(false);
   //     progressLoading();

        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

button_start.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, TriviaActivity.class);
        intent.putParcelableArrayListExtra(QUESTIONS_LIST,OriginalQuestionsList);
        startActivity(intent);


    }
});


    }



    @Override
    public void setUpData(ArrayList<Questions> questions) {

        findViewById(R.id.imgProgress).setVisibility(View.GONE);
        Picasso.with(MainActivity.this).load(R.drawable.trivia).into(main_image);
        button_start.setEnabled(true);
        OriginalQuestionsList = questions;
    }

}

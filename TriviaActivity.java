package com.example.himanshu.trivia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity implements GetImagesAsyncTask.ITriviaImages {
TextView textView_questionNumber, textView_seconds, textView_question;
    Button button_quit, button_next;
RadioGroup radioGroup_choices;
ImageView imageView_questionImage;

    int count=0;
    Bitmap bitmap;
    Uri uri_final;
    int trueAnswers=0;
    public static ArrayList<Questions> OriginalQuestionsList = new ArrayList<>();
    public static final String ANSWERS="true_answers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        if (getIntent().getExtras() != null) {
            OriginalQuestionsList = getIntent().getParcelableArrayListExtra(MainActivity.QUESTIONS_LIST);
        }


        textView_questionNumber = (TextView)findViewById(R.id.textView_questionNumber);
        textView_seconds=(TextView)findViewById(R.id.textView_seconds);
        textView_question=(TextView)findViewById(R.id.textView_question);
        radioGroup_choices=(RadioGroup)findViewById(R.id.radioGroup_choices);
        imageView_questionImage=(ImageView)findViewById(R.id.imageView_questionImage);
        button_next=(Button)findViewById(R.id.button_next);
        button_quit=(Button)findViewById(R.id.button_quit);

        textView_questionNumber.setText("Q"+String.valueOf(OriginalQuestionsList.get(count).getId()+1));
        textView_question.setText(OriginalQuestionsList.get(count).getQuestion());
        textView_seconds=(TextView)findViewById(R.id.textView_seconds);
        uri_final = Uri.parse(OriginalQuestionsList.get(count).getImage());
        new GetImagesAsyncTask(this).execute(String.valueOf(uri_final));
        addRadioButtons(OriginalQuestionsList.get(count).getChoiceArrayList().size());
        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView_seconds.setText("Time left: "+millisUntilFinished / 1000 + " seconds");
            }

            public void onFinish() {
                Intent i = new Intent(TriviaActivity.this, StatsActivity.class);
                i.putExtra(ANSWERS,trueAnswers);
                //i.putExtra("questions", 16);
                startActivity(i);
            }
        }.start();
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count < OriginalQuestionsList.size()) {

                    if((radioGroup_choices.getCheckedRadioButtonId()+1)==OriginalQuestionsList.get(count-1).getAnswer()){
                        trueAnswers++;
                        Log.d("demo","ANSWER IS"+trueAnswers);
                    }
                    imageView_questionImage.setImageResource(0);
                    findViewById(R.id.imgProgress).setVisibility(View.VISIBLE);

                    textView_questionNumber.setText("Q" + String.valueOf(OriginalQuestionsList.get(count).getId()+1));
                    textView_question.setText(OriginalQuestionsList.get(count).getQuestion());

                    addRadioButtons(OriginalQuestionsList.get(count).getChoiceArrayList().size());

                    try {
                        uri_final = Uri.parse(OriginalQuestionsList.get(count).getImage());
                        new GetImagesAsyncTask(TriviaActivity.this).execute(String.valueOf(uri_final));
                    }catch (Exception e){
                        findViewById(R.id.imgProgress).setVisibility(View.GONE);
                    }


                }
                else{
                    Intent i = new Intent(TriviaActivity.this,StatsActivity.class);
                    i.putExtra(ANSWERS,trueAnswers);
                    startActivity(i);

                }
            }
        });
//}
        findViewById(R.id.button_quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(TriviaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });





    }

    @Override
    public void setUpImages(Bitmap bitmap) {

          try {
              findViewById(R.id.imgProgress).setVisibility(View.GONE);
            imageView_questionImage.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }




    public void addRadioButtons(int i) {


        radioGroup_choices.clearCheck();
        radioGroup_choices.removeAllViews();

            RadioGroup ll = new RadioGroup(this);

            RadioGroup l1 = new RadioGroup(this);
            RadioButton rdbtn;


            for (int j = 0; j < i; j++) {

                rdbtn = new RadioButton(this);
                if (j==0){
                    rdbtn.setChecked(true);
                }

                rdbtn.setId(j);
                rdbtn.setText("" + OriginalQuestionsList.get(count).getChoiceArrayList().get(j));
               radioGroup_choices.addView(rdbtn);

                }





        }



}

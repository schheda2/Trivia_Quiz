package com.example.himanshu.trivia;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Himanshu on 9/24/2016.
 */
public class QuestionsUtil {
    static JSONObject root;
    static Questions question;
    static JSONObject choicesJSONObject;

    static public class QuestionsJSONParser {
        static ArrayList<Questions> parseQuestions(String in) throws JSONException {
            ArrayList<Questions> questionsList = new ArrayList<Questions>();
            root = new JSONObject(in);
            JSONArray questionsJSONArray = root.getJSONArray("questions");
            for (int i = 0; i < questionsJSONArray.length(); i++)
            {
                JSONObject questionsJSONObject = questionsJSONArray.getJSONObject(i);
                question = new Questions();
                Log.d("demo", "contacting questions class");
                question.setQuestion(questionsJSONObject.getString("text"));
                question.setId(questionsJSONObject.getInt("id"));
                    try {

                        question.setImage(questionsJSONObject.getString("image"));
                    }
                    catch(Exception e)
                    {

                    }


                choicesJSONObject = questionsJSONObject.getJSONObject("choices");
                JSONArray choicesJSONArray = choicesJSONObject.getJSONArray("choice");
                question.setChoiceArray(choicesJSONObject.getJSONArray("choice"));
                question.setAnswer(choicesJSONObject.getInt("answer"));
                Log.d("demo", "In choice" + question.getChoiceArray());
                Log.d("demo", "" + question.getAnswer());
                questionsList.add(question);
                Log.d("demo","The length of the arrayList is "+questionsList.size());
            }
            return questionsList;

        }


    }
}

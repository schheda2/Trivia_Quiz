package com.example.himanshu.trivia;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Himanshu on 9/24/2016.
 */
public class GetQuestionsAsyncTask extends AsyncTask<String, Void, ArrayList<Questions>> {
IData activity;

    public GetQuestionsAsyncTask(IData activity) {
        this.activity = activity;
    }

    @Override

    protected ArrayList doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status_code = con.getResponseCode();
            if (status_code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }

                return QuestionsUtil.QuestionsJSONParser.parseQuestions(sb.toString());
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Questions> questions) {
        int i=0;
        activity.setUpData(questions);
        super.onPostExecute(questions);

        for (Questions q: questions) {
           // Log.d("demo",q.toString());
            Log.d("demo","ANSWER IS:"+questions.get(i).getAnswer());
            i++;

        }


    }

    public static interface IData
    {
        public void setUpData(ArrayList<Questions> questions);
    }



}
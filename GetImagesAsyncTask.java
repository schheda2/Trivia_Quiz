package com.example.himanshu.trivia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Himanshu on 9/25/2016.
 */
public class GetImagesAsyncTask extends AsyncTask<String, Void, Bitmap>  {
ITriviaImages trivia_images;
//TriviaActivity triviaActivity;
    public GetImagesAsyncTask(ITriviaImages trivia_images) {
        this.trivia_images = trivia_images;
    }



    @Override
    protected Bitmap doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);



            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setRequestMethod("GET");
            con.connect();
            int status_code = con.getResponseCode();

            if (status_code == HttpURLConnection.HTTP_OK) {

                int response = con.getResponseCode();
                InputStream in= con.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(in);


                return bitmap;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {


        try {
            trivia_images.setUpImages(bitmap);
        }
        catch(Exception e)
        {
Log.d("demo",e.toString());
        }
        super.onPostExecute(bitmap);


    }

    public static interface ITriviaImages
    {
        public void setUpImages(Bitmap bitmap);
    }
}
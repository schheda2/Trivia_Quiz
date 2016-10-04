package com.example.himanshu.trivia;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by Himanshu on 9/24/2016.
 */
public class Questions implements Parcelable{
    int id,answer;
    String question, image;
    JSONArray choiceArray;
    ArrayList<String> choiceArrayList = new ArrayList<>();
    public Questions() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public JSONArray getChoiceArray() {

        if(choiceArray!=null) {
            for (int i = 0; i < choiceArray.length(); i++) {
                try {
                    choiceArrayList.add(choiceArray.get(i).toString());
                    Log.d("demo","array list ko send kar bulleya");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
            return choiceArray;

    }

    public ArrayList<String> getChoiceArrayList() {
        Log.d("demo","Array list aa gaya bulleya");
        return choiceArrayList;
    }

    public void setChoiceArray(JSONArray choiceArray) {
        this.choiceArray = choiceArray;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                //", choices=" + choices +
                ", id=" + id +
                '}';
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(image);
       dest.writeInt(answer);
        dest.writeStringList(choiceArrayList);

    }

    public Questions(Parcel in)
    {
this.id=in.readInt();
        this.question=in.readString();
        this.image=in.readString();
        this.answer=in.readInt();
        this.choiceArrayList=in.createStringArrayList();
    }

}

package com.adamson.miles.climbingtrainer;

import android.os.Debug;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainingDay {

    public String type = "ERROR ERROR"; // Type should be overwritten by the actual type
    public String dateString;
    public Date date;
    public String grade;
    public String commitment;
    public Exercise[] exercises;
    public int exerciseCount;
    public String dayOfWeek;
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat format_EEEE = new SimpleDateFormat("EEEE");

    public TrainingDay(Date d, String g, String c){
        date = d;
        dateString = format.format(date);
        dayOfWeek = format_EEEE.format(date);
        grade = g;
        commitment = c;
    }

    // Sets the exercises array with another exercise array, which might have nulls
    // at the end
    public void setExercises(Exercise[] e){
        for(int i = 0; i < e.length; i++){
            if(e[i]!=null){
                exerciseCount++;
            }
        }
        exercises = new Exercise[exerciseCount];
        for(int i = 0; i < exerciseCount; i++){
            exercises[i] = e[i];
        }
    }

}

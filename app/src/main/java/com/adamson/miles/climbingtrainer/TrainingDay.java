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
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public TrainingDay(Date d, String g, String c){
        date = d;
        dateString = format.format(date);
        grade = g;
        commitment = c;
    }

    public TrainingDay(String d, String g, String c){
        dateString = d;
        try{
            date = format.parse(d);
        } catch(ParseException e){
            // TODO: catch something
        }
        grade = g;
        commitment = c;
    }

}

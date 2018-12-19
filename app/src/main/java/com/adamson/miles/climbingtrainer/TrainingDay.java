package com.adamson.miles.climbingtrainer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainingDay {

    public String type = "ERROR ERROR"; // Type should be overwritten by the actual type
    public String dateString;
    public Date date;
    public String grade;
    public String commitment;
    public Exercise[] exercises;

    public TrainingDay(Date d, String g, String c){
        date = d;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        dateString = format.format(date);
        grade = g;
        commitment = c;
    }

}

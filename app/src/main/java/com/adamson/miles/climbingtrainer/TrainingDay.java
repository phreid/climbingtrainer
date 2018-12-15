package com.adamson.miles.climbingtrainer;

import java.util.Date;

public class TrainingDay {

    public String type = "ERROR ERROR"; // Type should be overwritten by the actual type
    public Date date;
    public String grade;
    public String commitment;
    public Exercise[] exercises;

    public TrainingDay(Date d, String g, String c){
        date = d;
        grade = g;
        commitment = c;
    }

}

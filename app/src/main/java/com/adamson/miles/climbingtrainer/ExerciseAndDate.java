package com.adamson.miles.climbingtrainer;

public class ExerciseAndDate {

    public String[] dates;
    public String[] exerciseNames;
    public Exercise[] exercises;

    ExerciseAndDate(String[] d, String[] e, Exercise[] ex){
        dates = d;
        exerciseNames = e;
        exercises = ex;
    }
}

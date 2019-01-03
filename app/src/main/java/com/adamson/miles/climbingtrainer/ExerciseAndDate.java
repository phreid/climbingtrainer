package com.adamson.miles.climbingtrainer;

// This is the class which contains a programs info being TAKEN FROM the database
public class ExerciseAndDate {

    public String[] dateStrings;
    public String[] exerciseNames;
    public Exercise[] exercises;
    public String[] uniqueDates;
    public String[] uniqueDatesDayOfWeek;
    public String[] uniqueDatesType;
    public String[] daysOfWeek;
    public String[] types;
    public int uniqueDatesCount;

    ExerciseAndDate(String[] d, String[] e, String[] w, String[] t, Exercise[] ex){
        dateStrings = d;
        exerciseNames = e;
        types = t;
        daysOfWeek = w;
        exercises = ex;
        groupDates();
    }

    void groupDates(){

        String[] uniqueDatesPadded = new String[dateStrings.length];
        String[] uniqueDatesDayOfWeekPadded = new String[dateStrings.length];
        String[] uniqueDatesTypePadded = new String[dateStrings.length];

        String lastDate = dateStrings[0];
        uniqueDatesPadded[0] = dateStrings[0];

        // Every time the date changes, add it to uniqueDatesPadded
        for(int i = 1; i < dateStrings.length; i++){
            // If the date is different from the last date, add it to uniqueDatesPadded
            if(!dateStrings[i].equals(dateStrings[i - 1])){
                uniqueDatesPadded[i] = dateStrings[i];
                uniqueDatesDayOfWeekPadded[i] = daysOfWeek[i];
                uniqueDatesTypePadded[i] = types[i];
            }
        }

        // Find how many elements of uniqueDatesPadded are not null
        int count = 0;
        for(int i = 0; i < uniqueDatesPadded.length; i++){
            if(uniqueDatesPadded[i] != null){
                count++;
            }
        }
        uniqueDatesCount = count;
        uniqueDates = new String[uniqueDatesCount];
        uniqueDatesDayOfWeek = new String[uniqueDatesCount];
        uniqueDatesType = new String[uniqueDatesCount];
        // Remove the null spots at the end to create the uniqueDates array
        int k = 0;
        for(int i = 0; i < uniqueDatesPadded.length; i++){
            if(uniqueDatesPadded[i] != null) {
                uniqueDates[k] = uniqueDatesPadded[i];
                uniqueDatesDayOfWeek[k] = uniqueDatesDayOfWeekPadded[i];
                uniqueDatesType[k] = uniqueDatesTypePadded[i];
                k++;
            }
        }

    }
}

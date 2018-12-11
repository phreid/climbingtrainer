package com.adamson.miles.climbingtrainer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by miles on 2018-12-02.
 */

public class ProgramBuilder {
    private static ProgramBuilder ourInstance = new ProgramBuilder();

    private String programType;
    private boolean[] daysOfWeek;
    private String commitmentLevel;
    private String currentGrade;
    private String startDateString;
    private Date startDate;
    private String endDateString;
    private Date endDate;
    private boolean[] equipmentAvailable = new boolean[12];
    private static final int minimumSegment = 7;
    private static final int minimumBoulder = 28;
    private static final int minimumRoutes = 42;
    private Date[] trainingDatesInProgram;
    private long programLength;

    public static ProgramBuilder getInstance() {
        if(null == ourInstance){
            ourInstance = new ProgramBuilder();
        }
        return ourInstance;
    }

    private ProgramBuilder() {}

    public void setProgramType(String s){programType = s;}
    public void setCommitmentLevel(String s){commitmentLevel = s;}
    public void setCurrentGrade(String s){currentGrade = s;}
    public void setEquipmentAvailable(int i){equipmentAvailable[i] = true;};
    public void setStartDateString(String date){ startDateString = date;}
    public void setEndDateString(String date){ endDateString = date;}
    public void setDaysOfWeek(boolean[] b){daysOfWeek = b;}

    public String getProgramType(){return programType;}
    public String getCommitmentLevel(){return commitmentLevel;}
    public String getCurrentGrade(){return currentGrade;}
    public boolean[] getEquipmentAvailable(){return equipmentAvailable;}
    public String getStartDateString(){return startDateString;}
    public String getEndDateString(){return endDateString;}
    public boolean[] getDaysOfWeek(){return daysOfWeek;}
    public Date[] getTrainingDatesInProgram(){return trainingDatesInProgram;}
    public long getProgramLength(){return programLength;}

    // Returns false if the dates fail the minimum length requirements.
    public boolean checkDates(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = format.parse(startDateString);
            endDate = format.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long msDiff = endDate.getTime() - startDate.getTime();
        programLength = TimeUnit.MILLISECONDS.toDays(msDiff);

        // Program can't have zero or negative length
        if(programLength <=0){
            return false;
        }

        // Route and boulder programs have minimum lengths.
        // Segments must be 1 week long.
        boolean result = false;
        switch (programType){
            case "Bouldering Program":
                if(programLength >= minimumBoulder){
                    result = true;
                }
                break;

            case "Routes Program":
                if(programLength >= minimumRoutes){
                    result = true;
                }
                break;

            default:
                if(programLength >= minimumSegment){
                    result = true;
                }
                break;
        }
        return result;
    }

    // Fills the trainingDatesInProgram array with Date objects
    // if that day is a selected training day. If it's not a selected
    // training day, that element will be null.
    public void buildDatesInProgram(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        trainingDatesInProgram = new Date[(int)programLength];
        // step through every day of the program. If it matches the day the user
        // climbs on, add it to dates array
        for(int i = 0; i < programLength; i++){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(startDate.getTime() + (long)i * ((long)1000 * (long)60 * (long)60 * (long)24));
            String dateString = formatter.format(calendar.getTime());
            Date date;
            try {
                date = formatter.parse(dateString);
                // if that day is a day of the week the user wants to train, create a date for it
                if(daysOfWeek[date.getDay()]){
                    trainingDatesInProgram[i] = date;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

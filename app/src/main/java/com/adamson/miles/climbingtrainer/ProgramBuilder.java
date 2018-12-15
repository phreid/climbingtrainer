package com.adamson.miles.climbingtrainer;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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
    private TrainingDay[] trainingDays;

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
    public void setEquipmentAvailable(int i, boolean b){equipmentAvailable[i] = b;};
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
    public TrainingDay[] getTrainingDays(){return trainingDays;}

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

    boolean checkEquipment(Context context, String equipment){
        String[] allEquipment = context.getResources().getStringArray(R.array.equipment);
        for (int i = 0; i < allEquipment.length; i++){
            // Check if this is the peice of equipment in question
            if(equipment.equals(allEquipment[i])){
                // Check if the user selected that they have the equipment
                if(equipmentAvailable[i]){
                    return true;
                }
            }
        }
        return false;
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

    public void buildTrainingDays(){
        // First, decide how many days of each segment the users program will have.
        // A bouldering program has 1 week of volume at the start. A route program has 2.
        int volumeDays = 0;
        int strPowDays = 0;
        int powerEndDays = 0;
        int enduranceDays = 0;

        switch (programType){
            case "Bouldering Program":
                volumeDays = 7;
                int boulderSegmentLength = ((int)programLength - volumeDays) / 2;
                strPowDays = boulderSegmentLength;
                powerEndDays = boulderSegmentLength;

                // If the number of days to split up was a decimal,
                // add one to powerEndDays to make up for the truncated day
                while(programLength - volumeDays - strPowDays - powerEndDays > 0){
                    powerEndDays++;
                }
                break;

            case "Routes Program":
                volumeDays = 14;
                int routeSegmentLength = ((int)programLength - volumeDays) / 3;
                strPowDays = routeSegmentLength;
                powerEndDays = routeSegmentLength;
                enduranceDays = routeSegmentLength;

                // If the number of days to split up created a decimal,
                // add one or two to enduranceDays to make up for the truncated days
                while(programLength - volumeDays - strPowDays - powerEndDays - enduranceDays > 0){
                    enduranceDays++;
                }

                break;

            case "Volume Only":
                volumeDays = (int)programLength;
                break;

            case "Strength and Power Only":
                strPowDays = (int)programLength;
                break;

            case "Power Endurance Only":
                powerEndDays = (int)programLength;
                break;

            case "Route Endurance Only":
                enduranceDays = (int)programLength;
                break;
        }

        boolean lastWasPower = false;

        // For each non-null day in the dates array,
        // create a corresponding training day in its array
        trainingDays = new TrainingDay[(int)programLength];
        for(int i = 0; i < programLength; i++){
            if(trainingDatesInProgram[i] != null){
                trainingDays[i] = new TrainingDay(trainingDatesInProgram[i], currentGrade, commitmentLevel);
           }
            // Step through days and assign a type based on the numbers found above.
            // A "null" day is a rest day which doesn't have a type.
            if(trainingDays[i] != null) {
                if (i < volumeDays) {
                    trainingDays[i].type = ExerciseBuilder.types[ExerciseBuilder.VOLUME];
                } else if (i <= strPowDays + volumeDays) {
                    // Strength and power alternate.
                    if (lastWasPower) {
                        trainingDays[i].type = ExerciseBuilder.types[ExerciseBuilder.STRENGTH];
                        lastWasPower = false;
                    } else {
                        trainingDays[i].type = ExerciseBuilder.types[ExerciseBuilder.POWER];
                        lastWasPower = true;
                    }
                } else if (i <= powerEndDays + volumeDays + strPowDays) {
                    trainingDays[i].type = ExerciseBuilder.types[ExerciseBuilder.POWEND];
                } else if (i <= enduranceDays + powerEndDays + volumeDays + strPowDays) {
                    trainingDays[i].type = ExerciseBuilder.types[ExerciseBuilder.ENDURANCE];
                }
            }
        }
    }


    // To be called after buildTrainingDays, this method populates all the training day
    // objects with exercises based on the user, equipment, and training day type
    public TrainingDay[] populateTrainingDays(Context context){
        Exercise[] exercises = new Exercise[10]; // No day will have more than 10 exercises.
        DatabaseHelper db = new DatabaseHelper(context);
        for (int dayIndex = 0; dayIndex <trainingDays.length; dayIndex++){

            // Every day of every program begins with a warm up
            trainingDays[dayIndex].exercises[0] = ExerciseBuilder.warmUp;

            switch (trainingDays[dayIndex].type){
                case "Volume":
                    Exercise[] volumeExercises = db.selectAllExerciseByType("Volume");
                    boolean foundExercise = false;
                    // Try to find an exercise where the user has the equipment needed
                    while (!foundExercise) {
                        int randomIndex = new Random().nextInt(volumeExercises.length);
                        if(checkEquipment(context, volumeExercises[randomIndex].equip)) {
                            foundExercise = true;
                            trainingDays[dayIndex].exercises[1] = volumeExercises[randomIndex];
                        }
                    }
                    // All volume days end with free time.
                    trainingDays[dayIndex].exercises[2] = ExerciseBuilder.freeTime;
                    break;

                case "Strength":

                    break;

                case "Power":


                    break;

                case "Power Endurance":

                    break;

                case "Endurance":

                    break;
            }



        }


        return trainingDays;
    }
}

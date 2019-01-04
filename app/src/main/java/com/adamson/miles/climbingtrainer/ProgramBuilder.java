package com.adamson.miles.climbingtrainer;

import android.content.Context;
import android.widget.Toast;

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

    public void destroyInstance(){
        ourInstance = null;
    }

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
                volumeDays = 5;
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
                volumeDays = 10;
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
    public void populateTrainingDays(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        String[] commitments = context.getResources().getStringArray(R.array.commitment_levels);
        String DEDICATED = commitments[2];

        // loop through each day
        for (int dayIndex = 0; dayIndex <trainingDays.length; dayIndex++){

            // Check if this day is a training day to be built. Rest days are null
            if(trainingDatesInProgram[dayIndex] != null) {

                // No day will have more than 10 exercises
                trainingDays[dayIndex].exercises = new Exercise[10];

                // Every day of every program begins with a warm up
                trainingDays[dayIndex].exercises[0] = ExerciseBuilder.warmUp;

                switch (trainingDays[dayIndex].type) {
                    case "Volume":
                        Exercise[] volumeExercises = filterExerciseArray(db.selectAllExerciseByType("Volume"));
                        int randomIndex = new Random().nextInt(volumeExercises.length);
                        trainingDays[dayIndex].exercises[1] = volumeExercises[randomIndex];
                        // If dedicated, add a second volume exercise, which is NOT already the one
                        // selected as the first exercise.
                        if(commitmentLevel.equals(DEDICATED)){
                            int randomIndexTwo = randomIntNew(volumeExercises.length, new int[]{randomIndex});
                            trainingDays[dayIndex].exercises[2] = volumeExercises[randomIndexTwo];
                        } else {
                            trainingDays[dayIndex].exercises[2] = ExerciseBuilder.freeTime;
                        }
                        break;

                    case "Strength":
                        trainingDays[dayIndex].exercises[1] = ExerciseBuilder.freeTime;
                        trainingDays[dayIndex].exercises[2] = ExerciseBuilder.freeTime;
                        trainingDays[dayIndex].exercises[3] = ExerciseBuilder.freeTime;
                        break;

                    case "Power":
                        trainingDays[dayIndex].exercises[1] = ExerciseBuilder.freeTime;
                        trainingDays[dayIndex].exercises[2] = ExerciseBuilder.freeTime;
                        trainingDays[dayIndex].exercises[3] = ExerciseBuilder.freeTime;

                        break;

                    case "Power Endurance":
                        trainingDays[dayIndex].exercises[1] = ExerciseBuilder.freeTime;
                        trainingDays[dayIndex].exercises[2] = ExerciseBuilder.freeTime;
                        trainingDays[dayIndex].exercises[3] = ExerciseBuilder.freeTime;
                        break;

                    case "Endurance":
                        trainingDays[dayIndex].exercises[1] = ExerciseBuilder.freeTime;
                        trainingDays[dayIndex].exercises[2] = ExerciseBuilder.freeTime;
                        trainingDays[dayIndex].exercises[3] = ExerciseBuilder.freeTime;
                        break;
                }
            }
        }
    }

    // Takes an exercise array and returns another exercise array where every
    // exercise where the user doesn't have the equipment is removed.
    Exercise[] filterExerciseArray(Exercise[] e){
        boolean[] valid = new boolean[e.length];
        int count = 0;
        for(int i = 0; i < e.length; i++){
            if(checkExerciseEquipment(e[i])){
                valid[i] = true;
                count++;
            } else {
                valid[i] = false;
            }
        }

        Exercise[] validExercises = new Exercise[count];
        count = 0;
        for(int i = 0; i < e.length; i++){
            if(valid[i]){
                validExercises[count] = e[i];
                count++;
            }
        }
        return validExercises;
    }

    // Returns true if the user has selected they have the equipment necessary
    // for an exercise.
    Boolean checkExerciseEquipment(Exercise e){
        String[] equipment = ExerciseBuilder.equip;
        // exercises which require no equipment always return true
        if(e.equip.equals(ExerciseBuilder.none)) {
            return true;
        }
        // loop through and see if we have the equipment available
        for (int i = 0; i < equipment.length; i++) {
            if ((equipment[i].equals(e.equip)) && equipmentAvailable[i]) {
                return true;
            }
        }
        return false;
    }

    // returns a random integer from [0:max-1]
    int randomInt(int maxExclusive){
        return new Random().nextInt(maxExclusive);
    }

    // returns a random integer that is NOT in the "old" array
    // attempts 100 times to do so and returns 0 if it fails
    int randomIntNew(int maxExclusive, int[] old){
        boolean foundNew = false;
        // If the int already exists in the array, this flag is set.
        boolean existsFlag = false;
        int r;
        for(int attempts = 0; attempts < 100; attempts++) {
            r = randomInt(maxExclusive);

            // Step through each existing number and see if this is new
            for (int i = 0; i < old.length; i++) {
                if (r == old[i]){
                    existsFlag = true;
                }
            }

            // If the number is new, return it. Otherwise, tries again
            if(!existsFlag){
                return r;
            }
        }
        // returns zero if after 100 tries no new int can be found
        return 0;
    }

}

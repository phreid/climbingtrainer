package com.adamson.miles.climbingtrainer;

import android.content.Context;
import android.provider.ContactsContract;
import android.widget.TextView;

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
    private int volumeDays = 0;
    private int strPowDays = 0;
    private int powerEndDays = 0;
    private int enduranceDays = 0;
    private Date[] trainingDatesInProgram;
    private long programLength;
    private Random random;
    private String programName;
    private String DEDICATED = "Dedicated";
    private boolean lastWasPower = false;
    private int i = 0;
    private int attempts = 0;
    private boolean done = false;

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
    public void setProgramName(String a){programName = a;}


    public String getProgramType(){return programType;}
    public String getCommitmentLevel(){return commitmentLevel;}
    public String getCurrentGrade(){return currentGrade;}
    public boolean[] getEquipmentAvailable(){return equipmentAvailable;}
    public String getStartDateString(){return startDateString;}
    public String getEndDateString(){return endDateString;}
    public boolean[] getDaysOfWeek(){return daysOfWeek;}
    public Date[] getTrainingDatesInProgram(){return trainingDatesInProgram;}
    public long getProgramLength(){return programLength;}
    public boolean isDone() { return done; }

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
    public void buildDatesInProgram(Context context){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        trainingDatesInProgram = new Date[(int)programLength];
        attempts = 0;
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
        DatabaseHelper db = new DatabaseHelper(context);
        db.createProgram(programName);
        // First, decide how many days of each segment the users program will have.
        // A bouldering program has 1 week of volume at the start. A route program has 2.
        volumeDays = 0;
        strPowDays = 0;
        powerEndDays = 0;
        enduranceDays = 0;

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

    }

    // Call this to insert one training day. Call until isDone() is true, that's when program
    // is finished being built.
    public int buildTrainingDays(final Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        // For each non-null day in the dates array, create a corresponding training day
        // Rest days are null, skip them
        if(trainingDatesInProgram[i] != null){
            TrainingDay trainingDay = new TrainingDay(trainingDatesInProgram[i], currentGrade, commitmentLevel);
            // Set trainingDay Type
            if (i < volumeDays) {
                trainingDay.type = ExerciseBuilder.types[ExerciseBuilder.VOLUME];
            } else if (i <= strPowDays + volumeDays) {
                // Strength and power alternate.
                if (lastWasPower) {
                    trainingDay.type = ExerciseBuilder.types[ExerciseBuilder.STRENGTH];
                    lastWasPower = false;
                } else {
                    trainingDay.type = ExerciseBuilder.types[ExerciseBuilder.POWER];
                    lastWasPower = true;
                }
            } else if (i <= powerEndDays + volumeDays + strPowDays) {
                trainingDay.type = ExerciseBuilder.types[ExerciseBuilder.POWEND];
            } else if (i <= enduranceDays + powerEndDays + volumeDays + strPowDays) {
                trainingDay.type = ExerciseBuilder.types[ExerciseBuilder.ENDURANCE];
            }

            // No day will have more than 10 exercises
            Exercise[] e = new Exercise[10];

            // Every day of every program begins with a warm up
            e[0] = ExerciseBuilder.warmUp;

            switch (trainingDay.type) {
                case "Volume":
                    Exercise[] volumeExercises = filterByEquipment(db.selectAllExerciseByType("Volume"));
                    e[1] = randomFrom(volumeExercises);
                    // If dedicated, add a second volume exercise, which is NOT the same
                    if(commitmentLevel.equals(DEDICATED)){
                        do{
                            e[2] = randomFrom(volumeExercises);
                        } while (!uniqueExercises(e[1], e[2], null) && !toManyTries());
                    }
                    break;

                case "Strength":
                    Exercise[] strengthExercises = filterByGrade(filterByEquipment(db.selectAllExerciseByType("Strength")), currentGrade);
                    Exercise[] longExercises = filterMinimumTime(strengthExercises, 30);
                    Exercise[] fifteenMinExercises = filterByExactTime(strengthExercises, 15);
                    Exercise[] thirtyMinExercises =  filterByExactTime(strengthExercises, 30);
                    Exercise[] fifteenMinConditioning = filterByEquipment(db.selectAllExerciseByType("Conditioning"));

                    // If dedicated, give two long exercises. Fill in the rest with 15 minute
                    // drills until the session is three hours.
                    if(commitmentLevel.equals(DEDICATED)){
                        // Add two long exercises. If they are the exact same, try again
                        e[1] = randomFrom(longExercises);
                        do {
                            e[2] = randomFrom(longExercises);
                        } while (!uniqueExercises(e[1], e[2], null) && !toManyTries());
                        // Fill in the rest of the day based on how much time is left
                        int time = e[1].timeInt + e[2].timeInt;
                        switch (time){
                            // Two half hour exercises picked
                            case 60:
                                do {
                                    e[3] = randomFrom(thirtyMinExercises);
                                } while (!uniqueExercises(e[1], e[2], e[3]) && !toManyTries());
                                // Add 15 minute drill and 15 minute conditioning
                                e[4] = randomFrom(fifteenMinExercises);
                                e[5] = randomFrom(fifteenMinConditioning);
                                break;
                            // Half hour and 45min exercises picked
                            case 75:
                                // Add two 15 minute drills, which aren't both hangboard
                                e[3] = randomFrom(fifteenMinExercises);
                                do {
                                    e[4] = randomFrom(fifteenMinExercises);
                                    // If they are both hangboard routines, force it try again. Only 1 hangboard per day
                                    if(bothHangboard(e[3], e[4])){
                                        e[4] = e[3];
                                    }
                                } while (!uniqueExercises(e[3], e[4], null) && !toManyTries());
                                // Finish with 15 minutes of conditioning
                                e[5] = randomFrom(fifteenMinConditioning);
                                break;
                            // Two 45min exercises picked, or 30 min and a 1h
                            case 90:
                                e[3] = randomFrom(fifteenMinExercises);
                                e[4] = randomFrom(fifteenMinConditioning);
                                break;

                            // 45min and 1h exercises picked
                            case 105:
                                e[3] = randomFrom(fifteenMinConditioning);
                                break;

                            // Two 1h exercises picked
                            default:
                                // Day is already 3h, add nothing more
                                break;
                        }
                    } else {
                        // if not dedicated, give a long + short exercise which aren't the same
                        e[1] = randomFrom(longExercises);
                        e[2] = randomFrom(fifteenMinExercises);
                        e[3] = randomFrom(fifteenMinConditioning);
                    }
                    break;

                // Identical to strength except it chooses power exercises
                case "Power":
                    Exercise[] powerExercises = filterByGrade(filterByEquipment(db.selectAllExerciseByType("Power")), currentGrade);
                    longExercises = filterMinimumTime(powerExercises, 30);
                    fifteenMinExercises = filterByExactTime(powerExercises, 15);
                    thirtyMinExercises = filterByExactTime(powerExercises, 30);
                    fifteenMinConditioning = filterByEquipment(db.selectAllExerciseByType("Conditioning"));

                    // If dedicated, give two long exercises. Fill in the rest with 15 minute
                    // drills until the session is three hours.
                    if(commitmentLevel.equals(DEDICATED)){
                        // Add two long exercises. If they are the exact same, try again
                        e[1] = randomFrom(longExercises);
                        do {
                            e[2] = randomFrom(longExercises);
                        } while (!uniqueExercises(e[1], e[2], null) && !toManyTries());
                        // Fill in the rest of the day based on how much time is left
                        int time = e[1].timeInt + e[2].timeInt;
                        switch (time){
                            // Two half hour exercises picked
                            case 60:
                                do {
                                    e[3] = randomFrom(thirtyMinExercises);
                                } while (!uniqueExercises(e[1], e[2], e[3]) && !toManyTries());
                                // Add 15 minute drill and 15 minute conditioning
                                e[4] = randomFrom(fifteenMinExercises);
                                e[5] = randomFrom(fifteenMinConditioning);
                                break;
                            // Half hour and 45min exercises picked
                            case 75:
                                // Add two 15 minute drills
                                e[3] = randomFrom(fifteenMinExercises);
                                do {
                                    e[4] = randomFrom(fifteenMinExercises);
                                } while (!uniqueExercises(e[3], e[4], null) && !toManyTries());
                                // Finish with 15 minutes of conditioning
                                e[5] = randomFrom(fifteenMinConditioning);
                                break;
                            // Two 45min exercises picked, or 30 min and a 1h
                            case 90:
                                e[3] = randomFrom(fifteenMinExercises);
                                e[4] = randomFrom(fifteenMinConditioning);
                                break;

                            // 45min and 1h exercises picked
                            case 105:
                                e[3] = randomFrom(fifteenMinConditioning);
                                break;

                            // Two 1h exercises picked
                            default:
                                // Day is already 3h, add nothing more
                                break;
                        }
                    } else {
                        // if not dedicated, give a long + short exercise
                        e[1] = randomFrom(longExercises);
                        e[2] = randomFrom(fifteenMinExercises);
                        e[3] = randomFrom(fifteenMinConditioning);
                    }

                    break;

                case "Power Endurance":
                    // All power endurance drills are of "any" grade
                    Exercise[] powEndExercises = filterByEquipment(db.selectAllExerciseByType("Power Endurance"));
                    longExercises = filterMinimumTime(powEndExercises, 30);
                    fifteenMinExercises = filterByExactTime(powEndExercises, 15);
                    thirtyMinExercises = filterByExactTime(powEndExercises, 30);
                    fifteenMinConditioning = filterByEquipment(db.selectAllExerciseByType("Conditioning"));
                    Exercise[] fifteenMinStr = filterByGrade(filterByExactTime(filterByEquipment(db.selectAllExerciseByType("Strength")), 15), currentGrade);
                    Exercise[] fifteenMinPow = filterByGrade(filterByExactTime(filterByEquipment(db.selectAllExerciseByType("Power")), 15), currentGrade);

                    // If dedicated, give two long exercises. Fill in the rest with 15 minute
                    // drills until the session is three hours.
                    if(commitmentLevel.equals(DEDICATED)){
                        // Add two long exercises. If they are the exact same, try again
                        e[1] = randomFrom(longExercises);
                        do {
                            e[2] = randomFrom(longExercises);
                        } while (!uniqueExercises(e[1], e[2], null) && !toManyTries());
                        // Fill in the rest of the day based on how much time is left
                        int time = e[1].timeInt + e[2].timeInt;
                        switch (time){
                            // Two half hour exercises picked
                            case 60:
                                do {
                                    // Add a third 30 minute exercise
                                    e[3] = randomFrom(thirtyMinExercises);
                                } while (!uniqueExercises(e[1], e[2], e[3]) && !toManyTries());
                                // Add 15 minute drill of either strength or power
                                int strOrPow = randomInt(1);
                                switch (strOrPow){
                                    case 0:
                                        e[4] = randomFrom(fifteenMinStr);
                                        break;
                                    case 1:
                                        e[4] = randomFrom(fifteenMinPow);
                                        break;
                                }
                                // finish with conditioning
                                e[5] = randomFrom(fifteenMinConditioning);
                                break;
                            // Half hour and 45min exercises picked
                            case 75:
                                // Add three 15 minute drills, pow, str then con
                                e[3] = randomFrom(fifteenMinPow);
                                e[4] = randomFrom(fifteenMinStr);
                                e[5] = randomFrom(fifteenMinConditioning);
                                break;
                            // Two 45min exercises picked, or 30 min and a 1h
                            case 90:
                                strOrPow = randomInt(1);
                                switch (strOrPow){
                                    case 0:
                                        e[3] = randomFrom(fifteenMinStr);
                                        break;
                                    case 1:
                                        e[3] = randomFrom(fifteenMinPow);
                                        break;
                                }
                                e[4] = randomFrom(fifteenMinConditioning);
                                break;

                            // 45min and 1h exercises picked
                            case 105:
                                e[3] = randomFrom(fifteenMinConditioning);
                                break;

                            // Two 1h exercises picked
                            default:
                                // Day is already 3h, add nothing more
                                break;
                        }
                    } else {
                        // if not dedicated, give a long + short str/pow exercise
                        e[1] = randomFrom(longExercises);
                        int strOrPow = randomInt(1);
                        switch (strOrPow){
                            case 0:
                                e[2] = randomFrom(fifteenMinStr);
                                break;
                            case 1:
                                e[2] = randomFrom(fifteenMinPow);
                                break;
                        }
                        // finish with conditioning
                        e[3] = randomFrom(fifteenMinConditioning);
                    }
                    break;

                case "Endurance":
                    // All power endurance drills are of "any" grade
                    Exercise[] endExercises = filterByEquipment(db.selectAllExerciseByType("Endurance"));
                    longExercises = filterMinimumTime(endExercises, 30);
                    thirtyMinExercises = filterByExactTime(endExercises, 30);
                    fifteenMinConditioning = filterByEquipment(db.selectAllExerciseByType("Conditioning"));
                    fifteenMinStr = filterByGrade(filterByExactTime(filterByEquipment(db.selectAllExerciseByType("Strength")), 15), currentGrade);
                    fifteenMinPow = filterByGrade(filterByExactTime(filterByEquipment(db.selectAllExerciseByType("Power")), 15), currentGrade);

                    // If dedicated, give two long exercises. Fill in the rest with 15 minute
                    // drills until the session is three hours.
                    if(commitmentLevel.equals(DEDICATED)){
                        // Add two long exercises. If they are the exact same, try again
                        e[1] = randomFrom(longExercises);
                        do {
                            e[2] = randomFrom(longExercises);
                        } while (!uniqueExercises(e[1], e[2], null) && !toManyTries());
                        // Fill in the rest of the day based on how much time is left
                        int time = e[1].timeInt + e[2].timeInt;
                        switch (time){
                            // Two half hour exercises picked
                            case 60:
                                do {
                                    // Add a third 30 minute exercise
                                    e[3] = randomFrom(thirtyMinExercises);
                                } while (!uniqueExercises(e[1], e[2], e[3]) && !toManyTries());
                                // Add 15 minute drill of either strength or power
                                int strOrPow = randomInt(1);
                                switch (strOrPow){
                                    case 0:
                                        e[4] = randomFrom(fifteenMinStr);
                                        break;
                                    case 1:
                                        e[4] = randomFrom(fifteenMinPow);
                                        break;
                                }
                                // finish with conditioning
                                e[5] = randomFrom(fifteenMinConditioning);
                                break;
                            // Half hour and 45min exercises picked
                            case 75:
                                // Add three 15 minute drills, pow, str then con
                                e[3] = randomFrom(fifteenMinPow);
                                e[4] = randomFrom(fifteenMinStr);
                                e[5] = randomFrom(fifteenMinConditioning);
                                break;
                            // Two 45min exercises picked, or 30 min and a 1h
                            case 90:
                                strOrPow = randomInt(1);
                                switch (strOrPow){
                                    case 0:
                                        e[3] = randomFrom(fifteenMinStr);
                                        break;
                                    case 1:
                                        e[3] = randomFrom(fifteenMinPow);
                                        break;
                                }
                                e[4] = randomFrom(fifteenMinConditioning);
                                break;

                            // 45min and 1h exercises picked
                            case 105:
                                e[3] = randomFrom(fifteenMinConditioning);
                                break;

                            // Two 1h exercises picked
                            default:
                                // Day is already 3h, add nothing more
                                break;
                        }
                    } else {
                        // if not dedicated, give a long + short str/pow exercise
                        e[1] = randomFrom(longExercises);
                        int strOrPow = randomInt(1);
                        switch (strOrPow){
                            case 0:
                                e[2] = randomFrom(fifteenMinStr);
                                break;
                            case 1:
                                e[2] = randomFrom(fifteenMinPow);
                                break;
                        }
                        // finish with conditioning
                        e[3] = randomFrom(fifteenMinConditioning);
                    }
                    break;
            }
            trainingDay.setExercises(e);
            if(toManyTries()){
                trainingDay.type = "ERROR";
            }
            db.insertProgramRow(trainingDay, programName);
            i++;
       }
       if(i == programLength){
            done = true;
       }
        return i;
    }

    // Takes an exercise array and returns another exercise array where every
    // exercise where the user doesn't have the equipment is removed.
    Exercise[] filterByEquipment(Exercise[] e){
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

    // Returns a random exercise from an exercise array
    Exercise randomFrom(Exercise[] e){
        return e[randomInt(e.length)];
    }

    // returns true if all three exercises are different.
    // Can pass null as 3rd exercise to compare 2
    boolean uniqueExercises(Exercise one, Exercise two, Exercise three){
        if(three == null){
            if(!one.name.equals(two.name)){
                return true;
            } else {
                return false;
            }
        } else {
            if(!one.name.equals(two.name)){
                if(!two.name.equals(three.name)){
                    if(!one.name.equals(three.name)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Takes an exercise array and returns another exercise array where every
    // exercise is equal to or over the duration given
    Exercise[] filterMinimumTime(Exercise[] e, int t){
        boolean[] valid = new boolean[e.length];
        int count = 0;
        for(int i = 0; i < e.length; i++){
            if(e[i].timeInt >= t){
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

    // Takes an exercise array and returns another exercise array where every
    // exercise is the difficulty selected
    Exercise[] filterByGrade(Exercise[] e, String s){
        boolean[] valid = new boolean[e.length];
        int count = 0;
        for(int i = 0; i < e.length; i++){
            if(e[i].diff.equals(s) || e[i].diff.equals(ExerciseBuilder.ANY)){
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

    // Takes an exercise array and returns another exercise array where every
    // exercise is equal to the duration given
    Exercise[] filterByExactTime(Exercise[] e, int t){
        boolean[] valid = new boolean[e.length];
        int count = 0;
        for(int i = 0; i < e.length; i++){
            if(e[i].timeInt == t){
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
    // If given less than or equal to zero as the range, return zero
    int randomInt(int maxExclusive){
        if(random == null){
            random = new Random();
        }
        if(maxExclusive <= 0){
            return 0;
        } else {
            return random.nextInt(maxExclusive);
        }
    }

    // Returns true if both exercises contain "Hangboard" in the name
    // and false if only one or neither do
    boolean bothHangboard(Exercise e, Exercise q){
        if(e.name.contains("Hangboard")){
            if(q.name.contains("Hangboard")){
                return true;
            }
        }
        return false;
    }

    boolean toManyTries(){
        attempts++;
        if(attempts > 10000){
            return true;
        } else {
            return false;
        }
    }

}

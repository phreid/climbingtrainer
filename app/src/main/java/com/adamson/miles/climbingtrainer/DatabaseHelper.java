package com.adamson.miles.climbingtrainer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";

    private static final int DATABASE_VERSION = 1;

    // The exercise table saves all exercises with their details
    private static final String T1 = "exercises";
    private static final String T1_name = "name";
    private static final String T1_desc = "description"; // cannot be desc because that is SQL code
    private static final String T1_type = "type";
    private static final String T1_sets = "sets";
    private static final String T1_reps = "reps";
    private static final String T1_rest = "rest";
    private static final String T1_diff = "diff";
    private static final String T1_equip = "equip";
    private static final String T1_time = "time";

    // A user-created program is saved as every exercise supposed to be done, all
    // in order from start to finish, with the date to do it.
    private static final String T2_date = "day";
    private static final String T2_exercise = "exercise";
    private static final String T2_type = "type";
    private static final String T2_dayOfWeek = "dayOfWeek";
    private static final String T2_completed = "completed";
    private static final String T2_week = "week";

    private static final String T3 = "programs";
    private static final String T3_name = "name";

    private static final String TEXT = " TEXT";
    private static final String UNIQUE = " UNIQUE";
    private static final String END = ");";

    // Indexes for the array returned by selectRoutine
    public static final int ACTIVITIES = 0;
    public static final int DURATIONS = 1;
    public static final int READABLES = 2;

    // Indexes for array returned by selectLogbook
    public static final int ENTRIES = 0;
    public static final int KEYS = 1;
    public static final int NAMES = 2;

    public DatabaseHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + T1 + " (" +
                T1_name + TEXT + UNIQUE + ", " +
                T1_desc + TEXT + ", " +
                T1_type + TEXT + ", " +
                T1_sets + TEXT + ", " +
                T1_reps + TEXT + ", " +
                T1_rest + TEXT + ", " +
                T1_diff + TEXT + ", " +
                T1_equip + TEXT + ", " +
                T1_time + TEXT +
                END);

        database.execSQL("CREATE TABLE " + T3 + " (" + T1_name + TEXT + UNIQUE + END);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + T1);
        //database.execSQL("DROP TABLE IF EXISTS " + T3);
        onCreate(database);
    }

    // Inserts an exercise. Returns true if succeeded.
    // Since name is UNIQUE, it should fail with duplicates.
    public boolean insertExercise(Exercise exercise) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+T1+" WHERE "+T1_name+" = '"+exercise.name+"'", null);
        // If cursor fails move to first, the cursor is empty, so we can
        // insert and the exercise will be unique
        if(!c.moveToFirst()) {
            ContentValues myCV = new ContentValues();
            myCV.put(T1_name, exercise.name);
            myCV.put(T1_desc, exercise.desc);
            myCV.put(T1_type, exercise.type);
            myCV.put(T1_sets, exercise.sets);
            myCV.put(T1_reps, exercise.reps);
            myCV.put(T1_rest, exercise.rest);
            myCV.put(T1_diff, exercise.diff);
            myCV.put(T1_equip, exercise.equip);
            myCV.put(T1_time, exercise.time);

            long result = db.insert(T1, null, myCV);

            // -1 is an error
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    // Returns all exercises in an array
    public Exercise[] selectAllExercises() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + T1 +" ORDER BY "+T1_type+";", null);
        Exercise[] exercises = new Exercise[cursor.getCount()];

        // if there are any exercises, return them
        if (cursor.moveToFirst()) {
            for (int i = 0; i < exercises.length; i++) {
                Exercise row = new Exercise()
                .setName(cursor.getString(0))
                .setDesc(cursor.getString(1))
                .setType(cursor.getString(2))
                .setSets(cursor.getString(3))
                .setReps(cursor.getString(4))
                .setRest(cursor.getString(5))
                .setDiff(cursor.getString(6))
                .setEquip(cursor.getString(7))
                .setTime(cursor.getString(8));
                exercises[i] = row;
                cursor.moveToNext();
            }
            cursor.close();
        }
        return exercises;
    }

    // Returns all programs in a string array. Returns null if there are NONE
    public String[] selectAllPrograms() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+T3+";", null);
        String[] names = new String[cursor.getCount()];

        // if there are any program weeks, return them
        if (cursor.moveToFirst()) {
            for (int i = 0; i < names.length; i++) {
                names[i] = replaceUnderscores(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
            return names;
        } else {
            return null;
        }
    }

    // Returns all programs in a string array. Returns null if there are NONE
    public boolean programExists(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+T3+" WHERE "+T3_name+"='"+removeSpaces(name)+"';", null);

        // if there is a program of this name, return true
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }


    // Returns all exercises of a type in an array
    public Exercise[] selectAllExerciseByType(String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+T1+" WHERE "+T1_type+ " = '"+type+"';", null);
        Exercise[] exercises = new Exercise[cursor.getCount()];

        // if there are any exercises, return them
        if (cursor.moveToFirst()) {
            for (int i = 0; i < exercises.length; i++) {
                Exercise row = new Exercise()
                        .setName(cursor.getString(0))
                        .setDesc(cursor.getString(1))
                        .setType(cursor.getString(2))
                        .setSets(cursor.getString(3))
                        .setReps(cursor.getString(4))
                        .setRest(cursor.getString(5))
                        .setDiff(cursor.getString(6))
                        .setEquip(cursor.getString(7))
                        .setTime(cursor.getString(8));
                exercises[i] = row;
                cursor.moveToNext();
            }
            cursor.close();
        }
        return exercises;
    }

    // Returns an exercises by its name. Returns null if it doesn't exist.
    public Exercise selectExerciseByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+T1+" WHERE "+T1_name+ " = '"+name+"';", null);

        // if there are any exercises, return them
        if (cursor.moveToFirst()) {
            Exercise exercise = new Exercise()
                    .setName(cursor.getString(0))
                    .setDesc(cursor.getString(1))
                    .setType(cursor.getString(2))
                    .setSets(cursor.getString(3))
                    .setReps(cursor.getString(4))
                    .setRest(cursor.getString(5))
                    .setDiff(cursor.getString(6))
                    .setEquip(cursor.getString(7))
                    .setTime(cursor.getString(8));
            cursor.close();
            return exercise;
        } else {
            return null;
        }
    }

    public ExerciseAndDate selectFromProgramByWeek(String program, String week) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+removeSpaces(program)+"' WHERE "+T2_week+ " = '"+week+"';", null);

        String[] exerciseNames = new String[cursor.getCount()];
        String[] dateStrings = new String[cursor.getCount()];
        String[] typeStrings = new String[cursor.getCount()];
        String[] daysOfWeekStrings = new String[cursor.getCount()];
        Exercise[] exercises = new Exercise[cursor.getCount()];

        // put all exercise weeks into string array
        if (cursor.moveToFirst()) {
            for (int i = 0; i < exerciseNames.length; i++) {
                dateStrings[i] = cursor.getString(0);
                exerciseNames[i] = cursor.getString(1);
                typeStrings[i] = cursor.getString(2);
                daysOfWeekStrings[i] = cursor.getString(3);
                exercises[i] = selectExerciseByName(exerciseNames[i]);
                cursor.moveToNext();
            }
            cursor.close();
            return new ExerciseAndDate(dateStrings, exerciseNames, daysOfWeekStrings, typeStrings, exercises);
        } else {
            return null;
        }
    }

    // Returns true if a week is completed
    public boolean programWeekCompleted(String program, String week) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+removeSpaces(program)+"' WHERE "+T2_week+ " = '"+week+"' AND "+T2_completed+"='0';", null);
        if(cursor.getCount() == 0){
            return true;
        } else {
            return false;
        }
    }

    public String selectTypeByWeek(String program, String week) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT "+T2_type+" FROM '"+removeSpaces(program)+"' WHERE "+T2_week+ " = '"+week+"';", null);
        // Return the type
        if (cursor.moveToFirst()) {
            String s = cursor.getString(0);
            cursor.close();
            return s;
        } else {
            return null;
        }
    }

    public ExerciseAndDate selectFromProgramByDate(String program, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+removeSpaces(program)+"' WHERE "+T2_date+ " = '"+date+"';", null);

        String[] exerciseNames = new String[cursor.getCount()];
        String[] dateStrings = new String[cursor.getCount()];
        String[] typeStrings = new String[cursor.getCount()];
        String[] daysOfWeekStrings = new String[cursor.getCount()];
        Exercise[] exercises = new Exercise[cursor.getCount()];

        // put all exercise weeks into string array
        if (cursor.moveToFirst()) {
            for (int i = 0; i < exerciseNames.length; i++) {
                dateStrings[i] = cursor.getString(0);
                exerciseNames[i] = cursor.getString(1);
                typeStrings[i] = cursor.getString(2);
                daysOfWeekStrings[i] = cursor.getString(3);
                exercises[i] = selectExerciseByName(exerciseNames[i]);
                cursor.moveToNext();
            }
            cursor.close();
            return new ExerciseAndDate(dateStrings, exerciseNames, daysOfWeekStrings, typeStrings, exercises);
        } else {
            return null;
        }
    }

    public String[] selectProgramWeeks(String program) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT "+T2_week+" FROM '"+removeSpaces(program)+"';", null);

        String[] dateStrings = new String[cursor.getCount()];

        // put all exercise weeks into string array
        if (cursor.moveToFirst()) {
            for (int i = 0; i < dateStrings.length; i++) {
                dateStrings[i] = cursor.getString(0);
                cursor.moveToNext();
            }
            cursor.close();
            return dateStrings;
        } else {
            return null;
        }
    }

    // Creates a program of a given name
    public boolean createProgram(String name) {
        String nameNoSpaces = removeSpaces(name);
        SQLiteDatabase db = this.getWritableDatabase();
        // If a program by that name doesn't already exist
        if (insertProgramName(nameNoSpaces)) {
            db.execSQL("CREATE TABLE IF NOT EXISTS '" + nameNoSpaces + "' (" +
                    T2_date + TEXT + ", " +
                    T2_exercise + TEXT + ", " +
                    T2_type + TEXT + ", " +
                    T2_dayOfWeek + TEXT + ", " +
                    T2_completed + TEXT + ", " +
                    T2_week + TEXT +
                    END);
        } else {
            return false;
        }
        return true;
    }

    // With an array of trainingDays objects, save the program in sql.
    // If a program by that name already exists, return false
    public boolean insertProgram(TrainingDay[] trainingDays, String name){
        String nameNoSpaces = removeSpaces(name);
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat format_EEEE = new SimpleDateFormat("EEEE");
        if(createProgram(nameNoSpaces)){
            // Insert all the exercises and their dates into the table
            for (int i = 0; i < trainingDays.length; i++) {
                // null days are rest days. Skip them.
                if (trainingDays[i] != null) {
                    // Insert a row for every exercise provided, and the date to do it on.
                    for (int k = 0; k < trainingDays[i].exerciseCount; k++) {
                        ContentValues myCV = new ContentValues();
                        myCV.put(T2_date, trainingDays[i].dateString);
                        myCV.put(T2_exercise, trainingDays[i].exercises[k].name);
                        myCV.put(T2_type, trainingDays[i].type);
                        myCV.put(T2_dayOfWeek, format_EEEE.format(trainingDays[i].date));
                        myCV.put(T2_completed, "0");
                        if (db.insert(nameNoSpaces, null, myCV) == -1) {
                            return false;
                        }
                    }
                }
            }
            // creation and inserting all succeeded.
            return true;
        } else {
            return false;
        }
    }

    // Insert a single trainingDay into a program
    public boolean insertProgramRow(TrainingDay trainingDay, String week, String name){
        String nameNoSpaces = removeSpaces(name);
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat format_EEEE = new SimpleDateFormat("EEEE");

        for(int k = 0; k < trainingDay.exercises.length; k++) {
            // Exercise array is not of known length, check for end which is null
            if (trainingDay.exercises[k] != null) {
                ContentValues myCV = new ContentValues();
                myCV.put(T2_date, trainingDay.dateString);
                myCV.put(T2_exercise, trainingDay.exercises[k].name);
                myCV.put(T2_type, trainingDay.type);
                myCV.put(T2_dayOfWeek, format_EEEE.format(trainingDay.date));
                myCV.put(T2_completed, "0");
                myCV.put(T2_week, week);

                if (db.insert(nameNoSpaces, null, myCV) == -1) {
                    return false;
                }
            }
        }
        // creation and inserting all succeeded.
        return true;
    }

    // Replaces underscores with spaces again for the text user sees
    private String replaceUnderscores(String input){
        String space = " ";
        String underscore = "_";
        char[] result = new char[input.length()];
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == underscore.charAt(0)){
                result[i] = space.charAt(0);
            } else {
                result[i] = input.charAt(i);
            }
        }
        return new String(result);
    }

    // Removes spaces and puts underscores instead, since table weeks cannot
    // have spaces in them.
    private String removeSpaces(String input){
        String space = " ";
        String underscore = "_";
        char[] result = new char[input.length()];
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == space.charAt(0)){
                result[i] = underscore.charAt(0);
            } else {
                result[i] = input.charAt(i);
            }
        }
        return new String(result);
    }

    // Inserts a program. Returns true if succeeded.
    // Since name is UNIQUE, it should fail with duplicates.
    private boolean insertProgramName(String name) {
        String nameNoSpaces = removeSpaces(name);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+T3+" WHERE "+T3_name+" = '"+nameNoSpaces+"'", null);
        // If cursor fails move to first, the cursor is empty, so we can
        // insert and the exercise will be unique
        if(!c.moveToFirst()) {
            ContentValues myCV = new ContentValues();
            myCV.put(T3_name, nameNoSpaces);

            long result = db.insert(T3, null, myCV);

            // -1 is an error
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    // Selects a program and it's returned in a ExerciseAndDate class
    // object, which is a list if every exercise, its name, and date to do it on.
    public ExerciseAndDate selectProgram(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '" + removeSpaces(name) + "';", null);
        String[] exerciseNames = new String[cursor.getCount()];
        String[] dateStrings = new String[cursor.getCount()];
        String[] typeStrings = new String[cursor.getCount()];
        String[] daysOfWeekStrings = new String[cursor.getCount()];
        Exercise[] exercises = new Exercise[cursor.getCount()];


        // put all exercise weeks into string array
        if (cursor.moveToFirst()) {
            for (int i = 0; i < exerciseNames.length; i++) {
                dateStrings[i] = cursor.getString(0);
                exerciseNames[i] = cursor.getString(1);
                typeStrings[i] = cursor.getString(2);
                daysOfWeekStrings[i] = cursor.getString(3);
                exercises[i] = selectExerciseByName(exerciseNames[i]);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return new ExerciseAndDate(dateStrings, exerciseNames, daysOfWeekStrings, typeStrings, exercises);
    }

    // Updates the completed column of a program "name" on a given date and exercise to being true
    public void completeExercise(String date, String e, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(T2_completed,"1");
        db.update(removeSpaces(name), cv, T2_date+"='"+date+"' AND "+T2_exercise+"='"+e+"'", null);
    }

    // Updates the completed column of a program "name" on a given date and exercise to being false
    public void uncompleteExercise(String date, String e, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(T2_completed,"0");
        db.update(removeSpaces(name), cv, T2_date+"='"+date+"' AND "+T2_exercise+"='"+e+"'", null);
    }

    // Returns true if an exercise on a given date is completed
    // Returns false if not completed or that row didn't exist
    public boolean isCompleted(String date, String e, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+removeSpaces(name)+"' WHERE ("+T2_date+"='"+date+"' AND "+T2_exercise+"='"+e+"');", null);
        if(cursor.moveToFirst()){
            if(cursor.getString(4).equals("1")){
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }

    // Deletes a program
    public void deleteProgram(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+T3+" WHERE "+T3_name+"='"+removeSpaces(name)+"';");
        db.execSQL("DROP TABLE '"+removeSpaces(name)+"';");
    }

    // Deletes exercise
    public String deleteExercise(String name){
        if(!inProgram(name)) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + T1 + " WHERE " + T1_name + "='" + name + "';");
            return null;
        } else {
            return "This exercise is currently in one of your programs, and can't be deleted.";
        }
    }

    // Replaces all exercises in a program with a different one
    public void updateProgram(Exercise oldExercise, Exercise newExercise, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(T2_exercise, newExercise.name);
        cv.put(T2_completed,"0");
        db.update(removeSpaces(name), cv, T2_exercise+"='"+oldExercise.name+"' AND "+T2_completed+"='0'", null);
    }

    // Returns true if an exercise is in ANY program
    // Returns false if it is not
    public boolean inProgram(String e){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] programs = selectAllPrograms();
        for(int i = 0; i < programs.length; i++){
            Cursor cursor = db.rawQuery("SELECT * FROM '"+removeSpaces(programs[i])+"' WHERE "+T2_exercise+"='"+e+"';", null);
            if(cursor.getCount() != 0){
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

}

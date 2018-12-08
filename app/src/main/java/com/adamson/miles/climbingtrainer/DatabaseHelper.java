package com.adamson.miles.climbingtrainer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";

    private static final int DATABASE_VERSION = 1;

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

    private static final String T2_C1 = "activity";
    private static final String T2_C2 = "duration";

    private static final String T3 = "logbook";
    private static final String T3_C1 = "workout";
    private static final String T3_C2 = "date";

    private static final String TEXT = " TEXT";
    private static final String KEY = " _ID INTEGER PRIMARY KEY AUTOINCREMENT, ";
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
        /*
            private static final String T1_name = "name";
            private static final String T1_desc = "description"; // cannot be desc because that is SQL code
            private static final String T1_type = "type";
            private static final String T1_sets = "sets";
            private static final String T1_reps = "reps";
            private static final String T1_rest = "rest";
            private static final String T1_diff = "diff";
            private static final String T1_equip = "equip";
            private static final String T1_time = "time";
         */
        //database.execSQL("CREATE TABLE " + T3 + " (" + KEY + T3_C1 + TEXT + ", " + T3_C2 + TEXT + END);
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

    // Returns all exercises in an array
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
    /*

    // Deletes an entire routine table and removes it from workouts list
    public void deleteWorkout(String rowName) {
        String rowNameNoSpaces = removeSpaces(rowName);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(T1, T1_C1 + " = ?", new String[]{rowNameNoSpaces});
        db.execSQL("DROP TABLE IF EXISTS " + rowNameNoSpaces);
    }

    // Adds a list of entriesList into a workout routine, if it exists. If it doesn't,
    // return false.
    public void fillWorkout(String table, String[] names, String[] durations){
        String tableNoSpaces = removeSpaces(table);
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i = 0; i < names.length; i++){
            ContentValues myCV = new ContentValues();
            myCV.put(T2_C1, names[i]);
            myCV.put(T2_C2, durations[i]);
            db.insert(tableNoSpaces, null, myCV);
        }
    }

    // Returns the activities, durations and a human readable string for the workout.
    // If the workout doesn't exist, the lists returned are empty.
    public ArrayList<String>[] selectRoutine(String table){
        String tableNoSpaces = removeSpaces(table);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='"
                + tableNoSpaces + "';)", null);
        ArrayList<String> activities = new ArrayList<>();
        ArrayList<String> durations = new ArrayList<>();
        ArrayList<String> readable = new ArrayList<>();

        // If workout exists, populate the return arrays.
        if(cursor.getCount() != 0) {
            cursor = db.rawQuery("SELECT * FROM " + tableNoSpaces + ";", null);
            if (cursor.moveToFirst()) {
                do {
                    activities.add(cursor.getString(ACTIVITIES));
                    durations.add(cursor.getString(DURATIONS));
                    readable.add(cursor.getString(ACTIVITIES) + ": " +
                            cursor.getString(DURATIONS) + "s");
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }

        return new ArrayList[]{activities, durations, readable};
    }

    // Inserts a workout_editable_item into logbook table, which is the workout name
    // and workout date strings. Date string should be pre-formatted
    public boolean insertLogbook(String name, String date){
        String nameNoSpace = removeSpaces(name);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues myCV = new ContentValues();

        myCV.put(T3_C1, nameNoSpace);
        myCV.put(T3_C2, date);

        long result = db.insert(T3, null, myCV);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Returns the entire logbook table
    public ArrayList<String>[] selectLogbook(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + T3 + ";", null);

        ArrayList<String> tableEntires = new ArrayList<>();
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                keys.add(cursor.getString(0));
                names.add(replaceUnderscores(cursor.getString(1)));
                tableEntires.add(cursor.getString(2) + ":\n " + replaceUnderscores(cursor.getString(1)));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return new ArrayList[] {tableEntires, keys, names};
    }

    // Deletes an entry in the logbook table based on its id
    public void deleteLogbookEntry(String key){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(T3, "_ID=?", new String[] { key });
    }
    */
}

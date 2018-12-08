package com.adamson.miles.climbingtrainer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by miles on 2018-12-02.
 */

public class ProgramBuilder {
    private static ProgramBuilder ourInstance = new ProgramBuilder();

    private String programType;
    private String sessionsPerWeek;
    private String commitmentLevel;
    private String sessionDuration;
    private String currentGrade;
    private String startDate;
    private String endDate;
    private boolean[] equipmentAvailable = new boolean[12];


    public static ProgramBuilder getInstance() {
        if(null == ourInstance){
            ourInstance = new ProgramBuilder();
        }
        return ourInstance;
    }

    private ProgramBuilder() {}

    public void setProgramType(String s){programType = s;}
    public void setSessionsPerWeek(String s){sessionsPerWeek = s;}
    public void setCommitmentLevel(String s){commitmentLevel = s;}
    public void setSessionDuration(String s){sessionDuration = s;}
    public void setCurrentGrade(String s){currentGrade = s;}
    public void setEquipmentAvailable(int i){equipmentAvailable[i] = true;};
    public void setStartDate(String date){startDate = date;}
    public void setEndDate(String date){endDate = date;}

    public String getProgramType(){return programType;}
    public String getSessionsPerWeek(){return sessionsPerWeek;}
    public String getCommitmentLevel(){return commitmentLevel;}
    public String getSessionDuration(){return sessionDuration;}
    public String getCurrentGrade(){return currentGrade;}
    public boolean[] getEquipmentAvailable(){return equipmentAvailable;}
    public String getStartDate(){return startDate;}
    public String getEndDate(){return endDate;}
}

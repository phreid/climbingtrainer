package com.adamson.miles.climbingtrainer;

import java.io.Serializable;

public class Exercise implements Serializable {

    String name;
    String desc;
    String type;
    String sets;
    String reps;
    String rest;
    String diff;  // difficulty minimum
    String equip; // required equipment
    String time;

    public Exercise(){}
    
    public Exercise(Exercise e){
        name = e.name;
        desc = e.desc;
        type = e.type;
        sets = e.sets;
        reps = e.reps;
        rest = e.rest;
        diff = e.diff;
        equip = e.equip;
        time = e.time;
    }

    public Exercise setName(String n) {this.name = n; return this;}
    public Exercise setDesc(String d) {this.desc = d; return this;}
    public Exercise setType(String t) {this.type = t; return this;}
    public Exercise setSets(String s) {this.sets = s; return this;}
    public Exercise setReps(String r) {this.reps = r; return this;}
    public Exercise setRest(String r) {this.rest = r; return this;}
    public Exercise setDiff(String d) {this.diff = d; return this;}
    public Exercise setEquip(String e) {this.equip = e; return this;}
    public Exercise setTime(String t) {this.time = t; return this;}

}


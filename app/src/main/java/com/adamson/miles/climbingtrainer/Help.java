package com.adamson.miles.climbingtrainer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by miles on 2018-11-28.
 */

public class Help {
    public static void helpAlert(int id, final Context context) {
        String text;
        switch (id) {
            case R.id.imageHelpVolume:
                text = "Build only the Volume phase of a training program. Volume training aims to do as many moves as possible in a session. " +
                        "It is often trained first to prepare the body with a baseline fitness level. During this phase the climbing " +
                        "is not continuous or difficult enough to get a pump.";
                break;
            case R.id.imageHelpStrPow:
                text = "Build only the Strength and Power phase of a training program. Strength and Power are defined as 1-4 moves. Strength is your ability to hold positions, " +
                        "and power is your ability to generate from them. Strength and Power control the hardest individual moves you can do.";
                break;
            case R.id.imageHelpPowEnd:
                text = "Build only the Power Endurance phase of a training program. Power Endurance is defined as 8-15 moves." +
                        "It helps with your ability to do several near-limit moves in a row, or when slightly fatigued.";
                break;
            case R.id.imageHelpEnd:
                text = "Build only the Endurance phase of a training program. Endurance is defined as more than 20 moves." +
                        "It helps with your ability to complete long moderate sequences without getting pumped, and to recover from pump.";
                break;
            case R.id.imageHelpBoulderingProgram:
                text = "Build an entire bouldering program with three phases: Volume, Strength and Power, and then Power Endurance.";
                break;
            case R.id.imageHelpRoutesProgram:
                text = "Build an entire routes program with four phases: Volume, Strength and Power, Power Endurance and then Endurance.";
                break;
            case R.id.buttonToEquipment:
                text = "You must make a selection before continuing.";
                break;
            default:
                text = "Help text missing";
                break;


        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(text).setCancelable(true).show();
    }
}

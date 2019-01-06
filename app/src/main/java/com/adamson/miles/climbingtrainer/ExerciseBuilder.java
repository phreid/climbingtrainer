package com.adamson.miles.climbingtrainer;

import android.content.Context;

public class ExerciseBuilder {

    Context context;
    DatabaseHelper db;

    public static String[] types;
    public static int STRENGTH = 0;
    public static int POWER = 1;
    public static int POWEND = 2;
    public static int ENDURANCE = 3;
    public static int CONDITIONING = 4;
    public static int VOLUME = 5;
    public static int ALL = 6;

    public static String[] equip;
    public static int HANGBOARD = 0;
    public static int CAMPUSBOARD = 1;
    public static int FILLWALL = 2;
    public static int LEADTR = 3;
    public static int FREEWEIGHTS = 4;
    public static int PULLUPBAR = 5;
    public static int KETTLEBELLS = 6;
    public static int RINGS = 7;
    public static int MOONBOARD = 8;
    public static int MEDICINEBALLS = 9;
    public static int THERABANDS = 10;
    public static int ANGLEADJUSTABLEWALL = 11;
    public static String none = "none";

    public static String[] grades;
    public static int TEN = 1;
    public static int ELEVEN = 2;
    public static int TWELVE = 3;
    public static int THIRTEEN = 4;
    public static int FOURTEEN = 5;
    public static String ANY = "Any";

    public static String[] times;
    public static int FIFTEEN_M = 0;
    public static int THIRTY_M = 1;
    public static int FOUTYFIVE_M = 2;
    public static int SIXTY_M = 3;

    public static Exercise warmUp;
    public static Exercise freeTime;

    ExerciseBuilder(Context c){
        types = c.getResources().getStringArray(R.array.types_strings);
        equip = c.getResources().getStringArray(R.array.equipment);
        grades = c.getResources().getStringArray(R.array.grades);
        times = c.getResources().getStringArray(R.array.time_strings);

        // Warm Up is the same for each day and every person.
        // It does not need to be in the database and is instead a static variable
        warmUp = new Exercise()
                .setName("Warm Up")
                .setDesc("Get your heart rate up through skipping, running in place, cycling, or extremely easy boulders\n\n2.\tDo any injury specific warm ups which a physio has recommended\n\n3.\tClimb progressively harder boulders, avoiding pockets, crimps and shoulder-intensive moves\n\n4.\tRest briefly and then climb some boulders which are harder but still well under your limit. Ease into crimps and pockets at this time\n\n5.\tClimb a short circuit, or a long boulder slowly. Get slightly pumped, but DO NOT get flash pumped\n\n6.\tRest until you are no longer pumped what so ever. This prevents a flash pump later\n\n7.\t Climb some boulders or individual moves near your redpoint limit. Just a couple, don’t tire yourself out\n\n8.\tClimb a longer circuit which gets you fairly pumped. Stop if you are feeling flash pumped instead of a normal pump, and go back to step 6\n\n9.\tRest for around 10 minutes, keeping warm with either a jacket or light cardio if it is cold in the gym or outside\n\n10.\tIf you time this properly, 45 minutes to an hour after you start you should be able to attempt a very hard redpoint or onsight. You should not be getting flash pumped or feel too cold to pull as hard as you can.")
                .setType("Warm Up")
                .setSets("N/A")
                .setEquip(none)
                .setReps("N/A")
                .setRest("Rest if you feel like you are getting pumped during a stage you should not be.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);

        // Free time is one hour long
        // It does not need to be in the database and is instead a static variable
        freeTime = new Exercise()
                    .setName("60 Minutes of Free Time")
                    .setDesc("Climb whatever you feel like. If this is scheduled for the end of the day, you could go home early instead.")
                    .setType("Free Time")
                    .setSets("N/A")
                    .setEquip(none)
                    .setReps("N/A")
                    .setRest("N/A")
                    .setDiff(ANY)
                    .setTime(times[SIXTY_M]);

        context = c;
        db = new DatabaseHelper(context);
        db.insertExercise(freeTime);
        db.insertExercise(warmUp);
        insertFourByFour();
        insertPullUps();
        insertTwoOnOne();
        insertMediumEasyHard();
        insertPullUpLegRaise();
        insertMoonboardPowerProject();
        insertMoonboardStrengthProject();
        insertBoulderLink();
        insertHangboardOne();
        insertHangboardTwo();
        insertHangboardThree();
        insertHangboardFour();
        insertHangboardMetoliusOne();
        insertHangboardMetoliusTwo();
        insertHangboardMetoliusThree();
        insertHangboardDWoods();
        insertHangboardREIcoop();
        insertThreeHundredAbs();
        insertRotatorCuff();
        insertThreesBeginner();
        insertThreesInt();
        insertThreesAdvanced();
        insertTherabandPulls();
        insertEightMinCore();
        insertVolumeBoulderPyramid();
        insertVolumeBoulderUpDown();
        insertVolumeStickGame();
        insertTimeOnRoutes();
        insertTimeOnBoulders();
        insertPowerProject();
        insertMaxThrow();
        insertLockUps();
        insertDyno();
        insertSwingControl();
        insertTimeOnEnduranceBoulders();
        insertCircuitLinkUps();
        insertHoverBoulderStrength();
        insertHoverBoulderEndurance();
        insertKettlebells();
        insertAngleCircuit();
        insertStrengthProgressive();
        insertPowerProgressive();
        insertPowerEndProgressive();
        insertBoulderLinks();
        insertUpDownLaps();
        campusBoardPowerOne();
        campusBoardPowerTwo();
        campusBoardPowerThree();
        campusBoardPowerFour();
        campusBoardStrOne();
        campusBoardStrTwo();
        campusBoardStrThree();
        campusBoardStrFour();
    }

    void insertFourByFour(){
        Exercise e = new Exercise()
                .setName("Four by Four")
                .setDesc("Find or set four boulder problems which you can normally complete when fresh. Climb each boulder once as a set. Do four sets. If you fall before the halfway point on a boulder, climb up to where you fell and continue from there. If you fall above the halfway point, count it as a rep.")
                .setType(types[POWEND])
                .setEquip("Boulder Problems")
                .setSets("1")
                .setReps("4")
                .setRest("Do not rest between boulder attempts. Time each set and rest equal to the time it took. If you are falling too low often, rest twice as long (or select an easier problem).")
                .setTime(times[THIRTY_M])
                .setDiff(ANY);
        db.insertExercise(e);
    }

    void insertPullUps(){
        Exercise e = new Exercise()
                .setName("Pull Ups")
                .setDesc("Do pull ups until failure. Repeat three times. If you can do more than ten in a set, add weights until you fail at around ten reps.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("?")
                .setRest("3 minutes")
                .setTime(times[FIFTEEN_M])
                .setDiff(ANY);
        db.insertExercise(e);
    }

    void insertTwoOnOne() {
        Exercise e = new Exercise()
                .setName("Two on One")
                .setDesc("Choose a route near your redpoint grade which you can complete, but gets you pumped. Climb the route twice, with as little rest as possible between reps. Do this twice. See training tips for a method to do 2 laps on lead without untying.")
                .setType(types[ENDURANCE])
                .setSets("2")
                .setEquip(equip[LEADTR])
                .setReps("2")
                .setTime(times[THIRTY_M])
                .setRest("Rest for 10-15 minutes between sets.")
                .setDiff(ANY);
        db.insertExercise(e);
    }

    void insertMediumEasyHard(){
        Exercise e = new Exercise()
                .setName("Medium-Easy-Hard Laps")
                .setDesc("Find three routes. Medium is a route which gets you slightly pumped. Easy is a route where you can recover throughout. Hard is near your redpoint grade. Climb them in that order, back to back. Do not rest between reps, but you may climb the easy route as slowly as you wish.")
                .setType(types[ENDURANCE])
                .setSets("1")
                .setEquip(equip[LEADTR])
                .setReps("3")
                .setTime(times[THIRTY_M])
                .setRest("This drill has only one set, with no rest between reps.")
                .setDiff(ANY);
        db.insertExercise(e);
    }

    void insertPullUpLegRaise(){
        Exercise e = new Exercise()
                .setName("Pull Ups with Leg Raise")
                .setDesc("Do slow pull ups, with a leg raise. At the bottom of your pull up, be hanging straight. At the top, legs out at 90 degrees. Connect the two positions as slowly and smoothly as possible. Hold the end position for 5 seconds before lowering slowly.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("5")
                .setTime(times[FIFTEEN_M])
                .setRest("Rest for one minute between sets.")
                .setDiff(ANY);
        db.insertExercise(e);
    }

    void insertMoonboardPowerProject(){
        Exercise e = new Exercise()
                .setName("Project Powerful Boulders on the Moonboard")
                .setDesc("Project powerful (dynamic) boulders on the moonboard. Rest well between attempts. If you're stuck on a move, try to do the move from a more positive hold. For example, you cannot hit a large move with your right hand. Instead of using the lower right hand in the problem, work the move with a higher hold until you can complete the real move.")
                .setType(types[POWER])
                .setSets("1")
                .setEquip(equip[MOONBOARD])
                .setReps("1")
                .setTime(times[FOUTYFIVE_M])
                .setRest("Rest completely between attempts.")
                .setDiff(grades[TWELVE]);
        db.insertExercise(e);
    }

    void insertMoonboardStrengthProject(){
        Exercise e = new Exercise()
                .setName("Project Static Boulders on the Moonboard")
                .setDesc("Project strength (static) boulders on the moonboard. Rest well between attempts. If you're stuck on a move, try to do the move from a more positive hold. For example, say you cannot hit a large move with your right hand. Instead of using the lower right hand in the problem, work the move with a higher hold until you can complete the real move.")
                .setType(types[STRENGTH])
                .setSets("1")
                .setEquip(equip[MOONBOARD])
                .setReps("1")
                .setTime(times[FOUTYFIVE_M])
                .setRest("Rest completely between attempts.")
                .setDiff(grades[TWELVE]);
        db.insertExercise(e);
    }

    void insertBoulderLink(){
        Exercise e = new Exercise()
                .setName("Boulder Linkup")
                .setDesc("Set or choose a four to six move problem which is near your redpoint level. Then, set a ten to fifteen move traverse into the starting holds. The traverse can have open feet, the boulder should have set feet. Attempt to link the traverse into the boulder four times.")
                .setType(types[POWEND])
                .setSets("1")
                .setEquip(equip[FILLWALL])
                .setReps("4")
                .setTime(times[THIRTY_M])
                .setRest("Rest three minutes between attempts.")
                .setDiff(ANY);
        db.insertExercise(e);
    }

    void insertHangboardOne() {
        Exercise e = new Exercise()
                .setName("10s Hangboard Repeaters 1")
                .setDesc("Do 10 second hangs on a medium edge, with a 30 second rest, four times as one set. Do four sets, with 2 minutes of rest between sets.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[HANGBOARD])
                .setReps("4")
                .setRest("Rest 30 seconds between reps, and 2 minutes between sets.")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardTwo(){
        Exercise e = new Exercise()
                .setName("10s Hangboard Repeaters 2")
                .setDesc("Do 10 second hangs on a medium edge, with a 20 second rest, five times as one set. Do four sets, with 1.5 minutes of rest between sets.")
                .setType(types[STRENGTH])
                .setSets("4")
                .setEquip(equip[HANGBOARD])
                .setReps("5")
                .setRest("Rest 20 seconds between reps, and 1.5 minutes between sets.")
                .setDiff(grades[TWELVE])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardThree(){
        Exercise e = new Exercise()
                .setName("10s Hangboard Repeaters 3")
                .setDesc("Do 10 second hangs on a small edge, with a 10 second rest, six times as one set. Do four sets, with 1 minute of rest between sets.")
                .setType(types[STRENGTH])
                .setSets("4")
                .setEquip(equip[HANGBOARD])
                .setReps("6")
                .setRest("Rest 10 seconds between reps, and 1 minute between sets.")
                .setDiff(grades[THIRTEEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardFour(){
        Exercise e = new Exercise()
                .setName("10s Hangboard Repeaters 4")
                .setDesc("Do 10 second hangs on a [small edge, 2 finger pocket, small edge, then sloper], with a 10 second rest, six times as one set. Do four sets, with 1 minute of rest between sets.")
                .setType(types[STRENGTH])
                .setSets("4")
                .setEquip(equip[HANGBOARD])
                .setReps("6")
                .setRest("Rest 20 seconds between reps, and 2 minutes between sets.")
                .setDiff(grades[FOURTEEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardMetoliusOne(){
        Exercise e = new Exercise()
                .setName("Metolius Climbing Beginner Hangboard")
                .setDesc("1st minute: 15s hang, Jug: \n\n" +
                        "2nd minute: 1 pull-up, Round Sloper: \n\n" +
                        "3rd minute: 10s hang, Medium Edge: \n\n" +
                        "4th minute: 15s hang w/ 3 shrugs, Pocket: \n\n" +
                        "5th minute: 20s hang w/ 2 pull-ups, Large Edge: \n\n" +
                        "6th minute: 10s hang, Round Sloper: \n\n" +
                        "5th minute: 5 knee raises, Pocket: \n\n" +
                        "7th minute: 4 pull-ups, Large Edge: \n\n" +
                        "8th minute: 10s hang, Medium Edge: \n\n" +
                        "9th minute: 3 pull-ups, Jug: \n\n" +
                        "10th minute: Hang as long as you can, Round Sloper")
                .setType(types[STRENGTH])
                .setSets("1")
                .setEquip(equip[HANGBOARD])
                .setReps("10")
                .setRest("Rest for the remaining time until the next minute after the activity described.")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardMetoliusTwo(){
        Exercise e = new Exercise()
                .setName("Metolius Climbing Intermediate Hangboard")
                .setDesc("1st minute: 15s hang, 3 pull-ups, Large Edge: \n\n" +
                        "2nd minute: 2 pull ups, Round Sloper + 20 s hang, Medium Edge: \n\n" +
                        "3rd minute: 20s hang, Small Edge + 15s 90º bent arm hang, Pocket: \n\n" +
                        "4th minute: 30s hang, Round Sloper: \n\n" +
                        "5th minute: 20s hang, Large Edge + 4 pull-ups, Pocket: \n\n" +
                        "6th minute: 3 offset pulls each arm (high arm jug, low arm small hold): \n\n" +
                        "7th minute: 15 knee raises, Jug+ 15s hang, Medium Edge: \n\n" +
                        "8th minute: 25s hang, Medium Edge: \n\n" +
                        "9th minute: 15s hang, Sloper + 3 pull-ups, Jug: \n\n" +
                        "10th minute: Hang as long as you can, Round Sloper")
                .setType(types[STRENGTH])
                .setSets("1")
                .setEquip(equip[HANGBOARD])
                .setReps("10")
                .setRest("Rest for the remaining time until the next minute after the activity described.")
                .setDiff(grades[TWELVE])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardMetoliusThree(){
        Exercise e = new Exercise()
                .setName("Metolius Climbing Advanced Hangboard")
                .setDesc("1st minute: 20 seconds straight arm hang, Large Sloper + 3 pull-ups, 4-Finger Flat Edge: \n\n" +
                        "2nd minute: 20 seconds slightly bent arm hang, Large Slope, stay on 20 seconds L-sit or 20 hanging knee curls: \n\n" +
                        "3rd minute: 5 pull-ups, 3-Finger Pocket, stay on 25 seconds straight arm hang: \n\n" +
                        "4th minute: Use every hold starting at the 3-Finger Pocket and working up, staying on each for 5 seconds (don't get off to change holds). Finish on Large Sloper with a 20 second hang: \n\n" +
                        "5th minute: 20 seconds single arm hang, Four-Finger Flat Edge. Switch hands and repeat: \n\n" +
                        "6th minute: 5 offset pull ups, Large Slope (top hand) 3-Finger Pocket (bottom hand). Change hands and repeat: \n\n" +
                        "7th minute: 30 seconds 90 degree bent arm hang, Four-Finger Incut Edge + 15 seconds straight arm hang, 3 Finger Pocket: \n\n" +
                        "8th minute: 3 L-sit pull-ups (bend knees if you have to) + 5 seconds front lever or 15 seconds straight arm hang, Large Sloper + 3 power pull-ups (use weights or helper for resistance, should just be able to do 3 pulls): \n\n" +
                        "10th minute: maximum slightly bent arm hang, Large Slope (go to failure) no rest + maximum straight arm hang - Large Sloper")
                .setType(types[STRENGTH])
                .setSets("1")
                .setEquip(equip[HANGBOARD])
                .setReps("10")
                .setRest("Rest for the remaining time until the next minute after the activity described.")
                .setDiff(grades[THIRTEEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardDWoods(){
        Exercise e = new Exercise()
                .setName("Daniel Woods 10x10 Hangboard")
                .setDesc("NOTE: This hangboard routine is especially hard. Use assistance such as foot in a theraband or a toe on the floor as needed. \n\n" +
                        "7 Times Through \n" +
                        "10 Sec Hang Index/Middle Finger \n" +
                        "10 Sec Rest \n" +
                        "Then immediately…\n" +
                        "\n" +
                        "7 Times Through \n" +
                        "10 Sec Hang Middle/Ring Finger \n" +
                        "10 Sec Rest \n" +
                        "Then immediately…\n" +
                        "\n" +
                        "7 Times Through \n" +
                        "10 Sec Hang Ring/Pinkie Finger \n" +
                        "10 Sec Rest \n" +
                        "Then immediately…\n" +
                        "\n" +
                        "7 Times Through \n" +
                        "10 Sec Hang All 4 Fingers")
                .setType(types[STRENGTH])
                .setSets("4")
                .setEquip(equip[HANGBOARD])
                .setReps("7")
                .setRest("Rest 10s between hangs throughout. ALL rests are 10s in this routine.")
                .setDiff(grades[THIRTEEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardREIcoop(){
        Exercise e = new Exercise()
                .setName("Open-Handed Moderate Hangboarding Routine")
                .setDesc("1. Using an open-handed grip, grab a matched pair of holds, using all four fingers. Hang for 10 to 15 seconds. If you can hang for more than 15 seconds, use smaller holds; if less, use bigger holds.\n" +
                        "\n" +
                        "2. Rest one minute after each hang, and then hang again. Four hangs equals one set.\n" +
                        "\n" +
                        "3. Rest five minutes and do another set of hangs on the same holds. Do four sets.")
                .setType(types[STRENGTH])
                .setSets("4")
                .setEquip(equip[HANGBOARD])
                .setReps("4")
                .setRest("Rest for the remaining time until the next minute after the activity described.")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertThreeHundredAbs(){
        Exercise e = new Exercise()
                .setName("300 Abs")
                .setDesc("30 Crunches\n" +
                        "20 Bicycles Crunches\n" +
                        "30 Toe Touches\n" +
                        "20 Reverse Crunches\n" +
                        "15 Side Plank Hip Lifts (each side)\n" +
                        "30 Crunches\n" +
                        "40 Russian Twists\n" +
                        "30 Bicycle Crunches\n" +
                        "15 Oblique V-Ups (each side)\n" +
                        "10 Leg Lifts\n" +
                        "20 Reverse Crunches")
                .setType(types[CONDITIONING])
                .setSets("1")
                .setEquip(none)
                .setReps("300")
                .setRest("Rest minimally throughout. Ideally zero rest.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertRotatorCuff(){
        Exercise e = new Exercise()
                .setName("Rotator Cuff Physio")
                .setDesc("Stand with your back to the wall, elbow touching your side. Bend your arm at 90 degrees, holding a very light freeweight in front of you. Rotate your arm away from your body, keeping your elbow touching your side. Do 15 twice on each arm.\n\n" +
                        "Hold a light freewight in both hands, standing straight up with your hands resting by your hips. With straight arms and good posture, raise the weights until your arms are outstretched horizontally at 90 degrees (T-pose). Do 15 twice on each arm.\n\n" +
                        "Hold a light freewight in both hands, standing straight up with your arms out horizontally, and your elbow at 90 degrees. Raise the weights above your head until you are straight armed with the weights touching. Do 15 twice on each arm.")
                .setType(types[CONDITIONING])
                .setSets("6")
                .setEquip(equip[FREEWEIGHTS])
                .setReps("15")
                .setRest("Minimal")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertThreesAdvanced(){
        Exercise e = new Exercise()
                .setName("Triple Threes Advanced")
                .setDesc("Do three pull ups, three front leavers, and three muscle ups as a set. Do three sets.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("9")
                .setRest("Rest three minutes between sets. Do not let go of the bar between during a set.")
                .setDiff(grades[THIRTEEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertThreesBeginner(){
        Exercise e = new Exercise()
                .setName("Triple Threes Beginner")
                .setDesc("Do three pull ups, three leg lifts, and three more pull ups. Do three sets.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("9")
                .setRest("Rest three minutes between sets. Do not let go of the bar between during a set.")
                .setDiff(grades[TEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertThreesInt(){
        Exercise e = new Exercise()
                .setName("Triple Threes Intermediate")
                .setDesc("Do three pull ups, three modified front leavers (knees bent at 90 degrees), and three more pull ups. Do three sets.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("9")
                .setRest("Rest three minutes between sets. Do not let go of the bar between during a set.")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertTherabandPulls(){
        Exercise e = new Exercise()
                .setName("Theraband Conditioning")
                .setDesc("Attach a theraband to something stable at chest height. Grab the theraband with your palm down, and take a step back to get tension in the theraband. Pull the theraband towards your chest slowly, with your shoulders square facing the pole. Hold for 5 seconds and release slowly. Do this 5 times on each arm.\n" +
                        "\n" +
                        "Attach a theraband to something stable above head height. Step back until the theraband is under tension, at approximately a 45 degree angle. Pull the theraband towards your chest slowly, with your shoulders square facing the pole. Hold for 5 seconds and release slowly. Do this 5 times on each arm.\n" +
                        "\n" +
                        "With the theraband still above head height, take a knee directly under where it is attached. Pull the theraband straight down vertically, towards your hip. Hold for 5 seconds at belly button height and release slowly. Do this 5 times on each arm.\n" +
                        "\n")
                .setType(types[CONDITIONING])
                .setSets("3")
                .setEquip(equip[THERABANDS])
                .setReps("5")
                .setRest("Rest one and a half minutes between sets.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertEightMinCore(){
        Exercise e = new Exercise()
                .setName("Eight Minute Core Routine")
                .setDesc("Do each exercise for 45s, then rest for 15s:\n" +
                        "Sit Ups\n" +
                        "Plank/Front Support\n" +
                        "Push ups\n" +
                        "Side plank Left\n" +
                        "Side Plank Right\n" +
                        "Leg Lifts\n" +
                        "V-Sits\n" +
                        "Mountain Climbers")
                .setType(types[CONDITIONING])
                .setSets("8")
                .setEquip(equip[PULLUPBAR])
                .setReps("45s")
                .setRest("Rest 15 seconds between actvities.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertVolumeBoulderPyramid(){
        Exercise e = new Exercise()
                .setName("Volume Bouldering Pyramid")
                .setDesc("Choose 3 boulders: one is very easy, one is easy, one is moderate. Climb the first 8 times, the second 4 times, and the last twice. Then the second 4 more times, and the first 8 more times.")
                .setType(types[VOLUME])
                .setSets("5")
                .setEquip(none)
                .setReps("8, 4, 2, 4, 8")
                .setRest("Rest no more than 20 seconds between attempts and boulders. If you are getting pumped or falling, choose easier boulders.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void insertVolumeBoulderUpDown(){
        Exercise e = new Exercise()
                .setName("Volume Bouldering Up/Down")
                .setDesc("Choose 5 boulders: All easy. Climb each up AND down 5 times.")
                .setType(types[VOLUME])
                .setSets("5")
                .setEquip(none)
                .setReps("5 up+down")
                .setRest("Rest no more than 20 seconds between attempts and boulders. If you are getting pumped or falling, choose easier boulders.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void insertVolumeStickGame() {
        Exercise e = new Exercise()
                .setName("Volume Circuits")
                .setDesc("Do three 60-move circuits. If your gym has graded circuits, choose one which doesn't get you pumped. If not, either climb for 60 moves or have a partner choose a path with a stick brush. Do not choose moves which result in a pump or a fall.")
                .setType(types[VOLUME])
                .setSets("3")
                .setEquip(equip[FILLWALL])
                .setReps("60 Moves")
                .setRest("Rest equal to the time you spent on the wall. Resting a couple minutes more is ok if you feel the need. If climbing with a partner, simply alternate.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void insertTimeOnRoutes(){
        Exercise e = new Exercise()
                .setName("Time on the Wall - Lead/TR")
                .setDesc("Choose a route which doesn't get you pumped or normally cause a fall. Lap it continuously for 6 minutes.")
                .setType(types[VOLUME])
                .setSets("2")
                .setEquip(equip[LEADTR])
                .setReps("N/A")
                .setRest("Rest for 10-15 minutes between sets.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void insertTimeOnBoulders(){
        Exercise e = new Exercise()
                .setName("Time on the Wall - Boulders")
                .setDesc("If your gym has graded circuits, choose one which doesn't get you pumped. If not, either climb randomly or have a partner choose a path with a stick brush. Climb continuously for 6 minutes.")
                .setType(types[VOLUME])
                .setSets("2")
                .setEquip(none)
                .setReps("N/A")
                .setRest("Rest for 10-15 minutes between sets.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void insertPowerProject(){
        Exercise e = new Exercise()
                .setName("Power Move Projects")
                .setDesc("Find or set a massive dynamic move, really close to your limit. Project it for 10 minutes. Do this four times (twice moving with each arm).")
                .setType(types[POWER])
                .setSets("4")
                .setEquip(none)
                .setReps("N/A")
                .setRest("Rest fully between attempts.")
                .setDiff(ANY)
                .setTime(times[FOUTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertMaxThrow(){
        Exercise e = new Exercise()
                .setName("Power Movement Generation")
                .setDesc("Find or set a body position which is hard to generate dynamically from. Choose a direction to move, and attempt to touch the wall as far away as you can in that direction (you will fall after tagging the wall).\n\nDo not aim for a specific hold, measure progress by improving the furthest distance you can touch on the wall. Find two moves for each arm.")
                .setType(types[POWER])
                .setSets("4")
                .setEquip(none)
                .setReps("N/A")
                .setRest("Rest fully between attempts.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void insertLockUps(){
        Exercise e = new Exercise()
                .setName("Lock-Ups")
                .setDesc("The starting position for a rep is fully locked off on a bar. By kipping with your knees and core, move dynamically above the bar. Attempt to get the bar down to chest or even belly button height. Catch yourself on the way down at fully locked off again.")
                .setType(types[POWER])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("5")
                .setRest("Rest 2 minutes between sets")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertDyno(){
        Exercise e = new Exercise()
                .setName("Dyno Projects")
                .setDesc("Project dyno's for 15 minutes. Find dyno's which require your upper body to generate, opposed to a run-jump competition style dyno on volumes.")
                .setType(types[POWER])
                .setSets("1")
                .setEquip(none)
                .setReps("N/A")
                .setRest("Rest fully between attempts.")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertSwingControl(){
        Exercise e = new Exercise()
                .setName("Swing Control")
                .setDesc("Jump to holds off of the ground which are slightly out of reach, on a relatively steep wall (30-50 degrees). Choose holds which are difficult to grab, and attempt to control the swing jumping to them creates. Find three of these, and try them each for 5 minutes.\n\nProgressively stand closer to the wall, jumping at a larger angle for a larger swing.")
                .setType(types[POWER])
                .setSets("1")
                .setEquip(none)
                .setReps("N/A")
                .setRest("Rest fully between attempts.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertTimeOnEnduranceBoulders(){
        Exercise e = new Exercise()
                .setName("Endurance Time on the Wall - Boulders")
                .setDesc("If your gym has graded circuits, choose one which gets you pumped but you can succeed on. If not, either climb randomly or have a partner choose a path with a stick brush. Climb continuously for 5 minutes on pumpy terrain.\n\nIf you fall, hop right back on a jug and shake out briefly.")
                .setType(types[ENDURANCE])
                .setSets("3")
                .setEquip(none)
                .setReps("1")
                .setRest("Rest for 10-15 minutes between sets.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void insertCircuitLinkUps(){
        Exercise e = new Exercise()
                .setName("Circuit Link-Ups")
                .setDesc("Choose two circuits, one which gets you slightly pumped and one which is relatively easy. Ideally they should be close together.\n\nClimb the easier one first, and then either traverse or hop off and run to the start of the harder. Climb as far as you can into that circuit.")
                .setType(types[ENDURANCE])
                .setSets("3")
                .setEquip(equip[FILLWALL])
                .setReps("1")
                .setRest("Rest for 10-15 minutes between sets.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void insertHoverBoulderStrength(){
        Exercise e = new Exercise()
                .setName("Hover-Hands: Bouldering")
                .setDesc("Choose four boulders. Avoid boulders with moves that are extremely dynamic. When climbing, touch the bolt-hole of each hold before you grab it for 3 seconds. If it's too far, lock-off and point in the holds direction and hold that position before moving to it normally. Climb each boulder 3 times.")
                .setType(types[STRENGTH])
                .setSets("4")
                .setEquip(none)
                .setReps("3")
                .setRest("Rest 5 minutes between boulders. Rest 1 minute between attempts.")
                .setDiff(ANY)
                .setTime(times[FOUTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertHoverBoulderEndurance(){
        Exercise e = new Exercise()
                .setName("Hover-Hands: Endurance Circuits")
                .setDesc("Choose a circuit which is well below your redpoint level. When climbing, touch the bolt-hole of each hold before you grab it for 3 seconds. If it's too far, lock-off and point in the holds direction and hold that position before moving to it normally. Climb the circuit 3 times.")
                .setType(types[ENDURANCE])
                .setSets("3")
                .setEquip(equip[FILLWALL])
                .setReps("1")
                .setRest("Rest 5 minutes between sets.")
                .setDiff(ANY)
                .setTime(times[FOUTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertKettlebells(){
        Exercise e = new Exercise()
                .setName("Kettlebells for Shoulders")
                .setDesc("1. Stand straight up, with a kettle bell close to your shoulder in front of you. Raise the kettlebell to a straight arm and overhead position. Hold it there for 60 seconds. Your the wrist and arm should be kept dead straight, the shoulder, back and down, in its socket.\n\n" +
                        "2. Do the same lift, but add a squat. Start from crouched with your back straight, kettlebell at your shoulder. End standing up, with the kettlebell overhead. Exaggerate the movement, and do it dynamically. Hold it there for 60 seconds.\n\n" +
                        "Repeat both steps 1 and 2 for two reps on each arm, for a total of 8 minutes of holding the overhead position. Rest 45 seconds between reps.")
                .setType(types[CONDITIONING])
                .setSets("1")
                .setReps("3")
                .setEquip(equip[KETTLEBELLS])
                .setRest("Rest 45 seconds between reps.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertAngleCircuit(){
        Exercise e = new Exercise()
                .setName("Progressive Circuit Endurance")
                .setDesc("Find or create an approximately 30 move circuit on an angle-adjustable wall. The circuit should be relatively easy at 20 degrees. Climb it three times, starting at 20 degrees. Each lap, increase the steepness by 10 degrees.")
                .setType(types[ENDURANCE])
                .setSets("1")
                .setEquip(equip[ANGLEADJUSTABLEWALL])
                .setReps("3")
                .setRest("Rest twice the time you were on the wall in-between each circuit.")
                .setDiff(grades[ELEVEN])
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void insertStrengthProgressive(){
    Exercise e = new Exercise()
            .setName("Progressive Strength Boulder")
            .setDesc("Find or create a static boulder on an angle-adjustable wall. The problem should be relatively easy at 20 degrees. Climb it ten times, starting at 20 degrees. Every time you complete it, increase the steepness by 5 degrees. If you fall, do not increase the angle.")
            .setType(types[STRENGTH])
            .setSets("1")
            .setEquip(equip[ANGLEADJUSTABLEWALL])
            .setReps("3")
            .setRest("Rest three minutes between attempts.")
            .setDiff(grades[ELEVEN])
            .setTime(times[FOUTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertPowerProgressive(){
        Exercise e = new Exercise()
                .setName("Progressive Power Boulder")
                .setDesc("Find or create a powerful, dynamic boulder on an angle-adjustable wall. The problem should be relatively easy at 20 degrees. Climb it ten times, starting at 20 degrees. Every time you complete it, increase the steepness by 5 degrees. If you fall, do not increase the angle.")
                .setType(types[POWER])
                .setSets("1")
                .setEquip(equip[ANGLEADJUSTABLEWALL])
                .setReps("3")
                .setRest("Rest three minutes between attempts.")
                .setDiff(grades[ELEVEN])
                .setTime(times[FOUTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertPowerEndProgressive(){
        Exercise e = new Exercise()
                .setName("Progressive Power Endurance Boulder")
                .setDesc("Find or create a long boulder on an angle-adjustable wall. It should be around 12 moves, with the crux near the end. The problem should be relatively easy at 20 degrees. Climb it ten times, starting at 20 degrees. Every time you complete it, increase the steepness by 5 degrees. If you fall, do not increase the angle.")
                .setType(types[POWEND])
                .setSets("1")
                .setEquip(equip[ANGLEADJUSTABLEWALL])
                .setReps("3")
                .setRest("Rest three minutes between attempts.")
                .setDiff(grades[ELEVEN])
                .setTime(times[FOUTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertBoulderLinks(){
        Exercise e = new Exercise()
                .setName("Boulder Linkups")
                .setDesc("Find a boulder which is moderate. Look for an easier climb which finishes in a similar area. Climb the moderate boulder, then traverse to the finish of the easier boulder and downclimb it. If possible, traverse back into the start of the moderate boulder. Or, run from the start of one to the other. Climb the moderate boulder again.\n\n" +
                        "Do this four times, ideally on different boulders. However it can be hard to find suitable boulders, so repeating the same set is ok if it's easier.")
                .setType(types[POWEND])
                .setSets("3")
                .setEquip(none)
                .setReps("1")
                .setRest("Rest four minutes between attempts.")
                .setDiff(ANY)
                .setTime(times[FOUTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertUpDownLaps(){
        Exercise e = new Exercise()
                .setName("Up/Down Boulder Laps")
                .setDesc("Find a boulder which you can downclimb (avoid massive moves). Climb up the first move, then down to the start. Then to the second move, and back down to the start. Repeat this process until you get to the finish, then downclimb to the start.\n\n" +
                        "Repeat this on five boulders.")
                .setType(types[POWEND])
                .setSets("5")
                .setEquip(none)
                .setReps("1")
                .setRest("Rest five minutes between boulders.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void campusBoardPowerOne(){
        Exercise e = new Exercise()
                .setName("Power on Campus Board One")
                .setDesc("On the largest rungs, complete 10 reps of the following. Then rest, and repeat on the other arm. Rest, and move to the next step.\n\n" +
                        "1. Start matched on rung 1. Pull up and skip a rung to rung 3. Go back down to rung 1. Repeat.\n\n" +
                        "2. Start matched on rung 1. Dynamically, pop both hands up to rung 2. Pop back down to rung 1. Repeat.\n\n" +
                        "3. Start matched on rung 1. Move a hand to rung 2, then bump to rung 3. Come back to rung 2, then to the start. Repeat.\n\n" +
                        "4. Start with one hand on rung 1 and one hand on rung 2. The higher hand goes to rung 3, then back down to rung 2. Repeat.\n\n" +
                        "5. Start with one hand on rung 1 and one hand on rung 2. Pop dynamically such that both hands go up a rung. Pop down to the starting position. Repeat.")
                .setType(types[POWER])
                .setSets("10")
                .setEquip(equip[CAMPUSBOARD])
                .setReps("10")
                .setRest("Rest one minute before switching arms and between each step.")
                .setDiff(grades[ELEVEN])
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void campusBoardPowerTwo(){
        Exercise e = new Exercise()
                .setName("Power on Campus Board Two")
                .setDesc("On the medium rungs, complete 10 reps of the following. Then rest, and repeat on the other arm. Rest, and move to the next step.\n\n" +
                        "1. Start matched on rung 1. Pull up and skip a rung to rung 4. Go back down to rung 1. Repeat.\n\n" +
                        "2. Start matched on rung 1. Dynamically, pop both hands up to rung 3. Pop back down to rung 1. Repeat.\n\n" +
                        "3. Start matched on rung 1. Move a hand to rung 3, then bump to rung 4. Come back to rung 3, then to the start. Repeat.\n\n" +
                        "4. Start with one hand on rung 1 and one hand on rung 2. The higher hand goes to rung 4, then back down to rung 2. Repeat.\n\n" +
                        "5. Start with one hand on rung 1 and one hand on rung 2. Pop dynamically such that both hands go up two rungs. Pop down to the starting position. Repeat.")
                .setType(types[POWER])
                .setSets("10")
                .setEquip(equip[CAMPUSBOARD])
                .setReps("10")
                .setRest("Rest one minute before switching arms and between each step.")
                .setDiff(grades[TWELVE])
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void campusBoardPowerThree(){
        Exercise e = new Exercise()
                .setName("Power on Campus Board Three")
                .setDesc("On the small rungs, complete 10 reps of the following. Then rest, and repeat on the other arm. Rest, and move to the next step.\n\n" +
                        "1. Start matched on rung 1. Pull up and skip a rung to rung 4. Go back down to rung 1. Repeat.\n\n" +
                        "2. Start matched on rung 1. Dynamically, pop both hands up to rung 3. Pop back down to rung 1. Repeat.\n\n" +
                        "3. Start matched on rung 1. Move a hand to rung 3, then bump to rung 4. Come back to rung 3, then to the start. Repeat.\n\n" +
                        "4. Start with one hand on rung 1 and one hand on rung 2. The higher hand goes to rung 4, then back down to rung 2. Repeat.\n\n" +
                        "5. Start with one hand on rung 1 and one hand on rung 2. Pop dynamically such that both hands go up two rungs. Pop down to the starting position. Repeat.")
                .setType(types[POWER])
                .setSets("10")
                .setEquip(equip[CAMPUSBOARD])
                .setReps("10")
                .setRest("Rest one minute before switching arms and between each step.")
                .setDiff(grades[THIRTEEN])
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void campusBoardPowerFour(){
        Exercise e = new Exercise()
                .setName("Power on Campus Board Four")
                .setDesc("On the small rungs, complete 10 reps of the following. Then rest, and repeat on the other arm. Rest, and move to the next step.\n\n" +
                        "1. Start matched on rung 1. Pull up and skip a rung to rung 5. Go back down to rung 1. Repeat.\n\n" +
                        "2. Start matched on rung 1. Dynamically, pop both hands up to rung 4. Pop back down to rung 1. Repeat.\n\n" +
                        "3. Start matched on rung 1. Move a hand to rung 3, then bump to rung 5. Come back to rung 3, then to the start. Repeat.\n\n" +
                        "4. Start with one hand on rung 1 and one hand on rung 2. The higher hand goes to rung 5, then back down to rung 3. Repeat.\n\n" +
                        "5. Start with one hand on rung 1 and one hand on rung 2. Pop dynamically such that both hands go up two rungs. Pop down to the starting position. Repeat.")
                .setType(types[POWER])
                .setSets("10")
                .setEquip(equip[CAMPUSBOARD])
                .setReps("10")
                .setRest("Rest one minute before switching arms and between each step.")
                .setDiff(grades[FOURTEEN])
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void campusBoardStrOne(){
        Exercise e = new Exercise()
                .setName("Strength on Campus Board One")
                .setDesc("On the largest rungs, move to the top of the campus board with the following sequences. Drop down from the top. Do each step twice. Start with the opposite arm on the second go.\n\n" +
                        "1. Start matched on rung 1. Pull up to rung 2 with your left hand, and match it. Pull up now with your right hand to 3, and match it.\n\n" +
                        "2. Start matched on rung 1. Alternate moving up, without matching or skipping rungs.\n\n" +
                        "3. Start matched on rung 1. Move your right hand to rung 3. Match it. Then your left goes to 5. Match and repeat.\n\n" +
                        "4. Start with one hand on rung 1 and one hand on rung 2. The higher hand goes to rung 3, then the lower goes up to 2. The higher up to 4, and so on.\n\n" +
                        "5. Start matched on rung 1. Alternate moving up, without matching or skipping rungs.")
                .setType(types[POWER])
                .setSets("10")
                .setEquip(equip[CAMPUSBOARD])
                .setReps("To the top")
                .setRest("Rest one minute before switching arms and between each step.")
                .setDiff(grades[ELEVEN])
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void campusBoardStrTwo(){
        Exercise e = new Exercise()
                .setName("Strength on Campus Board One")
                .setDesc("On the medium rungs, move to the top of the campus board with the following sequences. Drop down from the top. Do each step twice. Start with the opposite arm on the second go.\n\n" +
                        "1. Start matched on rung 1. Pull up to rung 2 with your left hand, and match it. Pull up now with your right hand to 3, and match it.\n\n" +
                        "2. Start matched on rung 1. Alternate moving up, without matching or skipping rungs.\n\n" +
                        "3. Start matched on rung 1. Move your right hand to rung 3. Match it. Then your left goes to 5. Match and repeat.\n\n" +
                        "4. Start with one hand on rung 1 and one hand on rung 2. The higher hand goes to rung 3, then the lower goes up to 2. The higher up to 4, and so on.\n\n" +
                        "5. Start matched on rung 1. Alternate moving up, without matching or skipping rungs.")
                .setType(types[POWER])
                .setSets("10")
                .setEquip(equip[CAMPUSBOARD])
                .setReps("To the top")
                .setRest("Rest one minute before switching arms and between each step.")
                .setDiff(grades[TWELVE])
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void campusBoardStrThree(){
        Exercise e = new Exercise()
                .setName("Strength on Campus Board Three")
                .setDesc("On the medium rungs, move to the top of the campus board with the following sequences. Drop down from the top. Do each step twice. Start with the opposite arm on the second go.\n\n" +
                        "1. Start matched on rung 1. Pull up to rung 3 with your left hand, and match it. Pull up now with your right hand to 5, and match it.\n\n" +
                        "2. Start matched on rung 1. Alternate moving up, skipping 1 rung with no matching.\n\n" +
                        "3. Start matched on rung 1. Move your right hand to rung 4. Match it. Then your left goes to 7. Match and repeat.\n\n" +
                        "4. Start with one hand on rung 1 and one hand on rung 2. The higher hand goes to rung 5, then the lower goes up to 4. The higher up to 7, and so on.\n\n" +
                        "5. Start matched on rung 1. Alternate moving up, without matching or skipping rungs.")
                .setType(types[POWER])
                .setSets("10")
                .setEquip(equip[CAMPUSBOARD])
                .setReps("To the top")
                .setRest("Rest one minute before switching arms and between each step.")
                .setDiff(grades[THIRTEEN])
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void campusBoardStrFour(){
        Exercise e = new Exercise()
                .setName("Strength on Campus Board Four")
                .setDesc("On the small rungs, move to the top of the campus board with the following sequences. Drop down from the top. Do each step twice. Start with the opposite arm on the second go.\n\n" +
                        "1. Start matched on rung 1. Pull up to rung 3 with your left hand, and match it. Pull up now with your right hand to 5, and match it.\n\n" +
                        "2. Start matched on rung 1. Alternate moving up, skipping 1 rung with no matching.\n\n" +
                        "3. Start matched on rung 1. Move your right hand to rung 4. Match it. Then your left goes to 7. Match and repeat.\n\n" +
                        "4. Start with one hand on rung 1 and one hand on rung 2. The higher hand goes to rung 5, then the lower goes up to 4. The higher up to 7, and so on.\n\n" +
                        "5. Start matched on rung 1. Alternate moving up, without matching or skipping rungs.")
                .setType(types[POWER])
                .setSets("10")
                .setEquip(equip[CAMPUSBOARD])
                .setReps("To the top")
                .setRest("Rest one minute before switching arms and between each step.")
                .setDiff(grades[FOURTEEN])
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void insertFrenchiesOne(){
        Exercise e = new Exercise()
                .setName("Frenchies One")
                .setDesc("")
                .setType(types[STRENGTH])
                .setSets("")
                .setEquip(equip[PULLUPBAR])
                .setReps("")
                .setRest("")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

}

/*

    void insert(){
        Exercise e = new Exercise()
                .setName()
                .setDesc()
                .setType()
                .setSets()
                .setEquip()
                .setReps()
                .setRest()
                .setDiff()
                .setTime();
        db.insertExercise(e);
    }

 */
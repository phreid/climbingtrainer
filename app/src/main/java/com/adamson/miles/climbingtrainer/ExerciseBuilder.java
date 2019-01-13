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
    public static int PULLUPBAR = 0;
    public static int HANGBOARD = 1;
    public static int CAMPUSBOARD = 2;
    public static int LEADTR = 3;
    public static int FREEWEIGHTS = 4;
    public static int KETTLEBELLS = 5;
    public static int RINGS = 6;
    public static int MOONBOARD = 7;
    public static int MEDICINEBALLS = 8;
    public static int THERABANDS = 9;
    public static int ANGLEADJUSTABLEWALL = 10;
    public static String NONE = "NONE";

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
    public static int FORTYFIVE_M = 2;
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
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest if you feel like you are getting pumped during a stage you should not be.")
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
        insertHangboardREIcoop();
        insertThreeHundredAbs();
        insertRotatorCuff();
        insertThreesOne();
        insertThreesTwo();
        insertThreesThree();
        insertThreesFour();
        insertThreesFive();
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
        insertFrenchiesOne();
        insertFrenchiesTwo();
        insertFrenchiesThree();
        insertFrenchiesFour();
        insertFrenchiesFive();
        insertFastPullUpsOne();
        insertFastPullUpsTwo();
        insertFastPullUpsThree();
        insertFastPullUpsFour();
        insertFastPullUpsFive();
        insertPushUpPyramid();
        staticExaggeration();
        powerExaggeration();
        skipMoves();
        fingerWalk();
        powerProjects();
        staticProjects();
        insertHangboardTenOne();
        insertHangboardTenTwo();
        insertHangboardTenThree();
        insertHangboardTenFour();
        insertARCthirty();
        insertARCfortyfive();
        insertARCsixty();
        insertRestEffectiveness();
        insertOneEnduranceIntervals();
        insertTwoEnduranceIntervals();
        insertRopedEnduranceIntervals();
        insertDoubleLapProject();
        insertRestOnRoute();
        insertRestOnCircuit();
        powerPullUps();
        campusProject();
        oneFiveNine();
        limitPowerBoulders();
        limitStaticBoulders();
        longCampusProject();
        longBoulderProject();
        enduranceIntervals();
        hangboardIntroduction();
        rings();
        hangboardIntroductionTwo();
        medBall();
        strengthPyramid();
        maxLock();
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
                .setTime(times[FORTYFIVE_M])
                .setRest("Rest completely between attempts.")
                .setDiff(ANY);
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
                .setTime(times[FORTYFIVE_M])
                .setRest("Rest completely between attempts.")
                .setDiff(ANY);
        db.insertExercise(e);
    }

    void insertBoulderLink(){
        Exercise e = new Exercise()
                .setName("Traverse into a Boulder")
                .setDesc("Set or choose a four to six move problem which is near your redpoint level. Then, set a ten to fifteen move traverse into the starting holds. The traverse can have open feet, the boulder should have set feet. Attempt to link the traverse into the boulder four times.")
                .setType(types[POWEND])
                .setSets("1")
                .setEquip(NONE)
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

    void insertHangboardMetoliusFour(){
        Exercise e = new Exercise()
                .setName("Metolius Climbing Expert Hangboard")
                .setDesc("1st minute: 20 seconds straight arm hang, Medium Sloper + 3 pull-ups, 4-Finger Small Edge: \n\n" +
                        "2nd minute: 20 seconds slightly bent arm hang, Medium Sloper, stay on 30 seconds L-sit or 20 hanging knee curls: \n\n" +
                        "3rd minute: 6 pull-ups, 2-Finger Pocket, stay on 25 seconds straight arm hang: \n\n" +
                        "4th minute: Use every hold starting at the 2-Finger Pocket and working up, staying on each for 5 seconds (don't get off to change holds). Finish on Medium Sloper with a 30 second hang: \n\n" +
                        "5th minute: 40 seconds single arm hang, Four-Finger Medium Edge. Switch hands and repeat: \n\n" +
                        "6th minute: 8 offset pull ups, Medium Slope (top hand) 2-Finger Pocket (bottom hand). Change hands and repeat: \n\n" +
                        "7th minute: 40 seconds 90 degree bent arm hang, Four-Finger Incut Edge + 15 seconds straight arm hang, 2 Finger Pocket: \n\n" +
                        "8th minute: 5 L-sit pull-ups + 5 seconds front lever or 20 seconds straight arm hang, Medium Sloper + 5 power pull-ups (use weights or helper for resistance, should just be able to do 3 pulls): \n\n" +
                        "10th minute: maximum slightly bent arm hang, Medium Slope (go to failure) no rest + maximum straight arm hang - Medium Sloper")
                .setType(types[STRENGTH])
                .setSets("1")
                .setEquip(equip[HANGBOARD])
                .setReps("10")
                .setRest("Rest for the remaining time until the next minute after the activity described.")
                .setDiff(grades[FOURTEEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardTenOne(){
        Exercise e = new Exercise()
                .setName("5x10 Hangboard One")
                .setDesc("NOTE: This hangboard routine is especially hard. Use assistance such as foot in a theraband or a toe on the floor as needed. \n\n" +
                        "7 Times Through \n" +
                        "5 Sec Hang Edge \n" +
                        "15 Sec Rest \n" +
                        "Then immediately…\n" +
                        "\n" +
                        "7 Times Through \n" +
                        "5 Sec Hang 3 Finger Pocket \n" +
                        "15 Sec Rest \n" +
                        "Then immediately…\n" +
                        "\n" +
                        "7 Times Through \n" +
                        "5 Sec Hang Edge \n" +
                        "15 Sec Rest \n" +
                        "Then immediately…\n" +
                        "\n" +
                        "7 Times Through \n" +
                        "10 Sec Hang Sloper")
                .setType(types[STRENGTH])
                .setSets("4")
                .setEquip(equip[HANGBOARD])
                .setReps("7")
                .setRest("Rest 15s between hangs throughout. ALL rests are 15s in this routine.")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardTenTwo(){
        Exercise e = new Exercise()
                .setName("5x10 Hangboard Two")
                .setDesc("NOTE: This hangboard routine is especially hard. Use assistance such as foot in a theraband or a toe on the floor as needed. \n\n" +
                        "7 Times Through \n" +
                        "5 Sec Hang Index/Middle Finger \n" +
                        "15 Sec Rest \n" +
                        "Then immediately…\n" +
                        "\n" +
                        "7 Times Through \n" +
                        "5 Sec Hang Middle/Ring Finger \n" +
                        "15 Sec Rest \n" +
                        "Then immediately…\n" +
                        "\n" +
                        "7 Times Through \n" +
                        "5 Sec Hang Ring/Pinkie Finger \n" +
                        "15 Sec Rest \n" +
                        "Then immediately…\n" +
                        "\n" +
                        "7 Times Through \n" +
                        "10 Sec Hang All 4 Fingers")
                .setType(types[STRENGTH])
                .setSets("4")
                .setEquip(equip[HANGBOARD])
                .setReps("7")
                .setRest("Rest 15s between hangs throughout. ALL rests are 15s in this routine.")
                .setDiff(grades[TWELVE])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertHangboardTenThree(){
        Exercise e = new Exercise()
                .setName("10x10 Hangboard One")
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
                        "10 Sec Hang Small Edge \n" +
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

    void insertHangboardTenFour(){
        Exercise e = new Exercise()
                .setName("10x10 Hangboard Two")
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
                .setDiff(grades[FOURTEEN])
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
                .setEquip(NONE)
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

    void insertThreesOne(){
        Exercise e = new Exercise()
                .setName("Triple Threes One")
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

    void insertThreesTwo(){
        Exercise e = new Exercise()
                .setName("Triple Threes Two")
                .setDesc("Do three pull ups, three pull ups with a simultaneous leg lift (legs out to 90 degrees), and three more pull ups. Do three sets.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("9")
                .setRest("Rest three minutes between sets. Do not let go of the bar between during a set.")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertThreesThree(){
        Exercise e = new Exercise()
                .setName("Triple Threes Stage Three")
                .setDesc("Do three pull ups, three modified front leavers (knees bent at 90 degrees), and three more pull ups. Do three sets.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("9")
                .setRest("Rest three minutes between sets. Do not let go of the bar between during a set.")
                .setDiff(grades[TWELVE])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }


    void insertThreesFour(){
        Exercise e = new Exercise()
                .setName("Triple Threes Four")
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

    void insertThreesFive(){
        Exercise e = new Exercise()
                .setName("Triple Threes Five")
                .setDesc("Do three muscle ups, three front leavers, and three more muscle ups as a set. Do three sets.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("9")
                .setRest("Rest three minutes between sets. Do not let go of the bar between during a set.")
                .setDiff(grades[THIRTEEN])
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
                .setEquip(NONE)
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
                .setEquip(NONE)
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
                .setEquip(NONE)
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
                .setEquip(NONE)
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
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest fully between attempts.")
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertMaxThrow(){
        Exercise e = new Exercise()
                .setName("Power Movement Generation")
                .setDesc("Find or set a body position which is hard to generate dynamically from. Choose a direction to move, and attempt to touch the wall as far away as you can in that direction (you will fall after tagging the wall).\n\nDo not aim for a specific hold, measure progress by improving the furthest distance you can touch on the wall. Find two moves for each arm.")
                .setType(types[POWER])
                .setSets("4")
                .setEquip(NONE)
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
                .setEquip(NONE)
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
                .setEquip(NONE)
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
                .setEquip(NONE)
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
                .setEquip(NONE)
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
                .setEquip(NONE)
                .setReps("3")
                .setRest("Rest 5 minutes between boulders. Rest 1 minute between attempts.")
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertHoverBoulderEndurance(){
        Exercise e = new Exercise()
                .setName("Hover-Hands: Endurance Circuits")
                .setDesc("Choose a circuit which is well below your redpoint level. When climbing, touch the bolt-hole of each hold before you grab it for 3 seconds. If it's too far, lock-off and point in the holds direction and hold that position before moving to it normally. Climb the circuit 3 times.")
                .setType(types[ENDURANCE])
                .setSets("3")
                .setEquip(NONE)
                .setReps("1")
                .setRest("Rest 5 minutes between sets.")
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
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
                .setDiff(ANY)
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
            .setDiff(ANY)
            .setTime(times[FORTYFIVE_M]);
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
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
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
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertBoulderLinks(){
        Exercise e = new Exercise()
                .setName("Boulder Linkups")
                .setDesc("Find a boulder which is moderate. Look for an easier climb which finishes in a similar area. Climb the moderate boulder, then traverse to the finish of the easier boulder and downclimb it. If possible, traverse back into the start of the moderate boulder. Or, run from the start of one to the other. Climb the moderate boulder again.\n\n" +
                        "Do this four times, ideally on different boulders. However it can be hard to find suitable boulders, so repeating the same set is ok.")
                .setType(types[POWEND])
                .setSets("3")
                .setEquip(NONE)
                .setReps("1")
                .setRest("Rest four minutes between attempts.")
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertUpDownLaps(){
        Exercise e = new Exercise()
                .setName("Up/Down Boulder Laps")
                .setDesc("Find a boulder which you can downclimb (avoid massive moves). Climb up the first move, then down to the start. Then to the second move, and back down to the start. Repeat this process until you get to the finish, then downclimb to the start.\n\n" +
                        "Repeat this on three boulders.")
                .setType(types[POWEND])
                .setSets("3")
                .setEquip(NONE)
                .setReps("1")
                .setRest("Rest five minutes between boulders.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
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
                .setDesc("From straight armed on a bar, pull up to a full lock off. Hold for 3 seconds. Lower completely. This is one rep. Do three sets of three.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("3")
                .setRest("Rest two minutes between sets")
                .setDiff(grades[TEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertFrenchiesTwo(){
        Exercise e = new Exercise()
                .setName("Frenchies Two")
                .setDesc("From straight armed on a bar, pull up to a full lock off. Hold for 3 seconds. Lower completely. Pull up fully, but this time lower to a 90 degree lock off. Hold this for 3 seconds, then lower. This is one rep. Do three sets of three.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("3")
                .setRest("Rest two minutes between sets")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertFrenchiesThree(){
        Exercise e = new Exercise()
                .setName("Frenchies Three")
                .setDesc("From straight armed on a bar, pull up fully. From here you will lower, but at every 45 degrees (full lock off, 45, 90, 135), you hold that position for 3 seconds. This is one rep. Do three sets of three.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("3")
                .setRest("Rest two minutes between sets")
                .setDiff(grades[TWELVE])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertFrenchiesFour(){
        Exercise e = new Exercise()
                .setName("Frenchies Four")
                .setDesc("From straight armed on a bar, pull up fully. From here you will lower, but at every 45 degrees (full lock off, 45, 90, 135), you hold that position for 4 seconds. This is one rep. Do three sets of three.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("3")
                .setRest("Rest two minutes between sets")
                .setDiff(grades[THIRTEEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertFrenchiesFive(){
        Exercise e = new Exercise()
                .setName("Frenchies Five")
                .setDesc("From straight armed on a bar, pull up fully. From here you will lower, but at every 45 degrees (full lock off, 45, 90, 135), you hold that position for 5 seconds. This is one rep. Do five sets of three.")
                .setType(types[STRENGTH])
                .setSets("5")
                .setEquip(equip[PULLUPBAR])
                .setReps("3")
                .setRest("Rest two minutes between sets")
                .setDiff(grades[FOURTEEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertFastPullUpsOne(){
        Exercise e = new Exercise()
                .setName("Fast Pull Ups One")
                .setDesc("Do 5 pull ups as fast as possible. Do not allow your elbows to lock or shoulders to come out of their sockets on the way down (stop and go up again just before this happens).")
                .setType(types[POWER])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("5")
                .setRest("Rest 3 minutes between sets")
                .setDiff(grades[TEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertFastPullUpsTwo(){
        Exercise e = new Exercise()
                .setName("Fast Pull Ups Two")
                .setDesc("Do 7 pull ups as fast as possible. Do not allow your elbows to lock or shoulders to come out of their sockets on the way down (stop and go up again just before this happens).")
                .setType(types[POWER])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("7")
                .setRest("Rest 3 minutes between sets")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertFastPullUpsThree(){
        Exercise e = new Exercise()
                .setName("Fast Pull Ups Three")
                .setDesc("Do 10 pull ups as fast as possible. Do not allow your elbows to lock or shoulders to come out of their sockets on the way down (stop and go up again just before this happens).")
                .setType(types[POWER])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("10")
                .setRest("Rest 3 minutes between sets")
                .setDiff(grades[TWELVE])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertFastPullUpsFour(){
        Exercise e = new Exercise()
                .setName("Fast Pull Ups Four")
                .setDesc("Do 10 pull ups as fast as possible. Add a small amount of weight, either with a weight vest or a freeweight held by your legs. Do not allow your elbows to lock or shoulders to come out of their sockets on the way down (stop and go up again just before this happens).")
                .setType(types[POWER])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("10")
                .setRest("Rest 3 minutes between sets")
                .setDiff(grades[THIRTEEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertFastPullUpsFive(){
        Exercise e = new Exercise()
                .setName("Fast Pull Ups Four")
                .setDesc("Do 10 pull ups as fast as possible. Add a moderate amount of weight, either with a weight vest or a freeweight held by your legs. Do not allow your elbows to lock or shoulders to come out of their sockets on the way down (stop and go up again just before this happens).")
                .setType(types[POWER])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("10")
                .setRest("Rest 3 minutes between sets")
                .setDiff(grades[FOURTEEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void insertPushUpPyramid(){
        Exercise e = new Exercise()
                .setName("Push Up Pyramid")
                .setDesc("10 Push Ups\n"+
                        "Rest 10 seconds\n"+
                        "9 Push-Ups\n"+
                        "Rest 9 seconds\n"+
                        "Repeat until you are at 1 push up (the last one)")
                .setType(types[CONDITIONING])
                .setSets("2")
                .setEquip(NONE)
                .setReps("55")
                .setRest("Rest 10 seconds between pyramid levels and three minutes between sets.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void powerExaggeration(){
        Exercise e = new Exercise()
                .setName("Power Exaggeration")
                .setDesc("Find four moderate boulders which are relatively steep. On each move, purposely cut your feet and hold the swing. Attempt to return your foot to a hold on the first swing back into the wall.\n\n" +
                        "Climb each boulder 2 times.")
                .setType(types[POWER])
                .setSets("4")
                .setEquip(NONE)
                .setReps("2")
                .setRest("Rest four minutes between boulders, and briefly between attempts.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void staticExaggeration(){
        Exercise e = new Exercise()
                .setName("Static Exaggeration")
                .setDesc("Find four moderate boulders which are between vertical and 30 degrees overhanging. Try to move as slowly and perfectly as possible up the problem. Attempt to move youe feet without making any noise. Try to make every move take at least 2 seconds of gradually reaching and locking off further.\n\n" +
                        "Climb each boulder 2 times.")
                .setType(types[STRENGTH])
                .setSets("4")
                .setEquip(NONE)
                .setReps("2")
                .setRest("Rest four minutes between boulders, and briefly between attempts.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void skipMoves(){
        Exercise e = new Exercise()
                .setName("Skip Moves")
                .setDesc("Find two moderate boulders. Climb them repeatedly. Every time you complete it, remove a hand hold.\n\n" +
                        "Once the boulder feels impossible (try for 15 minutes total) move to the next.")
                .setType(types[POWER])
                .setSets("2")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest completely between attempts and boulders.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void fingerWalk(){
        Exercise e = new Exercise()
                .setName("Finger Walk Boulders")
                .setDesc("Find four moderate boulders. On every move, 'walk' your fingers on the wall between the holds. Try to move very slowly and fluidly.\n\n" +
                        "Climb each boulder twice.")
                .setType(types[POWER])
                .setSets("4")
                .setEquip(NONE)
                .setReps("2")
                .setRest("Rest briefly between attempts and for three minutes between boulders.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void powerProjects(){
        Exercise e = new Exercise()
                .setName("Power Projects")
                .setDesc("Simply project a powerful and dynamic boulder. You can use this to measure progress. Once send a project, try another harder problem.")
                .setType(types[POWER])
                .setSets("N/A")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest completely between attempts.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void staticProjects(){
        Exercise e = new Exercise()
                .setName("Static Projects")
                .setDesc("Simply project a static boulder. You can use this to measure progress. Once send a project, try another harder problem.")
                .setType(types[STRENGTH])
                .setSets("N/A")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest completely between attempts.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void insertARCthirty(){
        Exercise e = new Exercise()
                .setName("30 Minute ARC Drill")
                .setDesc("Climb on a vertical wall or slight overhang for 5-10 minutes continuously, depending on your current endurance. The climbing should get you lightly pumped (fairly easy). At the end of the timer, hop off the wall and rest for 30 seconds. Then, attempt a long boulder problem which is around the grade you normally flash.\n\n" +
                        "Rest until the 15 minute mark, and repeat this again.")
                .setType(types[ENDURANCE])
                .setSets("2")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest until the 15 minute mark, after the first set.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void insertARCfortyfive(){
        Exercise e = new Exercise()
                .setName("45 Minute ARC Drill")
                .setDesc("Climb on a vertical wall or slight overhang for 5-10 minutes continuously, depending on your current endurance. The climbing should get you lightly pumped (fairly easy). At the end of the timer, hop off the wall and rest for 30 seconds. Then, attempt a long boulder problem which is around the grade you normally flash.\n\n" +
                        "Rest until the 15th minute of the drill, and repeat this again. Do 3 sets for a total of 45 minutes.")
                .setType(types[ENDURANCE])
                .setSets("4")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest with any remaining time between sets.")
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertARCsixty(){
        Exercise e = new Exercise()
                .setName("60 Minute ARC Drill")
                .setDesc("Climb on a vertical wall or slight overhang for 5-10 minutes continuously, depending on your current endurance. The climbing should get you lightly pumped (fairly easy). At the end of the timer, hop off the wall and rest for 30 seconds. Then, attempt a long boulder problem which is around the grade you normally flash.\n\n" +
                        "Rest until the 15th minute of the set, then repeat again 4 times. This makes for four sets, taking 1 hour total.")
                .setType(types[ENDURANCE])
                .setSets("4")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest with any remaining time between sets.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void insertRestEffectiveness(){
        Exercise e = new Exercise()
                .setName("Rest Effectiveness")
                .setDesc("Climb continuously until failure due to pump. Ramp the difficulty up such that you get at least 40 moves in before failure.\n\n" +
                        "Once you fail, go to a chosen rest position to attempt to recover. Shake out on it and attempt to recover as long as you can, until you fail again. Attempt to find holds to rest on which are mediocre, but you can still hold on for over a minute.\n\n" +
                        "Do this drill three times. You climb at the first, 15th and 30th minute. Rest until the next set.")
                .setType(types[ENDURANCE])
                .setSets("3")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest with any remaining time between sets.")
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertTwoEnduranceIntervals(){
        Exercise e = new Exercise()
                .setName("Two Endurance Bouldering Intervals")
                .setDesc("Climb for 1 minute on, and then rest for 1 minute. Do this 10 times, for a total of 20 minutes. Rest 10 minutes, then do it again.")
                .setType(types[ENDURANCE])
                .setSets("2")
                .setEquip(NONE)
                .setReps("10")
                .setRest("Rest 1 minute in intervals and 10 minutes between sets.")
                .setDiff(ANY)
                .setTime(times[SIXTY_M]);
        db.insertExercise(e);
    }

    void insertOneEnduranceIntervals(){
        Exercise e = new Exercise()
                .setName("One Endurance Bouldering Interval")
                .setDesc("Climb for 1 minute on, and then rest for 1 minute. Do this 12 times, for a total of 24 minutes.")
                .setType(types[ENDURANCE])
                .setSets("1")
                .setEquip(NONE)
                .setReps("12")
                .setRest("Rest 1 minute in intervals and for 6 minutes after the drill.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void insertRopedEnduranceIntervals(){
        Exercise e = new Exercise()
                .setName("Endurance Roped Interval")
                .setDesc("Climb for 5 minutes on, climbing up and down the same route. On lead, un-clip on your way down. After, belay your parner for 5 minutes as your rest. Attempt to switch quickly. Repeat this 2 times each/\n\n" +
                        "Unlike time on the wall for volume drills, the difficulty of the route should be higher and getting you extremely pumped. Swap to an easier route if you cannot climb for more than a minute without falling.")
                .setType(types[ENDURANCE])
                .setSets("2")
                .setEquip(equip[LEADTR])
                .setReps("5 min")
                .setRest("Rest 5 minutes between intervals.")
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertDoubleLapProject(){
        Exercise e = new Exercise()
                .setName("Double Lap Endurance Project")
                .setDesc("Find a route which is near your redpoint limit. Attempt to climb it twice in a row. Treat this like a project and come back to the same routefor this drill in the future (until you send).\n\n" +
                        "Attempt your project twice within 30 minutes, the remainder of the time is resting.")
                .setType(types[ENDURANCE])
                .setSets("N/A")
                .setEquip(equip[LEADTR])
                .setReps("N/A")
                .setRest("See Description")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void insertRestOnRoute(){
        Exercise e = new Exercise()
                .setName("Rest on Route")
                .setDesc("Find a route which is near your redpoint limit. Start by shaking out on the starting holds for 2 minutes before you climb. Once the time is up, attempt to redpoint the route.\n\n" +
                        "Do this three times within 45 minutes. Spread out resting evenly between attempts.")
                .setType(types[ENDURANCE])
                .setSets("N/A")
                .setEquip(equip[LEADTR])
                .setReps("N/A")
                .setRest("See Description")
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
        db.insertExercise(e);
    }

    void insertRestOnCircuit(){
        Exercise e = new Exercise()
                .setName("Rest on Circuit")
                .setDesc("Find a circuit which is near your redpoint limit. Start by shaking out on the starting holds for 2 minutes before you climb. Once the time is up, attempt to redpoint the route.\n\n" +
                        "Do this three times within 45 minutes. Spread out resting evenly between attempts.")
                .setType(types[ENDURANCE])
                .setSets("N/A")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("See Description")
                .setDiff(ANY)
                .setTime(times[FORTYFIVE_M]);
        db.insertExercise(e);
    }

    void campusProject(){
        Exercise e = new Exercise()
                .setName("Campus Project")
                .setDesc("Find or set a boulder conducive to campusing. Avoid jerky/violent movements, such as trying to campus a compression boulder. This can lead to elbow tendinitis. Project it for 15 minutes.")
                .setType(types[POWER])
                .setSets("N/A")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest completely between attempts.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void powerPullUps(){
        Exercise e = new Exercise()
                .setName("Bar to Chest Pull Ups")
                .setDesc("Do three sets of bar to chest pull ups until failure. A bar to chest pull up is dynamic and powerful. Attempt to generate enough momentum to touch the bar to your chest, with your upper body leaned back slightly.\n\n" +
                        "Failure is when you lose the dynamic power to get the bar to your chest, even if you could do more static pull ups, stop at this point.")
                .setType(types[POWER])
                .setSets("3")
                .setEquip(equip[PULLUPBAR])
                .setReps("N/A")
                .setRest("Rest such that this drill takes 15 minutes.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void oneFiveNine(){
        Exercise e = new Exercise()
                .setName("One Five Nine Progression")
                .setDesc("The elite campus board benchmark is to start matched on rung one, then go to 5, and without matching, move to 9. Do the same sequence, but suit the size of moves to yourself and treat it like a project.\n\n" +
                        "For example, if you complete 1-3-5 on both arms, then try either 1-4-5 or 1-3-6. Make sure to alternate which hand you are starting with for each attempt.")
                .setType(types[POWER])
                .setSets("N/A")
                .setEquip(equip[CAMPUSBOARD])
                .setReps("N/A")
                .setRest("Rest completely between attempts.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void limitStaticBoulders(){
        Exercise e = new Exercise()
                .setName("Static Limit Bouldering")
                .setDesc("Find or set three short (3-5 move) static sequences which you can complete, but are near your limit. Attempt each five times.\n\n" +
                        "Time your attempts such that you get on the wall at the start of every even minute (minute 0, 2, 4, ... 26, 28). This will make for 15 attempts total within 30 minutes.")
                .setType(types[STRENGTH])
                .setSets("3")
                .setEquip(NONE)
                .setReps("5")
                .setRest("Rest after the attempt until the next even minute.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void limitPowerBoulders(){
        Exercise e = new Exercise()
                .setName("Powerful Limit Bouldering")
                .setDesc("Find or set three short (3-5 move) powerful sequences which you can complete, but are near your limit. Attempt each five times.\n\n" +
                        "Time your attempts such that you get on the wall at the start of every even minute (minute 0, 2, 4, ... 26, 28). This will make for 15 attempts total within 30 minutes.")
                .setType(types[POWER])
                .setSets("3")
                .setEquip(NONE)
                .setReps("5")
                .setRest("Rest after the attempt until the next even minute.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void longBoulderProject(){
        Exercise e = new Exercise()
                .setName("Power Endurance Project")
                .setDesc("Find or set a boulder which is at least ten moves, with the crux in the upper half. Project it.")
                .setType(types[POWEND])
                .setSets("N/A")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest completely between attempts.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void longCampusProject() {
        Exercise e = new Exercise()
                .setName("Power Endurance Campus Project")
                .setDesc("Find or set a footless boulder which is at least ten moves, with the crux in the upper half. Project it.")
                .setType(types[POWEND])
                .setSets("N/A")
                .setEquip(NONE)
                .setReps("N/A")
                .setRest("Rest completely between attempts.")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void enduranceIntervals() {
        Exercise e = new Exercise()
                .setName("Short Rest Endurance Intervals")
                .setDesc("Find or set a circuit which is moderately hard. Climb three minutes on, one minute off, ten times.")
                .setType(types[ENDURANCE])
                .setSets("1")
                .setEquip(NONE)
                .setReps("10")
                .setRest("One minute")
                .setDiff(ANY)
                .setTime(times[THIRTY_M]);
        db.insertExercise(e);
    }

    void hangboardIntroduction(){
        Exercise e = new Exercise()
                .setName("Hangboard Introduction")
                .setDesc("Read the training tip on hangboard posture before trying to hangboard. As a light introduction, exercise should not cause you to fail at any point. If it does, use better holds." +
                        "\n\nStart: Jug for 15 seconds" +
                        "\nMinute 1: Large edge for 10 seconds" +
                        "\nMinute 2: Jug for 15 seconds" +
                        "\nMinute 3: Medium edge for 6 seconds" +
                        "\nMinute 4: Jug for 15 seconds" +
                        "\nMinute 5: Medium edge for 6 seconds" +
                        "\nMinute 6: Large edge for 10 seconds" +
                        "\nMinute 7: Jug for 15 seconds" +
                        "\nMinute 8: Medium edge for 6 seconds" +
                        "\nMinute 9: Jug for 15 seconds" +
                        "\nMinute 10: Large edge for 10 seconds")
                .setType(types[STRENGTH])
                .setSets("1")
                .setEquip(NONE)
                .setReps("10")
                .setRest("Rest until the next minute after the hang")
                .setDiff(grades[TEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void hangboardIntroductionTwo(){
        Exercise e = new Exercise()
                .setName("Hangboard Introduction Two")
                .setDesc("Read the training tip on hangboard posture before trying to hangboard. As a light introduction, exercise should not cause you to fail at any point. If it does, use better holds." +
                        "\n\nStart: Jug for 15 seconds" +
                        "\nMinute 1: Large edge for 12 seconds" +
                        "\nMinute 2: Jug for 15 seconds" +
                        "\nMinute 3: Medium edge for 12 seconds" +
                        "\nMinute 4: Jug for 15 seconds" +
                        "\nMinute 5: Medium edge for 10 seconds" +
                        "\nMinute 6: Large edge for 12 seconds" +
                        "\nMinute 7: Jug for 15 seconds" +
                        "\nMinute 8: Medium edge for 10 seconds" +
                        "\nMinute 9: Jug for 15 seconds" +
                        "\nMinute 10: Large edge for 12 seconds")
                .setType(types[STRENGTH])
                .setSets("1")
                .setEquip(NONE)
                .setReps("10")
                .setRest("Rest until the next minute after the hang")
                .setDiff(grades[ELEVEN])
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void rings(){
        Exercise e = new Exercise()
                .setName("Conditioning on Rings")
                .setDesc("Complete the following three times, with 30 seconds rest in between steps." +
                        "1. Hold plank position for 10 seconds\n" +
                        "2. 10 push ups on the rings\n" +
                        "3. 5 dips\n" +
                        "4. One typewriter on each arm, as slowly as possible\n" +
                        "5. 8 fly's (From plank position, extend both arms out to the side")
                .setType(types[STRENGTH])
                .setSets("1")
                .setEquip(equip[RINGS])
                .setReps("10")
                .setRest("Rest 30 seconds between steps.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void medBall(){
        Exercise e = new Exercise()
                .setName("Medicine Ball Core")
                .setDesc("Do each exercise for 45 seconds, then rest for 15 seconds. Then repeat again.\n\n" +
                        "1. Med Ball Crunch\n\n" +
                        "2. Russian Twist (extend the ball out to the side)\n\n" +
                        "3. Med Ball Plank (plank position, but with your hands on the top/sides of a med ball)\n\n" +
                        "4. Reverse Med Ball Plank (feet are on the ball)\n\n" +
                        "5. Med Ball Toe Touch (lie on your back with legs straight up. Touch the ball to toes)")
                .setType(types[CONDITIONING])
                .setSets("10")
                .setEquip(equip[MEDICINEBALLS])
                .setReps("N/A")
                .setRest("Rest 15 seconds between steps")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void strengthPyramid(){
        Exercise e = new Exercise()
                        .setName("Strength Pyramid")
                .setDesc("Start by doing 10 pull ups, and then 1 push up. Next do 9 pull ups and 2 push ups. Repeat until you do 1 pull up and 10 push ups.\n\n" +
                        "Rest for three minutes, then do the same in reverse.\n\n" +
                        "Use assistance for the pull ups as necessary, such as stepping in a theraband attached to the bar.")
                .setType(types[STRENGTH])
                .setSets("20")
                .setEquip(equip[PULLUPBAR])
                .setReps("11")
                .setRest("Rest for 3 minutes at the halfway point.")
                .setDiff(ANY)
                .setTime(times[FIFTEEN_M]);
        db.insertExercise(e);
    }

    void maxLock(){
        Exercise e = new Exercise()
                .setName("Max Lock Off")
                .setDesc("Find a comfortable body position on a steep wall. Lock off on the holds as high as possible, attempting to get your chest into the wall. Hold this position for 10 seconds.\n\n" +
                        "Do this five times, on two different positions. Rest one minute between reps, and two minutes between positions." +
                        "Choose holds and positions such that you are fighting for 4 seconds later into the drill.")
                .setType(types[STRENGTH])
                .setSets("2")
                .setEquip(NONE)
                .setReps("5")
                .setRest("Rest one minute between reps, and two minutes between positions.")
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
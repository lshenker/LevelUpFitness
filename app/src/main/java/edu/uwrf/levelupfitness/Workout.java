package edu.uwrf.levelupfitness;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Workout implements Parcelable {

    private String name;
    private String date;
    private ArrayList<Exercise> exerciseList;

    //constructors
    public Workout() {
        name = null;
        date = null;
        exerciseList = new ArrayList<Exercise>();
    }

    public Workout(String name, ArrayList<Exercise> exercises) {
        this.name = name;
        exerciseList = exercises;
        date = null;
    }

    public Workout(Parcel in) {
        this();
        name = in.readString();
        in.readTypedList(exerciseList, Exercise.CREATOR);
        //mInfo = in.readParcelable(WorkoutParcelable.class.getClassLoader());
    }

    //getters
    public String getName() { return name; }
    public String getDate() { return date; }
    public ArrayList<Exercise> getExerciseList() { return exerciseList; }
    public String getTodaysDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        String formattedDate = df.format(c);
        return formattedDate;
    }

    //setters
    public void setName(String name) {this.name = name;}
    public void setDate() {
        this.date = getTodaysDate();
    }
    public void setExerciseList(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeTypedList(exerciseList);
    }

    public static final Parcelable.Creator<Workout> CREATOR
            = new Parcelable.Creator<Workout>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };
}

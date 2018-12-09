package edu.uwrf.levelupfitness;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {

    private String name;
    private int sets;
    private int reps;
    private double weight;

    //constructors
    public Exercise() {
        name = null;
        sets = 0;
        reps = 0;
        weight = 0;
    }

    public Exercise(String name, int sets, int reps, double weight) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public Exercise(Parcel in) {
        this();
        name = in.readString();
        sets = in.readInt();
        reps = in.readInt();
        weight = in.readDouble();
    }

    //getters
    public String getName() {
        return name;
    }
    public int getSets() {
        return sets;
    }
    public int getReps() {
        return reps;
    }
    public double getWeight() {
        return weight;
    }

    //setters
    public void setname(String name) {
        this.name = name;
    }
    public void setsets(int sets) {
        this.sets = sets;
    }
    public void setreps(int reps) {
        this.reps = reps;
    }
    public void setweight(double weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(sets);
        out.writeInt(reps);
        out.writeDouble(weight);
    }

    public static final Parcelable.Creator<Exercise> CREATOR
            = new Parcelable.Creator<Exercise>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };
}

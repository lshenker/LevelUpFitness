package edu.uwrf.levelupfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import edu.uwrf.workoutapp.R;

public class WorkoutAdapter extends BaseAdapter {

    Context context;
    ArrayList<Workout> workoutList = new ArrayList<>();

    public WorkoutAdapter(Context context, ArrayList<Workout> workoutList) {
        this.context = context;
        this.workoutList = workoutList;
    }

    @Override
    public int getCount() {
        return workoutList.size();
    }

    @Override
    public Object getItem(int position) {
        return workoutList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.workout_item, parent,false);
        }

        Workout tempWorkout = (Workout) getItem(position);
        ArrayList<Exercise> tempExerciseList = tempWorkout.getExerciseList();

        TextView workName = (TextView) convertView.findViewById(R.id.workName);
        TextView exName1 = (TextView) convertView.findViewById(R.id.exName1);
        TextView exName2 = (TextView) convertView.findViewById(R.id.exName2);
        TextView exName3 = (TextView) convertView.findViewById(R.id.exName3);
        TextView exSetsRepsWeight1 = (TextView) convertView.findViewById(R.id.exSetsRepsWeight1);
        TextView exSetsRepsWeight2 = (TextView) convertView.findViewById(R.id.exSetsRepsWeight2);
        TextView exSetsRepsWeight3 = (TextView) convertView.findViewById(R.id.exSetsRepsWeight3);

        // TODO: Account for Workout objects with less than 3 exercises, or require >= 3

        // displays the workout name and first 3 exercises
        workName.setText(tempWorkout.getName());
        exName1.setText(tempExerciseList.get(0).getName());
        exName2.setText(tempExerciseList.get(1).getName());
        exName3.setText(tempExerciseList.get(2).getName());
        exSetsRepsWeight1.setText((String.valueOf(tempExerciseList.get(0).getSets())) + " x " +
                String.valueOf(tempExerciseList.get(0).getReps()) + "     " +
                String.valueOf(tempExerciseList.get(0).getWeight()) + " lbs."
        );
        exSetsRepsWeight2.setText((String.valueOf(tempExerciseList.get(1).getSets())) + " x " +
                String.valueOf(tempExerciseList.get(1).getReps()) + "     " +
                String.valueOf(tempExerciseList.get(1).getWeight()) + " lbs."
        );
        exSetsRepsWeight3.setText((String.valueOf(tempExerciseList.get(2).getSets())) + " x " +
                String.valueOf(tempExerciseList.get(2).getReps()) + "   " +
                String.valueOf(tempExerciseList.get(2).getWeight()) + " lbs."
        );;

        return convertView;
    }
}

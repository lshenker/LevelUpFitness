package edu.uwrf.levelupfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import edu.uwrf.workoutapp.R;

public class ExerciseAdapter extends BaseAdapter {

    // variables
    Context context;
    ArrayList<Exercise> exerciseList = new ArrayList<>();

    public ExerciseAdapter(Context context, ArrayList<Exercise> exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
    }

    @Override
    public int getCount() {
        return exerciseList.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.exercise_item, parent,false);
        }

        Exercise tempExercise = (Exercise) getItem(position);

        TextView exName = (TextView) convertView.findViewById(R.id.exName);
        TextView exSetsRepsWeight = (TextView) convertView.findViewById(R.id.exSetsRepsWeight);

        exName.setText(tempExercise.getName());
        exSetsRepsWeight.setText((String.valueOf(tempExercise.getSets())) + " x " +
                String.valueOf(tempExercise.getReps()) + "     " +
                String.valueOf(tempExercise.getWeight()) + " lbs."
        );

        return convertView;
    }
}

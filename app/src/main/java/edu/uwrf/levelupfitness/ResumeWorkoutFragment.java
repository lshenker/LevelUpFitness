package edu.uwrf.levelupfitness;

import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uwrf.workoutapp.R;

public class ResumeWorkoutFragment extends Fragment {

    // variables
    Workout model;
    ListView exerciseListView;
    ArrayList<Exercise> exerciseList = new ArrayList<>();
    Exercise current;
    EditText currName;
    EditText currSets;
    EditText currReps;
    EditText currWeight;

    public ResumeWorkoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get ref to view
        View view = inflater.inflate(R.layout.fragment_resume_workout, container, false);

        //initialize variables
        model = new Workout(Parcel.obtain());
        exerciseListView = (ListView)view.findViewById(R.id.rw_listview);
        currName = (EditText) view.findViewById(R.id.rw_name);
        currSets = (EditText) view.findViewById(R.id.rw_sets);
        currReps = (EditText) view.findViewById(R.id.rw_reps);
        currWeight = (EditText) view.findViewById(R.id.rw_weight);

        // load Workout object from HomeFragment if applicable
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            model = bundle.getParcelable("Workout");
        }

        // "✓" button on ResumeWorkoutFragment
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_rw_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exerciseList.size() >= 1) {
                    // not the last exercise in workout
                    updateWorkout();

                } else {
                    // finish workout
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.flMain, new HomeFragment());
                    ft.commit();
                }
            }
        });

        // "X" button on ResumeWorkoutFragment
        FloatingActionButton fab2 = (FloatingActionButton) view.findViewById(R.id.fab_rw_cancel);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cancel workout
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.flMain, new HomeFragment());
                ft.commit();
            }
        });

        // Set title
        ((MainActivity)getActivity()).setActionBarTitle(model.getName() + "   (" + model.getDate() + ")");

        // Load exercises and populate listview
        loadExercises();
        updateWorkout();

        // Inflate the layout for this fragment
        return view;
    }

    void loadExercises() {
        exerciseList = model.getExerciseList();
        current = exerciseList.get(0);

    }

    void updateWorkout() {
        if (exerciseList.size() > 0) {
            current = exerciseList.get(0);
            currName.setText(current.getName());
            currWeight.setText(String.valueOf(current.getWeight()));
            currSets.setText(String.valueOf(current.getSets()));
            currReps.setText(String.valueOf(current.getReps()));
            exerciseList.remove(0);
            fillListView();
        }
    }

    void saveWorkout(Workout model) {

        // TODO: Save exercize ArrayList to workout ArrayList SQL/txt file
    }

    void fillListView() {
        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(getActivity().getApplicationContext(), exerciseList);
        exerciseListView.setAdapter(exerciseAdapter);
    }
}


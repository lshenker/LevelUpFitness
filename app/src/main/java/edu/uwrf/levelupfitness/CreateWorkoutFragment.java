package edu.uwrf.levelupfitness;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

import edu.uwrf.workoutapp.R;

public class CreateWorkoutFragment extends Fragment implements AddExerciseDialogFragment.AddExerciseDialogListener {

    // variables
    ListView createWorkoutListView;
    ArrayList<Exercise> exerciseList = new ArrayList<>();
    String name;
    int sets;
    int reps;
    double weight;

    public CreateWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get the view
        View view = inflater.inflate(R.layout.fragment_create_workout, container, false);

        // get the view items
        createWorkoutListView = (ListView)view.findViewById(R.id.create_workout_listview);
        final EditText nameTxt = (EditText) view.findViewById(R.id.ex_workName);

        // "+" button on CreateWorkoutFragment
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_cw);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // go to CreateWorkoutFragment
                DialogFragment dialog = new AddExerciseDialogFragment();
                dialog.setTargetFragment(CreateWorkoutFragment.this, 1);
                dialog.show(getFragmentManager(), "addEx");
            }
        });

        // "✓" button on CreateWorkoutFragment
        FloatingActionButton fab_done = (FloatingActionButton) view.findViewById(R.id.fab_cw_done);
        fab_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // pass the created Workout object to HomeFragment
                name = nameTxt.getText().toString().trim();
                Workout model = new Workout(name, exerciseList);
                Fragment destFragment = new HomeFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Workout", model);
                destFragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(getId(), destFragment);
                ft.commit();
            }
        });

        // Set title
        ((MainActivity)getActivity()).setActionBarTitle("Create Workout");

        // Inflate the layout for this fragment
        return view;
    }

    void addExercise() {
        exerciseList.add(new Exercise(name, sets, reps, weight));
    }

    void fillListView() {
        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(getActivity().getApplicationContext(), exerciseList);
        createWorkoutListView.setAdapter(exerciseAdapter);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        addExercise();
        fillListView();
    }

    @Override
    public void getExerciseFields(String name, int sets, int reps, double weight) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }
}

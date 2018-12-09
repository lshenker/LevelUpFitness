package edu.uwrf.levelupfitness;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import edu.uwrf.workoutapp.R;

public class MyExercisesFragment extends Fragment {

    // variables
    String[] exercises;
    ListView listView;
    ArrayAdapter<String> exerciseAdapter;

    public MyExercisesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get view
        View view = inflater.inflate(R.layout.fragment_my_exercises, container, false);

        //  get list view
        listView = (ListView)view.findViewById(R.id.exercises_listview);

        // "+" button on MyExercisesFragment
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_myex);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "MyEx Button works!",
                        Toast.LENGTH_LONG).show();
            }
        });

        loadSavedExercises();

        exerciseAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                exercises
        );

        fillListView();

        // Set title
        ((MainActivity)getActivity()).setActionBarTitle("My Exercises");

        // Load workouts and populate listview
        loadSavedExercises();
        fillListView();

        // Inflate the layout for this fragment
        return view;
    }

    void loadSavedExercises() {
        //TESTING DATA
        exercises = new String[]{"Squat", "Deadlift", "Bench Press", "Barbell Row", "Overhead Press", "Barbell Curl", "Pull up"};

        // TODO: Load exercise Array from SQL/txt file
    }

    void fillListView() {
        listView.setAdapter(exerciseAdapter);
    }
}

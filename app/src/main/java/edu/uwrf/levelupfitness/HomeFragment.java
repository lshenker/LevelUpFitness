package edu.uwrf.levelupfitness;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.*;

import edu.uwrf.workoutapp.R;

public class HomeFragment extends Fragment {

    // variables
    Workout model;
    ListView workoutListView;
    ArrayList<Workout> workoutList = new ArrayList<>();
    //String username;
    public static String username;
    public static void sendSetUsername(String a){
        username = a;
    }


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get ref to view
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //initialize variables
        model = new Workout(Parcel.obtain());
        workoutListView = (ListView)view.findViewById(R.id.workout_listview);

        // load Workout object from CreateWorkoutFragment if applicable
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            model = bundle.getParcelable("Workout");
            workoutList.add(model);
            saveWorkout(model);
        }

        workoutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // pass the Workout object to ResumeWorkoutFragment
                Workout model = workoutList.get(position);
                model.setDate();
                Fragment destFragment = new ResumeWorkoutFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Workout", model);
                destFragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(getId(), destFragment);
                ft.commit();
            }
        });

        // "+" button on HomeFragment
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.flMain, new CreateWorkoutFragment());
                ft.commit();
            }
        });

        // Set title
        ((MainActivity)getActivity()).setActionBarTitle("Home");

        // Load workouts and populate listview
        loadSavedWorkouts();
        fillListView();

        // Inflate the layout for this fragment
        return view;
    }

    void loadSavedWorkouts() {
        //TESTING DATA
        ArrayList<Exercise> exList = new ArrayList<Exercise>();
        exList.add(new Exercise("Squat", 5, 5, 225));
        exList.add(new Exercise("Bench Press", 4, 8, 165));
        exList.add(new Exercise("Barbell Row", 3, 10, 135));
        Workout workoutA = new Workout("Workout A", exList);
        workoutList.add(workoutA);

        exList = new ArrayList<Exercise>();
        exList.add(new Exercise("Squat", 5, 5, 230));
        exList.add(new Exercise("Overhead Press", 4, 8, 135));
        exList.add(new Exercise("Deadlift", 3, 10, 245));
        Workout workoutB = new Workout("Workout B", exList);
        workoutList.add(workoutB);

        // TODO: Load workout ArrayList from SQL/txt file
       // String json = Read_column_value_logic_here
       // JSONObject jsonObject = new JSONObject(json);
    }

    void saveWorkout(Workout model) {

        ObjectMapper mapper = new ObjectMapper();


        try {
            String jsonString = mapper.writeValueAsString(model);
           // DatabaseHelper.addWorkout(username, jsonString);
        }
        catch(JsonGenerationException e){
            e.printStackTrace();
        }
        catch(JsonMappingException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }



    }

    void fillListView() {
        WorkoutAdapter workoutAdapter = new WorkoutAdapter(getActivity().getApplicationContext(), workoutList);
        workoutListView.setAdapter(workoutAdapter);
    }


}

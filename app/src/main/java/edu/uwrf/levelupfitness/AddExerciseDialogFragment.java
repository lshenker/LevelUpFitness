package edu.uwrf.levelupfitness;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import edu.uwrf.workoutapp.R;

public class AddExerciseDialogFragment extends DialogFragment
{

    // Interface definition
    public interface AddExerciseDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void getExerciseFields(String name, int sets, int reps, double weight);
    }

    // variables
    public AddExerciseDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle("Add Exercise");
        AlertDialog dialog = builder.create();

        // get the view
        View view = inflater.inflate(R.layout.dialog_add_exercise, null);


        //get text fields for validation
        final EditText name = (EditText) view.findViewById(R.id.ex_name);
        final EditText sets = (EditText) view.findViewById(R.id.ex_sets);
        final EditText reps = (EditText) view.findViewById(R.id.ex_reps);
        final EditText weight = (EditText) view.findViewById(R.id.ex_weight);



        /*

        boolean done = false;

        while(!done){
            try {
                String workoutValidator = name.getText().toString();
                done = true;
            }
            catch(NullPointerException e){
                System.out.print("type in a string");
            }

        }

        done = false;

        while(!done){
            try {
                int setValidator = Integer.parseInt(sets.getText().toString());
                done = true;
            }
            catch(NullPointerException e){
                System.out.print("type in a int");
            }

        }

        //done = false;

        while(!done){
            try {
                int repValidator = Integer.parseInt(reps.getText().toString());
                done = true;
            }
            catch(NullPointerException e){
                System.out.print("type in a int");
            }

        }

        done = false;

        while(!done){
            try {
                double weightValidator = Double.parseDouble(weight.getText().toString());
                done = true;
            }
            catch(NullPointerException e){
                System.out.print("type in a double");
            }

        }
        */

        // set the view
        builder.setView(view)
        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked Add button
                // pass Exercise fields to CreateWorkoutFragment
                listener.getExerciseFields(
                        name.getText().toString().trim(),
                        Integer.parseInt(sets.getText().toString().trim()),
                        Integer.parseInt(reps.getText().toString().trim()),
                        Double.parseDouble(weight.getText().toString().trim())
                );
                listener.onDialogPositiveClick(AddExerciseDialogFragment.this);
                dismiss();
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dismiss();
            }
        });

        return builder.create();
    }

    // Override the Fragment.onAttach() method to instantiate the DialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (AddExerciseDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("Must implement DialogListener");
        }
    }

} // end of class AddExerciseDialogFragment

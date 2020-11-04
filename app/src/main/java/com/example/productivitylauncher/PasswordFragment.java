package com.example.productivitylauncher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordFragment extends DialogFragment {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.fragment_password, null);
        final DataLoader readwriter = new DataLoader(getContext());

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v)
                // Add action buttons
                .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String key = readwriter.getKey();
                        TextView etPassport = v.findViewById(R.id.etPassword);
                        String enteredKey = etPassport.getText().toString();
                        Log.d("lol", enteredKey);
                        Log.d("lol", key);
                        if(key.equals(enteredKey)){
                            Intent secondAppIntent = new Intent(getContext(), ChangeAppsActivity.class);
                            startActivity(secondAppIntent);
                        }
                        else{
                            PasswordFragment.this.getDialog().cancel();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PasswordFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

}

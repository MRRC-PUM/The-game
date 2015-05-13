package com.example.bartek.shipswar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mirkowski.management.Controller;

import org.w3c.dom.Text;


public class WantPlayFragment extends DialogFragment implements View.OnClickListener{

    private Controller controller = null;
    private Button Yes, No;
    private String name;
    private TextView textView;


    public void setController(Controller controller) { this.controller = controller; }

    public void setName(String name){
        this.name = name;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_want_play, null);
        Yes = (Button) view.findViewById(R.id.Yes);
        No = (Button) view.findViewById(R.id.No);
        Yes.setOnClickListener(this);
        No.setOnClickListener(this);
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("Czy chcesz zagrrac z "+this.name+" ????");
        setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.Yes) {

            controller.responseOnInviteToGame(true,name);
            dismiss();
        }
        else
        {
            controller.responseOnInviteToGame(false,name);
            WantPlayFragment.this.dismiss();
        }
    }
}

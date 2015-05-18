package com.example.bartek.shipswar;

import android.app.Activity;
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
import com.mirkowski.management.command.SystemCommand;


public class EndingFragment extends DialogFragment implements View.OnClickListener{

    private Controller controller = null;
    Button ok;
    TextView textView;
    SystemCommand info;


    public void setController(Controller controller) { this.controller = controller; }

    public void setInfo(SystemCommand info){
        this.info = info;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View view = inflater.inflate(R.layout.fragment_want_play, null);
        ok = (Button) view.findViewById(R.id.OK);
        ok.setOnClickListener(this);


        textView = (TextView) view.findViewById(R.id.endinginfoTextView);

        if(info==SystemCommand.Win) textView.setText("Gratulacje wygrałes");
        if(info==SystemCommand.Defeat) textView.setText("Wiesz co? Przegrałęs :p");
        if(info==SystemCommand.Error) textView.setText("Cos poszło nie tak x.x");




        setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.OK) {

            controller.destroy(this.info);


            dismiss();
        }
    }

    public void destroy() {
        onDestroy();
    }
}

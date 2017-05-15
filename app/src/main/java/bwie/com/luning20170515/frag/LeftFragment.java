package bwie.com.luning20170515.frag;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.kyleduo.switchbutton.SwitchButton;

import org.greenrobot.eventbus.EventBus;

import bwie.com.luning20170515.Myvent;
import bwie.com.luning20170515.R;

public class LeftFragment extends Fragment  {

    private TextView leftTv;
    private SwitchButton switch_btn;
    private View view;
    private View id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.left, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        leftTv = (TextView) view.findViewById(R.id.left_tv);
        switch_btn = (SwitchButton) view.findViewById(R.id.switch_btn);
        id = view.findViewById(R.id.left);

    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               EventBus.getDefault().post(new Myvent(isChecked));



                setBackground(isChecked);
            }
        });


    }


    private void setBackground(boolean white) {

        if (white) {
            leftTv.setTextColor(Color.BLACK);

//            id.setBackgroundColor(Color.WHITE);

        } else {
            //夜间
//            id.setBackgroundColor(getResources().getColor(R.color.color_window));
            leftTv.setTextColor(Color.RED);

        }
    }
}

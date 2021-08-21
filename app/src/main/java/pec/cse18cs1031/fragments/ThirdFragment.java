package pec.cse18cs1031.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ThirdFragment extends Fragment {

    Button btn3,btnb3;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_third,container,false);
        btn3= view.findViewById(R.id.btn3);
        viewPager = getActivity().findViewById(R.id.fragmentview);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Login_Acitvity.class);
                getActivity().startActivity(i);

            }
        });
        btnb3 =view.findViewById(R.id.btnb3);
        btnb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
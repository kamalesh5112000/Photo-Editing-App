package pec.cse18cs1031.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SecondFragment extends Fragment {

    Button btn2,btnb2;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_second,container,false);
        btn2= view.findViewById(R.id.btn2);
        viewPager = getActivity().findViewById(R.id.fragmentview);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);

            }
        });

        btnb2 = view.findViewById(R.id.btnb2);
        btnb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
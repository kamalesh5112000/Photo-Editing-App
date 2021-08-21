package pec.cse18cs1031.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class mailfragment extends Fragment {

    EditText editText;
    Button btn;
    ViewPager viewPager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.mailfragment,container,false);
        btn= view.findViewById(R.id.mailbtn);
        editText = view.findViewById(R.id.emailfield);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mailpassfragment pfm = new mailpassfragment();

                String name = editText.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("name",name);

                pfm.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,pfm);
                fragmentTransaction.commit();

            }
        });

        return view;
    }
}


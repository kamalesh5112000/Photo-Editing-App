package pec.cse18cs1031.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class passwordfragment extends Fragment {
    public TextView textView;

    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.passwordfragment,container,false);

        textView = view.findViewById(R.id.namefield);
        editText = view.findViewById(R.id.password);

        Bundle bundle = getArguments();

        if(bundle!=null){
            String name = bundle.getString("name");
            Toast.makeText(getContext(),"name" +name,Toast.LENGTH_SHORT).show();
            textView.setText(name);

        }



        return view;
    }
}

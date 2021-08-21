package pec.cse18cs1031.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class mailpassfragment extends Fragment {

    Button btn;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mailpassfragment,container,false);

        textView = view.findViewById(R.id.passfield);

        Bundle bundle = getArguments();
        String data =bundle.getString("name");
        Toast.makeText(getContext(),data,Toast.LENGTH_SHORT).show();
        textView.setText("Welcome "+data);
        btn = view.findViewById(R.id.passbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MainActivity.class);
                i.putExtra("namekey",data);
                startActivity(i);
            }
        });

        return view;
    }
}
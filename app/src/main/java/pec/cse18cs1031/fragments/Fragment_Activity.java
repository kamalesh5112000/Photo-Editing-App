package pec.cse18cs1031.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

public class Fragment_Activity extends AppCompatActivity {

    ViewPager viewPager;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        viewPager = findViewById(R.id.fragmentview);

        OnboardAdapter onboardAdapter = new OnboardAdapter(getSupportFragmentManager());
        viewPager.setAdapter(onboardAdapter);


    }
}
package pec.cse18cs1031.fragments;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {


    public LoginAdapter(FragmentManager fm){
        super(fm);
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                mailfragment mf = new mailfragment();
                return mf;

            case 1:
                passwordfragment pf = new passwordfragment();
                return pf;

            default:
                return null;
        }
    }
    @Override
    public int getCount(){
        return 2;
    }

}

package com.heroes.lesh.kidneys.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.heroes.lesh.kidneys.FragmentsWelcome.WelcomeEighthFragment;
import com.heroes.lesh.kidneys.FragmentsWelcome.WelcomeFifthFragment;
import com.heroes.lesh.kidneys.FragmentsWelcome.WelcomeFirstFragment;
import com.heroes.lesh.kidneys.FragmentsWelcome.WelcomeFourthFragment;
import com.heroes.lesh.kidneys.FragmentsWelcome.WelcomeFragment;
import com.heroes.lesh.kidneys.FragmentsWelcome.WelcomeNinethFragment;
import com.heroes.lesh.kidneys.FragmentsWelcome.WelcomeSecondFragment;
import com.heroes.lesh.kidneys.FragmentsWelcome.WelcomeSeventhFragment;
import com.heroes.lesh.kidneys.FragmentsWelcome.WelcomeSixthFragment;
import com.heroes.lesh.kidneys.FragmentsWelcome.WelcomeThirdFragment;

public class PageAdapterWelcome extends FragmentStatePagerAdapter {
    //Variables globales
    Fragment fragment;
    private final int numPages = 10;

    public PageAdapterWelcome(FragmentManager fm) {
        super(fm);
    }

    //Metodo para cambiar entre fragmentos
    @Override
    public Fragment getItem(int position) {
        fragment = null;
        switch (position) {
            case 0:
                fragment = new WelcomeFragment();
                break;
            case 1:
                fragment = new WelcomeFirstFragment();
                break;
            case 2:
                fragment = new WelcomeSecondFragment();
                break;
            case 3:
                fragment = new WelcomeThirdFragment();
                break;
            case 4:
                fragment = new WelcomeFourthFragment();
                break;
            case 5:
                fragment = new WelcomeFifthFragment();
                break;
            case 6:
                fragment = new WelcomeSixthFragment();
                break;
            case 7:
                fragment = new WelcomeSeventhFragment();
                break;
            case 8:
                fragment = new WelcomeEighthFragment();
                break;
            case 9:
                fragment=new WelcomeNinethFragment();
                break;
        }
        return fragment;
    }

    //Metodo que devuelve el numero de paginas
    @Override
    public int getCount() {
        return numPages;
    }
}

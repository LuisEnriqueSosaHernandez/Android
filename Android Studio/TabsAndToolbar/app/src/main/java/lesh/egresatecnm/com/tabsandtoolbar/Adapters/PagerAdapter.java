package lesh.egresatecnm.com.tabsandtoolbar.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lesh.egresatecnm.com.tabsandtoolbar.Fragments.FirstFragment;
import lesh.egresatecnm.com.tabsandtoolbar.Fragments.SecondFragment;
import lesh.egresatecnm.com.tabsandtoolbar.Fragments.ThirdFragment;

/**
 * Created by pc on 06/10/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter{
private int numberOfTabs;
    public PagerAdapter(FragmentManager fm,int numberOfTabs) {
        super(fm);
        this.numberOfTabs=numberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            default:
                return new FirstFragment();
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}

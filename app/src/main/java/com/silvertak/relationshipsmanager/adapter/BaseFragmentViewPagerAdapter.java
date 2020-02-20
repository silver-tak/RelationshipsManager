package com.silvertak.relationshipsmanager.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.silvertak.relationshipsmanager.view.fragment.base.BaseFragment;

import java.util.ArrayList;

public class BaseFragmentViewPagerAdapter extends FragmentPagerAdapter
{
    private ArrayList<BaseFragment> fragmentArrayList = new ArrayList<>();
    private ArrayList<String> mFragmentTitleArrayList = new ArrayList<>();

    public BaseFragmentViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public void setFragmentArrayList(ArrayList<BaseFragment> arrayList)
    {
        this.fragmentArrayList = arrayList;
    }

    public void setFragmentTitleArrayList(ArrayList<String> arrayList)
    {
        this.mFragmentTitleArrayList = arrayList;
    }

    public void addFragment(BaseFragment fragment)
    {
        fragmentArrayList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String strTitle = mFragmentTitleArrayList.get(position);
        return strTitle;
    }
}

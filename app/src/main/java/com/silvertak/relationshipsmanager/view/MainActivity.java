package com.silvertak.relationshipsmanager.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.animation.ValueAnimator;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.adapter.BaseFragmentViewPagerAdapter;
import com.silvertak.relationshipsmanager.adapter.ViewPagerAdapter;
import com.silvertak.relationshipsmanager.databinding.ActivityMainBinding;
import com.silvertak.relationshipsmanager.fragment.GroupModifyFragment;
import com.silvertak.relationshipsmanager.fragment.SettingFragment;
import com.silvertak.relationshipsmanager.fragment.StatusFragment;
import com.silvertak.relationshipsmanager.fragment.base.BaseFragment;
import com.silvertak.relationshipsmanager.library.StringLib;
import com.silvertak.relationshipsmanager.viewmodel.MainViewModel;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private MainViewModel mainViewModel;
    private BaseFragmentViewPagerAdapter baseFragmentViewPagerAdapter;
    private ArrayList<BaseFragment> mFragmentArrayList = new ArrayList<>();
    private ArrayList<String> mFragmentTitleArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
        mainBinding.setVm(mainViewModel);
        mainBinding.setLifecycleOwner(this);
        setContentView(mainBinding.getRoot());

        setTabLayoutStyle();
        setFragmentAdapter();
    }

    private void setTabLayoutStyle()
    {
        mainBinding.tabLayout.setupWithViewPager(mainBinding.viewPager);
        mainBinding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.background));
        mainBinding.tabLayout.setTabTextColors(getResources().getColor(R.color.thickgray), getResources().getColor(R.color.pointColor));
    }

    private void setFragmentAdapter()
    {
        mFragmentArrayList.add(new StatusFragment());
        mFragmentTitleArrayList.add("현황");
        mFragmentArrayList.add(new GroupModifyFragment());
        mFragmentTitleArrayList.add("그룹 편집");
        mFragmentArrayList.add(new SettingFragment());
        mFragmentTitleArrayList.add("설정");
        baseFragmentViewPagerAdapter = new BaseFragmentViewPagerAdapter(getSupportFragmentManager());
        baseFragmentViewPagerAdapter.setFragmentArrayList(mFragmentArrayList);
        baseFragmentViewPagerAdapter.setFragmentTitleArrayList(mFragmentTitleArrayList);
        mainBinding.viewPager.setAdapter(baseFragmentViewPagerAdapter);
    }









}

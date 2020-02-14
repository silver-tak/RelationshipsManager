package com.silvertak.relationshipsmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter
{
    private Context mContext;
    private int[] mLayoutList;
    private String[] mTitleList;

    public ViewPagerAdapter()
    {

    }

    public ViewPagerAdapter(Context context, int[] layoutList, String[] titleList)
    {
        this.mContext = context;
        this.mLayoutList = layoutList;
        this.mTitleList = titleList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;

        if(mContext != null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mLayoutList[position], container, false);
            /*switch (position)
            {
                case 0 :
                    view = inflater.inflate(R.layout.store_brand_viewpager, container, false); break;
                case 1 :
                    view = inflater.inflate(R.layout.store_goods_viewpager, container, false); break;
            }*/
            container.addView(view);
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String strTitle = mTitleList[position];
        /*switch (position)
        {
            case 0 :
                strTitle = "브랜드"; break;
            case 1 :
                strTitle = "상품"; break;
        }*/
        return strTitle;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }
}

package com.silvertak.relationshipsmanager.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;

import com.silvertak.relationshipsmanager.R;

public class CallTypeCustomView extends LinearLayout {

    private AppCompatImageView iv;
    private Context mContext;

    public CallTypeCustomView(Context context)
    {
        super(context);
        this.mContext = context;
        addImageView(context);
    }

    public CallTypeCustomView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        addImageView(context);
    }

    private void addImageView(Context context)
    {
        iv = new AppCompatImageView(context);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics())
        );
        lp.weight = 1.0f;
        lp.gravity = Gravity.CENTER;
        iv.setLayoutParams(lp);

        this.addView(iv);
    }

    public void setImageResource(int nId)
    {
        iv.setImageResource(nId);
    }

    public void setIncommingType()
    {
        iv.setImageResource(R.mipmap.call_in);
        this.setBackgroundColor(mContext.getResources().getColor(R.color.call_button));
    }

    public void setOutgoingType()
    {
        iv.setImageResource(R.mipmap.call_out);
        this.setBackgroundColor(mContext.getResources().getColor(R.color.hashtag));
    }

    public void setMissedType()
    {
        iv.setImageResource(R.mipmap.call_miss);
        this.setBackgroundColor(mContext.getResources().getColor(R.color.red));
    }
}

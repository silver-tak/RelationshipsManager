package com.silvertak.relationshipsmanager.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.silvertak.relationshipsmanager.R;

public class ClickSwitchBackgroundColorTextView extends AppCompatTextView implements View.OnTouchListener {

    private int nNormalColorId;
    private int nClickedColorId;
    private int nNormalBackgroundResourceId;
    private int nClickedBackgroundResourceId;
    private  OnClickListener onClickListener;

    public ClickSwitchBackgroundColorTextView(Context context)
    {
        super(context);
    }

    public ClickSwitchBackgroundColorTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        nNormalColorId = context.obtainStyledAttributes(attrs, R.styleable.ClickSwitchBackgroundColorTextView).getResourceId(R.styleable.ClickSwitchBackgroundColorTextView_normalBackgroundColor, 0);
        nClickedColorId = context.obtainStyledAttributes(attrs, R.styleable.ClickSwitchBackgroundColorTextView).getResourceId(R.styleable.ClickSwitchBackgroundColorTextView_clickedBackgroundColor, 0);

        nNormalBackgroundResourceId = context.obtainStyledAttributes(attrs, R.styleable.ClickSwitchBackgroundColorTextView).getResourceId(R.styleable.ClickSwitchBackgroundColorTextView_normalBackgroundResource, 0);
        nClickedBackgroundResourceId = context.obtainStyledAttributes(attrs, R.styleable.ClickSwitchBackgroundColorTextView).getResourceId(R.styleable.ClickSwitchBackgroundColorTextView_clickedBackgroundResource, 0);

        this.setOnTouchListener(this);
        //this.setBackgroundColor(context.getResources().getColor(nNormalColorId));
        setNormal(context);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        onClickListener = l;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE)
            setClicked(view.getContext());
            //setBackgroundColor(view.getContext().getResources().getColor(nClickedColorId));
        else
        {
            setNormal(view.getContext());
            //setBackgroundColor(view.getContext().getResources().getColor(nNormalColorId));
            if(onClickListener != null) onClickListener.onClick(view);
        }
        return true;
    }

    private void setNormal(Context context)
    {
        if(nNormalColorId != 0)
            setBackgroundColor(context.getResources().getColor(nNormalColorId));
        else
            setBackgroundResource(nNormalBackgroundResourceId);
    }

    private void setClicked(Context context)
    {
        if(nClickedColorId != 0)
            setBackgroundColor(context.getResources().getColor(nClickedColorId));
        else
            setBackgroundResource(nClickedBackgroundResourceId);
    }
}

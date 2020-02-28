package com.silvertak.relationshipsmanager.customView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.silvertak.relationshipsmanager.R;

public class CustomDIalog extends Dialog {

    public CustomDIalog(Activity activity){
        super(activity);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_yes_or_no);

        Window window = getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        /*int orientation = activity.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            window.setLayout((int)(displayMetrics.widthPixels * 0.5f), (int)(displayMetrics.heightPixels * 0.7f));
        } else {
            window.setLayout((int)(displayMetrics.widthPixels * 0.9f), (int)(displayMetrics.heightPixels * 0.70f));
        }*/

        // Dialog 사이즈 조절 하기
        /*WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);*/
    }

    public void showYesOrNoDialog(String strMessage, final View.OnClickListener yesListener)
    {


        final TextView messageTv = findViewById(R.id.messageTv);
        final TouchChangeTextColorTextView confirmTv = findViewById(R.id.confirmTv);
        final TouchChangeTextColorTextView cancelTv = findViewById(R.id.cancelTv);

        messageTv.setText(strMessage);
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeYes(view, yesListener);
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        this.show();
    }

    private void executeYes(View view, View.OnClickListener yesListener)
    {
        this.dismiss();
        yesListener.onClick(view);
    }
}

package ru.arcadudu.danatest;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyToggle extends LinearLayout {

    private Boolean isChecked = false;
    private ImageView iv;
    private Context ctx;

    public MyToggle(Context context) {
        super(context);
        init(context);
    }

    public MyToggle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyToggle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MyToggle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(final Context context){
        View v = View.inflate(context, R.layout.background_eye_icon_active, this);
        iv = v.findViewById(R.id.imageView2);
        ctx = context;
    }

    public void click(boolean b){
        isChecked = b;
        if(isChecked){
            iv.setImageDrawable(ctx.getResources().getDrawable(R.drawable.icon_eye_active_dark, null));
        }else{
            iv.setImageDrawable(ctx.getResources().getDrawable(R.drawable.icon_eye_passive_dark, null));
        }
    }

    public boolean getIsChecked(){
        return isChecked;
    }

}

package com.basesoft.baseappretro.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import com.basesoft.baseappretro.R;

/**
 * Created by yamil.marques on 3/4/16.
 */
public class TextViewBase extends TextView {

    public TextViewBase(Context context) {
        super(context);
        init(context);
    }

    public TextViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextViewBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.Selected_typeface));
        setTypeface(typeface);
    }
}

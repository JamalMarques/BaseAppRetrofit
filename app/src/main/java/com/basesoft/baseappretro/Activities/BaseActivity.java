package com.basesoft.baseappretro.Activities;

import android.graphics.Typeface;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.basesoft.baseappretro.Fragments.BaseFragment;
import com.basesoft.baseappretro.R;

import java.lang.reflect.Field;

/**
 * Created by yamil.marques on 1/13/16.
 */
public class BaseActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    protected Toolbar toolBar;
    protected CharSequence mTitle;
    protected CharSequence mSubtitle;

    public Toolbar getToolBar(){
        return toolBar;
    }
    public CoordinatorLayout getCoordinatorLayout(){
        return coordinatorLayout;
    }
    public void setCoordinatorLayout(CoordinatorLayout coordinatorLayout){
        this.coordinatorLayout = coordinatorLayout;
    }

    public void setActionBarTitle(String title) {
        mTitle = title;
        getSupportActionBar().setTitle(title);
    }
    public void setActionBarSubtitle(String subtitle){
        mSubtitle = subtitle;
        getSupportActionBar().setSubtitle(subtitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.base_menu, menu);*/
        addTitleTypeface();
        return super.onCreateOptionsMenu(menu);
    }

    private void addTitleTypeface(){
        try {
            Field f = toolBar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            TextView titleTextView = (TextView) f.get(toolBar);
            Typeface font = Typeface.createFromAsset(this.getAssets(), this.getString(R.string.Selected_typeface));
            titleTextView.setTypeface(font);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void replaceFragment(BaseFragment f, String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment == null || !fragment.isVisible()) {
            transaction.replace(R.id.content_frame, f, tag).addToBackStack(tag).commit();
        }
    }


}

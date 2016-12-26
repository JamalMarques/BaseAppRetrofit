package com.basesoft.baseappretro.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.basesoft.baseappretro.Common.Constants;
import com.basesoft.baseappretro.Fragments.FragmentNavDrawer;
import com.basesoft.baseappretro.Helpers.DialogHelper;
import com.basesoft.baseappretro.Helpers.Utilities;
import com.basesoft.baseappretro.Models.NavDrawerItemAbs;
import com.basesoft.baseappretro.Models.NavItemCategory;
import com.basesoft.baseappretro.Models.NavItemNormal;
import com.basesoft.baseappretro.R;

import java.util.ArrayList;

public class NavDrawerActivity extends BaseActivity implements FragmentNavDrawer.FragmentDrawerListener {

    private DrawerLayout mDrawerLayout;
    private FragmentNavDrawer fragmentNavDrawer;
    private int itemSelectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(getString(R.string.title_activity_nav_drawer));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setCoordinatorLayout((CoordinatorLayout) findViewById(R.id.coordinator_layout));


        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        fragmentNavDrawer = (FragmentNavDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_nav_drawer);
        fragmentNavDrawer.setUpDrawer(R.id.fragment_nav_drawer, mDrawerLayout, toolBar, getNavDrawerConfig(),
                "Mail direction", "Address or another stuff");
        fragmentNavDrawer.setDrawerListener(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(findViewById(R.id.fragment_nav_drawer));
                //replaceFragmentNoBackStack();
            }
        });

        DialogHelper.showCommonErrorDialog(this,"Example of an error message",false,null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                Utilities.hideVirtualKeyBoard(this, getCoordinatorLayout());
                break;
            //Add more stuffs if necessary
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private ArrayList<NavDrawerItemAbs> getNavDrawerConfig(){

        int itemsMenu;
        ArrayList<NavDrawerItemAbs> items = new ArrayList<>();

        items.add(new NavItemNormal(getString(R.string.item_drawer_1), R.drawable.ic_list_home, Constants.ID_MENU_ITEM1));
        items.add(new NavItemNormal(getString(R.string.item_drawer_2), R.drawable.ic_flight_black_24dp, Constants.ID_MENU_ITEM2));

        items.add(new NavItemCategory(getString(R.string.category_drawer_1)));

        items.add(new NavItemNormal(getString(R.string.item_drawer_3),R.drawable.ic_local_cafe_black_24dp,Constants.ID_MENU_ITEM3));
        items.add(new NavItemNormal(getString(R.string.item_drawer_4), R.drawable.ic_wb_cloudy_black_24dp, Constants.ID_MENU_ITEM4));


        items.add(new NavItemNormal(getString(R.string.item_drawer_sing_out), R.drawable.ic_exit_to_app_black_24dp,
                Constants.ID_MENU_SIGN_OUT));

        return items;
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayFragment(position);
    }

    protected void displayFragment(int position){
        ArrayList<NavDrawerItemAbs> items = (((FragmentNavDrawer)fragmentNavDrawer).getRecyclerAdapter()).getItemsArrayList();
        if(items.get(position).getType() == Constants.NAV_ITEM_TYPE_NORMAL){
            NavItemNormal itemNormal = (NavItemNormal)items.get(position);
            selectItem(itemNormal.getId());
        }
    }

    protected void selectItem(int id){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        itemSelectedID = id;
        String link;
        Intent intent;
        switch (id) {
            case Constants.ID_MENU_ITEM1:
                //Do stuffs
                break;
            case Constants.ID_MENU_ITEM2:
                //Do stuffs
                break;
            case Constants.ID_MENU_ITEM3:
                //Do stuffs
                break;
            case Constants.ID_MENU_ITEM4:
                //Do stuffs
                break;
            case Constants.ID_MENU_SIGN_OUT: //log out
                finish();
                break;
        }

        transaction.commit();
        mDrawerLayout.closeDrawers();
    }


    @Override
    public void onBackPressed() {
        /*Fragment fragmentOnTop;
        fragmentOnTop = getSupportFragmentManager().findFragmentByTag(Constants.TAG_DASHBOARD);*/
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        } else {
                super.onBackPressed();
        }
    }


}

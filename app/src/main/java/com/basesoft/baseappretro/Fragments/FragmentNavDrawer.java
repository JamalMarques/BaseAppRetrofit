package com.basesoft.baseappretro.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basesoft.baseappretro.Adapters.RecyclerNavigationDrawerAdapter;
import com.basesoft.baseappretro.Listeners.RecyclerViewListener;
import com.basesoft.baseappretro.Models.NavDrawerItemAbs;
import com.basesoft.baseappretro.R;

import java.util.ArrayList;

/**
 * Created by yamil.marques
 */
public class FragmentNavDrawer extends Fragment {

    public static String TAG = FragmentNavDrawer.class.getSimpleName();

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private RecyclerNavigationDrawerAdapter adapter;
    private View fragmentView;
    private FragmentDrawerListener drawerListener;
    private LinearLayout headerLayout;
    private TextView tvUserName,tvAditionals;
    private LinearLayout layoutImgEdit;
    private ImageView imgEdit;


    public interface FragmentDrawerListener{
        void onDrawerItemSelected(View view, int position);
    }

    public static FragmentNavDrawer getInstance(){
        FragmentNavDrawer frag = new FragmentNavDrawer();
        return frag;
    }

    public void setDrawerListener(FragmentDrawerListener listener,View.OnClickListener editButtonListener){
        this.drawerListener = listener;
        imgEdit.setOnClickListener(editButtonListener);
        layoutImgEdit.setOnClickListener(editButtonListener);
    }


    public RecyclerNavigationDrawerAdapter getRecyclerAdapter(){
        return adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_drawer_layout,container,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do nothing. (Made to avoid "Notifications" triggering from clicking near "Edit" image)
            }
        });

        headerLayout = (LinearLayout)view.findViewById(R.id.main_header);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerItems);
        tvUserName = (TextView)view.findViewById(R.id.tv_user_name);
        tvAditionals = (TextView)view.findViewById(R.id.tv_aditional);
        layoutImgEdit = (LinearLayout)view.findViewById(R.id.layout_img_edit);
        imgEdit = (ImageView)view.findViewById(R.id.img_edit);

        return view;
    }

    public void setUpDrawer(int fragmentDrawerId, final DrawerLayout drawerLayout, final Toolbar toolbar,ArrayList<NavDrawerItemAbs> items,String userName,String aditionals){
        fragmentView = getActivity().findViewById(fragmentDrawerId);
        this.drawerLayout = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });

        adapter = new RecyclerNavigationDrawerAdapter(getActivity(),items);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLM = new LinearLayoutManager(getActivity());
        linearLM.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLM);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new RecyclerViewListener() {
            @Override
            public void recyclerViewOnItemClickListener(View view, int position) {
                if (drawerListener != null) {
                    drawerListener.onDrawerItemSelected(view, position);
                    drawerLayout.closeDrawer(fragmentView);
                }
            }
        });

        tvUserName.setText(userName);
        tvAditionals.setText(aditionals);
    }

}

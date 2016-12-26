package com.basesoft.baseappretro.Adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basesoft.baseappretro.Common.Constants;
import com.basesoft.baseappretro.Listeners.RecyclerViewListener;
import com.basesoft.baseappretro.Models.NavDrawerItemAbs;
import com.basesoft.baseappretro.Models.NavItemNormal;
import com.basesoft.baseappretro.R;


import java.util.ArrayList;

/**
 * Created by yamil.marques on 9/17/15.
 */
public class RecyclerNavigationDrawerAdapter extends RecyclerView.Adapter<RecyclerNavigationDrawerAdapter.ItemViewHolder> {

    private Context context;
    private RecyclerViewListener listener;
    private ArrayList<NavDrawerItemAbs> items;
    private int lastPositionSelected = 0;

    public RecyclerNavigationDrawerAdapter(Context context, ArrayList<NavDrawerItemAbs> items){
        this.context = context;
        this.items = items;
    }

    public void setListener(RecyclerViewListener listener){ this.listener = listener; }

    public ArrayList<NavDrawerItemAbs> getItemsArrayList(){
        return items;
    }

    public void setLastPositionSelected(int positionSelected){
        if(positionSelected < items.size() && positionSelected >= 0){
            lastPositionSelected = positionSelected;
        }
    }

    @Override
    public int getItemViewType(int position) {
        NavDrawerItemAbs auxItem = items.get(position);
        return (auxItem.getType() == Constants.NAV_ITEM_TYPE_NORMAL)? Constants.NAV_ITEM_TYPE_NORMAL : Constants.NAV_ITEM_TYPE_CATEGORY;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView;
        if (viewType == Constants.NAV_ITEM_TYPE_NORMAL) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_item_normal_layout, viewGroup, false);
        }else{
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_item_category_layout, viewGroup, false);
        }
        return new ItemViewHolder(itemView,viewType);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final NavDrawerItemAbs item = items.get(position);
        holder.label.setText(item.getLabel());
        if (item.getType() == Constants.NAV_ITEM_TYPE_NORMAL) {

            int notificationNumber = 8;

            if (item.getId() == Constants.ID_MENU_SIGN_OUT) { //Log out item
                holder.grayLine.setVisibility(View.VISIBLE);
            } else {
                holder.grayLine.setVisibility(View.GONE);
            }


            if (notificationNumber > 0) {
                holder.notificationLayout.setVisibility(View.VISIBLE);
                holder.notificationNumber.setText(String.valueOf(notificationNumber));
            } else {
                holder.notificationLayout.setVisibility(View.GONE);
            }

            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelectableName(items.get(position).getLabel())) {
                        lastPositionSelected = position;
                    }
                    //lastPositionSelected = position;
                    listener.recyclerViewOnItemClickListener(v, position);
                    notifyDataSetChanged();
                }
            });
        }
        selectableState(position, item.getType(), holder, item);
    }

    private boolean isSelectableName(String itemName){
        boolean selectable = true;
        /*if( itemName.equals(context.getString(R.string.nav_menu_shop_products))
            || itemName.equals(context.getString(R.string.nav_menu_earn))
            || itemName.equals(context.getString(R.string.nav_menu_notifications))
            || itemName.equals(context.getString(R.string.nav_menu_reminders))
            || itemName.equals(context.getString(R.string.nav_menu_contact_us))
            || itemName.equals(context.getString(R.string.nav_menu_faq))
            || itemName.equals(context.getString(R.string.nav_menu_privacy))
            || itemName.equals(context.getString(R.string.nav_menu_tips))
            || itemName.equals(context.getString(R.string.nav_menu_privacy)) ){
            selectable = false;
        }*/
        return selectable;
    }

    private void selectableState(int position, int type, ItemViewHolder holder, NavDrawerItemAbs item){
        int labelColor;
        if(type == Constants.NAV_ITEM_TYPE_NORMAL){
            Drawable drawableIcon;
            if (lastPositionSelected == position){
                labelColor = R.color.colorAccent;
                drawableIcon = context.getResources().getDrawable(((NavItemNormal)item).getIconId());
                drawableIcon.setColorFilter(context.getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                drawableIcon.setAlpha(255);
            }
            else{
                labelColor = R.color.gray1_H;
                drawableIcon = context.getResources().getDrawable(((NavItemNormal)item).getIconId());
                drawableIcon.setColorFilter(null);
                drawableIcon.setAlpha(138);
            }
            holder.icon.setImageDrawable(drawableIcon);
        }else{
            labelColor = R.color.gray9_H;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            labelColor = ContextCompat.getColor(context, labelColor);
        } else {
            labelColor = context.getResources().getColor(labelColor);
        }
        holder.label.setTextColor(labelColor);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        LinearLayout mainLayout,notificationLayout;
        View grayLine;
        TextView label,notificationNumber;
        ImageView icon;

        public ItemViewHolder(final View itemView,int itemType) {
            super(itemView);
            mainLayout = (LinearLayout)itemView.findViewById(R.id.main_layout);
            label = (TextView)itemView.findViewById(R.id.tv_label);
            if(itemType == Constants.NAV_ITEM_TYPE_NORMAL){
                notificationLayout = (LinearLayout)itemView.findViewById(R.id.notific_layout);
                notificationNumber = (TextView)itemView.findViewById(R.id.tv_notif_num);
                icon = (ImageView)itemView.findViewById(R.id.img_icon);
                grayLine = (View)itemView.findViewById(R.id.gray_line);
            }
        }
    }
}

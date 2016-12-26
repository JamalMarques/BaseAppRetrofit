package com.basesoft.baseappretro.Fragments;

import android.support.v4.app.Fragment;

/**
 * Created by yamil.marques on 1/18/16.
 */
public class BaseFragment extends Fragment{

    /*
    public LinearLayout cartContentsLayout;
    protected FS_Humana_TextView cartContentsTextNumber;
    protected LinearLayout notificationsLayout;
    protected FS_Humana_TextView notificationsTextNumber;
    protected ImageButton notificationsButton;
    protected ImageButton cartButton;
    protected boolean mIsMenuCreated;*/

    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        mIsMenuCreated = false;
        if (menu.findItem(R.id.actionsMenu) == null) {
            inflater.inflate(R.menu.menu_shop_with_notification, menu);
            mIsMenuCreated = true;
        }
        RelativeLayout menuLayout = (RelativeLayout) menu.findItem(R.id.actionsMenu)
                .getActionView();
        cartContentsLayout = (LinearLayout) menuLayout.findViewById(R.id.cartContentsLayout);
        cartContentsTextNumber = (FS_Humana_TextView) menuLayout
                .findViewById(R.id.cartContentsTextNumber);
        notificationsLayout = (LinearLayout) menuLayout.findViewById(R.id.notificationsLayout);
        notificationsTextNumber = (FS_Humana_TextView) menuLayout
                .findViewById(R.id.notificationsTextNumber);
        notificationsButton = (ImageButton) menuLayout.findViewById(R.id.scanButton);
        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getHumanaRoboActivity(), UpperLevelActivity.class);
                intent.putExtra(Constants.FRAGMENT_ID, Constants.NOTIFICATIONS_FRAGMENT);
                startActivity(intent);
            }
        });
        cartButton = (ImageButton) menuLayout.findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCart = new Intent(getActivity(), UpperLevelActivity.class);
                intentCart.putExtra(Constants.FRAGMENT_ID, Constants.SHOPPING_CART_FRAGMENT);
                startActivity(intentCart);
            }
        });

        if (mIsMenuCreated) {
            checkForNotificationCart();
            checkForNotificationMessages();
        }
    }*/


}

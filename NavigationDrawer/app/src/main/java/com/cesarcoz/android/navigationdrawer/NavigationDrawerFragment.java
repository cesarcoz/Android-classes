package com.cesarcoz.android.navigationdrawer;

import android.app.Fragment;

/**
 * Created by Cesar on 25/04/2015.
 */
public class NavigationDrawerFragment extends Fragment {




    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }
}

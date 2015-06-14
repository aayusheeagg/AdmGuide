package admin.neeraj.com.admguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NavigationFragment extends Fragment {

    DrawerLayout mDrawer;
    Communicator mCommInterface;
    String [] steps = { "Home","Website","Points to Remember","Map","Team Members", "About"};
    ActionBarDrawerToggle mToggle;
    private final static String DRAWER_LEARN = "prefdata";
    private boolean mLearnedDrawer;
    private boolean mSavedInstances;
    View container;
    ListView menu;
    private final static String USER_LEARN_DRAWER_KEY = "user learn drawer";

    public NavigationFragment() {
    }

    public static void savingPrefrences(Context context, String name, String key) {
        SharedPreferences shared = context.getSharedPreferences(DRAWER_LEARN, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        edit.putString(name, key);
        edit.apply();
    }

    public static String readPref(Context context, String name, String Defaultkey) {
        SharedPreferences shared = context.getSharedPreferences(DRAWER_LEARN, Context.MODE_PRIVATE);
        return shared.getString(name, Defaultkey);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLearnedDrawer = Boolean.valueOf(readPref(getActivity(), USER_LEARN_DRAWER_KEY, "false"));
        if (savedInstanceState != null) {
            mSavedInstances = true;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCommInterface = (Communicator) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view= inflater.inflate(R.layout.fragment_navigation, container, false);
        menu = (ListView) view.findViewById(R.id.nativation_drawer_menu);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,steps);
        menu.setAdapter(adapter);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long l) {
                selectItem(positon);
            }
        });
        return view;
    }

    private void selectItem(int positon) {
        Log.d("List item", "Selected ite no "+positon );
        if(mDrawer !=null)
        {
            mDrawer.closeDrawer(container);
        }
        if(mCommInterface!=null)
        {
            mCommInterface.onMenuItemSelected(positon);
        }
    }


    public void setUp(int frag,DrawerLayout drawer, Toolbar toolbar) {
        container =getActivity().findViewById(frag);
        mDrawer = drawer;
        mToggle = new ActionBarDrawerToggle(getActivity(), mDrawer, toolbar,R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                if (!mLearnedDrawer) {
                    mLearnedDrawer = true;
                    savingPrefrences(getActivity(), USER_LEARN_DRAWER_KEY, mLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

        };
        if (!mLearnedDrawer && !mSavedInstances) {
            mDrawer.openDrawer(container);
        }
        mDrawer.setDrawerListener(mToggle);
        mDrawer.post(new Runnable() {
            @Override
            public void run() {
                mToggle.syncState();
            }
        });
    }
     public static interface Communicator{
         public void onMenuItemSelected(int position);
     }

}

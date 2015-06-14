package admin.neeraj.com.admguide;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements NavigationFragment.Communicator {

    ActionBar actionBar;
    MapsActivity mapsActivity;
    Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tool= (Toolbar) findViewById(R.id.tool_b);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationFragment mNavigation = (NavigationFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigation.setUp( R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer),tool);

        Register mRegister = new Register();
        FragmentManager manager =getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.activity_container,mRegister,"mRegister");
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuItemSelected(int position) {
        position=position+1;
        FragmentManager manager =getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        switch (position)
        {
            case 1:
                Register mRegister=new Register();
                transaction.replace(R.id.activity_container,mRegister,"mRegister");
                transaction.commit();
                break;
            case 2:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.mnnit.ac.in"));
                startActivity(i);
                break;
            case 3:
                Points mPoints=new Points();
                transaction.replace(R.id.activity_container,mPoints,"mPoints");
                transaction.addToBackStack("mPoints");
                transaction.commit();
                break;
            case 4:
                Intent ia = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(ia);
                break;
            case 5:
                Team mTeam=new Team();
                transaction.replace(R.id.activity_container,mTeam,"mTeam");
                transaction.addToBackStack("mTeam");
                transaction.commit();
                break;
            case 6:
                About mAbout=new About();
                transaction.replace(R.id.activity_container,mAbout,"mAbout");
                transaction.addToBackStack("mAbout");
                transaction.commit();
                break;
        }
    }
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}

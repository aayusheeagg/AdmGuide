package admin.neeraj.com.admguide;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends Fragment {
	String classes[]={"Step 1", "Step 2", "Step 3"};
    private Class<?>[] newActivities = {Step1.class, Step2.class,Step3.class};
     View v;
    ListView list;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.menu,container,false);
       list= (ListView) v.findViewById(R.id.menu);
        ArrayAdapter adapter =new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,classes);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                FragmentManager manager =getFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                position=position+1;
                switch (position)
                {
                    case 1:
                        Step1 mOne=new Step1();
                        transaction.replace(R.id.activity_container,mOne,"mOne");
                        transaction.addToBackStack("mOne");
                        transaction.commit();
                        break;
                    case 2:
                        Step2 mTwo=new Step2();
                        transaction.replace(R.id.activity_container,mTwo,"mTwo");
                        transaction.addToBackStack("mTwo");
                        transaction.commit();
                        break;
                    case 3:
                        Step3 mThree=new Step3();
                        transaction.replace(R.id.activity_container,mThree,"mThree");
                        transaction.addToBackStack("mThree");
                        transaction.commit();
                        break;
                }
            }
        });
        return v;
    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            return;
        } else {
            getFragmentManager().popBackStack();
        }
    }
}

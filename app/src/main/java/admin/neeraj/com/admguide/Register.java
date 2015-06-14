package admin.neeraj.com.admguide;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Fragment {
    Spinner sp;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.register,container,false);
    sp = (Spinner) v.findViewById(R.id.spinner1);
        final TextView msg = (TextView) v.findViewById(R.id.msg);
        Button Go = (Button) v.findViewById(R.id.go);
        Button Register = (Button) v.findViewById(R.id.register);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(getActivity(), sp.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        Go.setOnClickListener(new View.OnClickListener() {
            FragmentManager manager =getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View arg0) {
                msg.setVisibility(arg0.INVISIBLE);
                Menu mMenu=new Menu();
                transaction.replace(R.id.activity_container,mMenu,"mMenu");
                transaction.commit();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                msg.setVisibility(arg0.VISIBLE);
            }
        });

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.academics.mnnit.ac.in"));
                startActivity(i);
            }
        });

        return v;
    }
}

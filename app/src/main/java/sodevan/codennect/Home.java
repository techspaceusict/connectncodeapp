package sodevan.codennect;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    GridView gv  ;
    FirebaseDatabase database ;
    DatabaseReference reference ;
    FirebaseListAdapter<interestitem> adapter ;
    Context c ;
    public Home() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_home, container, false);

        GridView gv = (GridView)v.findViewById(R.id.pgv) ;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext()) ;
        String uid = sp.getString("name", "error") + sp.getString("no" , "error") ;



        database = FirebaseDatabase.getInstance() ;
        reference = database.getReference("Users").child(uid).child("interests") ;


        adapter = new FirebaseListAdapter<interestitem>(getActivity() , interestitem.class , R.layout.pitem , reference) {

            @Override
            protected void populateView(View v, interestitem model, int position) {

              String name =   model.getName() ;
                Typeface caviar=Typeface.createFromAsset(c.getAssets(),"caviar.ttf");


                TextView tv = (TextView)v.findViewById(R.id.mtv) ;
                tv.setTypeface(caviar);
                tv.setText(name);

            }
        };

        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                interestitem item = (interestitem) parent.getItemAtPosition(position) ;
                String name = item.getName();
                Intent c1 = new Intent( c, Projects.class) ;
                c1.putExtra("name" , name) ;
                startActivity(c1);



            }
        });


        return v ;


    }






    public void setC(Context c) {
        this.c = c;
    }


}

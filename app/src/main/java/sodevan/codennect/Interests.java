package sodevan.codennect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.HashMap;

public class Interests extends AppCompatActivity {

    FirebaseListAdapter<interestitem> adapter ;
    GridView gv ;
    FirebaseDatabase database  ;
    DatabaseReference ref,ref2,ref3 ;

    HashMap<String,interestitem> hm ;
    Button btn1 ;

    CircularImageView circularImageView  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);



        btn1 =(Button)findViewById(R.id.sm)  ;



        hm = new HashMap<>() ;

        gv = (GridView)findViewById(R.id.gridview) ;

        database = FirebaseDatabase.getInstance() ;
        ref = database.getReference("Interests") ;

        SharedPreferences sp  = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()) ;
        String name = sp.getString("name" ,"error") ;
        String no = sp.getString("no" ,"error") ;

        TextView tv =  (TextView) findViewById(R.id.welcomtext) ;


        ref2 = database.getReference("Users").child(name+no).child("interests") ;

        adapter = new FirebaseListAdapter<interestitem>(this ,interestitem.class, R.layout.interests_item , ref ) {
            @Override
            protected void populateView(View v, interestitem a, int position) {



                // controls
                final ToggleButton iname = (ToggleButton) v.findViewById(R.id.iname) ;

                final String name = a.getName() ;
                final HashMap<String , Projectitem> projects ;
                projects = a.getProject() ;


                iname.setText(name);
                iname.setTextOn(name);
                iname.setTextOff(name);

                iname.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        if (b==true) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                iname.setBackgroundDrawable(getDrawable(R.drawable.interests_shape_on));
                            }

                            iname.setTextColor(getResources().getColor(R.color.colori));

                            hm.put(name , new interestitem(name , projects )) ;

                            Log.i(name ,": ON") ;
                        }

                        else {


                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                iname.setBackgroundDrawable(getDrawable(R.drawable.interests_shape));
                            }



                            iname.setTextColor(getResources().getColor(R.color.white));

                            hm.remove(name);

                            Log.i(name ,": OFF") ;




                        }


                    }
                });


            }
        };



        gv.setAdapter(adapter);

        String welcometext = "<font>Welcome</font>  <font color=#1E88E5>"+ name +"</font>";
        tv.setText(Html.fromHtml(welcometext)) ;

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref2.setValue(hm);
                Intent chah = new Intent(getApplicationContext() , Main.class) ;
                startActivity(chah);
                finish();
            }
        });





    }
}

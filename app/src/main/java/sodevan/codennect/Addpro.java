package sodevan.codennect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addpro extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference ,reference2  ;
    Button b  ;
    EditText et1, et2 , et3  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpro);


        b =(Button)findViewById(R.id.bb ) ;
        et1= (EditText)findViewById(R.id.proname) ;
        et2= (EditText)findViewById(R.id.prolink) ;
        et3= (EditText)findViewById(R.id.prod) ;


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this) ;
        String name1= sp.getString("name" , "error") ;
        String no1= sp.getString("no" , "error") ;

        Intent recieve = getIntent() ;
        String name2 =  recieve.getStringExtra("name") ;

        database = FirebaseDatabase.getInstance() ;
        reference = database.getReference("Interests").child(name2).child("project");
        reference2 = database.getReference("Users").child(name1+no1).child("interests").child(name2).child("project")   ;

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proname =et1.getText().toString() ;
                String prodesc = et3.getText().toString() ;
                String prolink = et2.getText().toString() ;

                reference.child(proname).child("desc").setValue(prodesc);
                reference.child(proname).child("name").setValue(proname);
                reference.child(proname).child("url").setValue(prolink);

                reference2.child(proname).child("desc").setValue(prodesc);
                reference2.child(proname).child("name").setValue(proname);
                reference2.child(proname).child("url").setValue(prolink);


            }
        });




    }
}

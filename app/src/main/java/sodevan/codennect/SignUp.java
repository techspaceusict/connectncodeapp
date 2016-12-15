package sodevan.codennect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity implements View.OnFocusChangeListener {

   FirebaseDatabase database ;
    DatabaseReference reference , ref ;
    EditText name , email , college , contactno ;
    Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()) ;
        String Uid = sp.getString("name" , "K3PHIJQL") ;
        if(!Uid.equals("K3PHIJQL")){

            Intent n = new Intent(getApplicationContext() , Main.class);
            startActivity(n);
            finish();
        }

        database = FirebaseDatabase.getInstance()  ;
        reference = database.getReference("Users") ;



        name= (EditText)findViewById(R.id.etname) ;
        email= (EditText)findViewById(R.id.etemail) ;
        college= (EditText)findViewById(R.id.etcollege) ;
        contactno= (EditText)findViewById(R.id.etno) ;
        btn = (Button)findViewById(R.id.Loginbutton) ;


        name.setOnFocusChangeListener(this);
        email.setOnFocusChangeListener(this);
        college.setOnFocusChangeListener(this);
        contactno.setOnFocusChangeListener(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name1 = name.getText().toString() ;
                String no1 = contactno.getText().toString() ;
                String college1 = college.getText().toString() ;
                String  email1= email .getText().toString() ;
                Boolean networkstatus = haveNetworkConnection() ;

                if(name1.equals("") || no1.equals("") || college1.equals("")  || email1.equals("") || !networkstatus){

                    if (name1.equals("") || no1.equals("") || college1.equals("")  || email1.equals(""))
                        Toast.makeText(getApplicationContext(), "Fill The Following Fields", Toast.LENGTH_SHORT).show();

                    else {
                        Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }


                else {

                    reference.child(name1+no1).child("name").setValue(name1) ;
                    reference.child(name1+no1).child("email").setValue(email1) ;
                    reference.child(name1+no1).child("college").setValue(college1) ;
                    reference.child(name1+no1).child("contactno").setValue(no1) ;

                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()) ;
                    sp.edit().putString("email",email1).commit() ;
                    sp.edit().putString("name",name1).commit() ;
                    sp.edit().putString("no",no1).commit() ;
                    sp.edit().putString("college",college1).commit() ;
                    Toast.makeText( getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();


                    Intent change = new Intent(getApplicationContext() , Interests.class) ;
                    startActivity(change);
                    finish();

                }

            }
        });



    }




    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if(hasFocus){
            v.setBackgroundResource(R.drawable.etshapefocus);
        }

        else {
            v.setBackgroundResource(R.drawable.etshape);

        }

    }
}

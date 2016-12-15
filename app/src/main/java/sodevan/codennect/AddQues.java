package sodevan.codennect;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddQues extends AppCompatActivity {

    FirebaseDatabase database ;
    DatabaseReference reference , ref ;
    EditText et1 ;
    Button btn1 ;
    int Date ;
    String month_name  , qid ;
    String Uid ;
    String name  ;
    String no  ;
    HashMap<String ,Answerchild>  answers ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ques);

        long i = 0;
       answers = new HashMap<>() ;
        answers.put("noAns" ,new Answerchild("Be First To Answer This Question" , i ,  "",  "" ))  ;
        database = FirebaseDatabase.getInstance()  ;
        reference = database.getReference("ForumQues") ;

        et1 = (EditText)findViewById(R.id.ask_ques) ;
        btn1 = (Button)findViewById(R.id.ask_button) ;





        Calendar cal = Calendar.getInstance();
        Date  =  cal.get(Calendar.DATE);
        final SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        month_name = month_date.format(cal.getTime());

        SharedPreferences sp  = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()) ;
          Uid = sp.getString("email", "error1") ;
          name = sp.getString("name" , "error2") ;
          no  = sp.getString("no" , "error3") ;


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Title = et1.getText().toString() ;
                Boolean networkstatus = haveNetworkConnection() ;

                   if( Title.equals("") || Uid.equals("error1")  || name.equals("error2") || no.equals("error3") || !networkstatus) {

                       if (Title.equals(""))
                           Toast.makeText(getApplicationContext() , "Type something Before Submitting", Toast.LENGTH_SHORT).show();


                       else if (!networkstatus){
                           Toast.makeText(getApplicationContext() , "Connection Problem", Toast.LENGTH_SHORT).show();
                       }

                       else
                           Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();

                   }

                else {
                       String qid = Title+no ;
                       ref = reference.child(qid) ;
                       ref.child("answers").setValue(answers) ;
                       ref.child("by").setValue(name);
                       ref.child("date").setValue(Date) ;
                       ref.child("month").setValue(month_name) ;
                       ref.child("qid").setValue(qid) ;
                       ref.child("bestAnswer").setValue("noAns") ;
                       ref.child("title").setValue(Title) ;

                       Toast.makeText(getApplicationContext(), "Question Added", Toast.LENGTH_SHORT).show();
                       finish();



                   }

            }
        });

    }

    private boolean haveNetworkConnection() {
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
}

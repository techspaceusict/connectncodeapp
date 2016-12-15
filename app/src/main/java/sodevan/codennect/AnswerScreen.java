package sodevan.codennect;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AnswerScreen extends AppCompatActivity {

    Button bta;
    EditText answer  ;
    TextView tv1 ;
    int Date ;
    String month_name ;

    FirebaseDatabase database   ;
    DatabaseReference reference , reference2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_screen);

        database = FirebaseDatabase.getInstance()  ;

        tv1 = (TextView)findViewById(R.id.quesatans) ;
        answer = (EditText)findViewById(R.id.etans) ;
        bta =(Button)findViewById(R.id.submitans) ;


        Intent recieve = getIntent() ;

        String ques = recieve.getStringExtra("Ques") ;
        tv1.setText(ques);




           Calendar cal = Calendar.getInstance();
            Date  =  cal.get(Calendar.DATE);
            SimpleDateFormat month_date = new SimpleDateFormat("MMM");
            month_name = month_date.format(cal.getTime());







        String qid = recieve.getStringExtra("qid") ;
        final String name = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("name", "error") ;
        final String Uid = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("email", "error1") ;

        reference = database.getReference("ForumQues").child(qid) ;
        reference2 = reference.child("answers").child(Uid) ;


        bta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             String answr =   answer.getText().toString() ;
                Boolean networkstatus = haveNetworkConnection() ;

                if(answr.equals("") || Uid.equals("error1")  || name.equals("error") || !networkstatus) {

                    if (answr.equals(""))
                    Toast.makeText(getApplicationContext() , "Type something Before Submitting", Toast.LENGTH_SHORT).show();

                    else if(!networkstatus){
                        Toast.makeText(getApplicationContext() , "Connection Problem", Toast.LENGTH_SHORT).show();
                    }


                    else
                        Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();

                }

                else {

                    reference2.child("answer").setValue(answr) ;
                    reference2.child("date").setValue(Date) ;
                    reference2.child("month").setValue(month_name) ;
                    reference2.child("name").setValue(name) ;
                    reference.child("bestAnswer").setValue(Uid) ;
                    reference.child("answers").child("noAns").removeValue() ;

                    Toast.makeText(getApplicationContext(), "Successfull Submitted", Toast.LENGTH_SHORT).show();
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

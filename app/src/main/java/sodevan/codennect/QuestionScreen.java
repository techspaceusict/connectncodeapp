package sodevan.codennect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuestionScreen extends AppCompatActivity  {

    FirebaseDatabase database ;
    DatabaseReference reference ;
    TextView tv ;
    ListView ltv ;
    String status ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);


        database = FirebaseDatabase.getInstance() ;

        Intent recieve  = getIntent() ;
        String qid = recieve.getStringExtra("qid") ;
        String ques = recieve.getStringExtra("ques") ;

        tv = (TextView)findViewById(R.id.ques_ques)  ;
        tv.setText(ques);
        ltv = (ListView)findViewById(R.id.ques_sc_lv) ;

        reference = database.getReference("ForumQues").child(qid).child("answers") ;

           FirebaseListAdapter<Answerchild> adapter = new FirebaseListAdapter<Answerchild>(this, Answerchild.class , R.layout.question_answer_child , reference ) {
            @Override
            protected void populateView(View v, Answerchild model, int position) {

                // views

                TextView ansnametv = (TextView) v.findViewById(R.id.ANSname) ;
                TextView ansdatetv = (TextView) v.findViewById(R.id.Ansdate) ;
                TextView anstv       = (TextView) v.findViewById(R.id.ques_ans) ;

                String ansname = model.getName() ;
                Long ansdate = model.getDate() ;
                String ansmonth = model.getMonth() ;
                String ans  = model.getAnswer() ;


                ImageView im = (ImageView)v.findViewById(R.id.ANSim) ;


                ansnametv.setText(ansname);
                ansdatetv.setText(ansmonth+" "+ansdate );
                anstv.setText(ans);

                if (ansname.equals("")){

                    ansdatetv.setText("");
                }

                else {
                    im.setImageResource(R.drawable.ic_account_circle_black_48dp);

                }






            }
        } ;

        ltv.setAdapter(adapter);

    }
}

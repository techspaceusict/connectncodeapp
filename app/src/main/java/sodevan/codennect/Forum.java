package sodevan.codennect;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Forum extends Fragment {

    Context c ;
    FirebaseDatabase database ;
    DatabaseReference reference ;
    FloatingActionButton fab  ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View V = inflater.inflate(R.layout.activity_forum ,container  , false) ;

        database = FirebaseDatabase.getInstance() ;
        reference = database.getReference("ForumQues")  ;

        ListView lv = (ListView) V.findViewById(R.id.forumques) ;

        fab = (FloatingActionButton)V.findViewById(R.id.fab) ;




        FirebaseListAdapter<Questionchild> questionAdapter = new FirebaseListAdapter<Questionchild>(getActivity() ,Questionchild.class , R.layout.forum_child, reference ) {
            @Override
            protected void populateView(View v, Questionchild qc, int position) {

                HashMap<String ,Answerchild> answers = new HashMap<>() ;
                answers = qc.getAnswers() ;
                String By = qc.getBy() ;
                By = "Asked By : "+By ;
                Long Date = qc.getDate() ;
                String Month = qc.getMonth() ;
                final String Title = qc.getTitle() ;
                String BestAnswer = qc.getBestAnswer()  ;// uid who have given best answer
                final String qid = qc.getQid() ;
                ImageView im = (ImageView)v.findViewById(R.id.ansim) ;
                String dday = Month +" "+ Date  ;
                String status =  qc.getStatus() ;

                Answerchild answerchild = answers.get(BestAnswer);

                TextView ansnametv = (TextView) v.findViewById(R.id.ansname);
                TextView ansdatetv = (TextView) v.findViewById(R.id.ansdate);
                TextView answertv = (TextView) v.findViewById(R.id.ans);

                String ansname = answerchild.getName();
                Long ansdate = answerchild.getDate();
                String ansmonth = answerchild.getMonth();
                String ansdday = ansmonth + " " + ansdate;
                final String answer = answerchild.getAnswer();
                ansnametv.setText(ansname);
                ansdatetv.setText(ansdday);
                answertv.setText(answer);

                if (ansname.equals("")){

                    ansdatetv.setText("");
                }

                else {
                    im.setImageResource(R.drawable.ic_account_circle_black_48dp);

                }

                TextView Bytv = (TextView)v.findViewById(R.id.By) ;
                TextView Datetv = (TextView)v.findViewById(R.id.date) ;
                TextView  questv = (TextView)v.findViewById(R.id.ques) ;

                Button ansbtn = (Button) v.findViewById(R.id.ansbtn) ;

                ansbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.d(" TEST : " , " BUTTON" ) ;
                        Intent answerscreen = new Intent(getContext(), AnswerScreen.class) ;
                        answerscreen.putExtra("Ques" , Title ) ;
                        answerscreen.putExtra("qid" , qid) ;

                        startActivity(answerscreen);

                    }
                });



                Bytv.setText(By);
                Datetv.setText(dday);
                questv.setText(Title);




                Typeface questf = Typeface.createFromAsset(c.getAssets() , "Lalezar-Regular.ttf") ;

                questv.setTypeface(questf);


            }
        };


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent n1 = new Intent(getContext() , AddQues.class) ;
                startActivity(n1);

            }
        });


        lv.setAdapter(questionAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Questionchild q =(Questionchild)parent.getItemAtPosition(position) ;
                String ques =  q.getTitle() ;
                String qid = q.getQid() ;
                String status = q.getStatus() ;
                Intent quesscreen = new Intent(getContext(),QuestionScreen.class) ;
                quesscreen.putExtra("ques", ques) ;
                quesscreen.putExtra("qid" , qid) ;
                quesscreen.putExtra("status" , status) ;
                startActivity(quesscreen);
                Log.d("ques" , ques) ;
                Log.d("qid" , qid) ;

            }
        });




        return V ;
    }



    public void setC(Context c) {
        this.c = c;
    }





}



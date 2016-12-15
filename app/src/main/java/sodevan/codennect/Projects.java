package sodevan.codennect;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Projects extends AppCompatActivity {

    FirebaseDatabase database ;
    DatabaseReference reference ;
    FirebaseListAdapter<Projectitem> adapter ;
    ListView lv ;
    FloatingActionButton button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        Intent recieve = getIntent()  ;
        final String name = recieve.getStringExtra("name") ;


        lv = (ListView)findViewById(R.id.nnlv) ;
        button =(FloatingActionButton)findViewById(R.id.fab2 ) ;






        database = FirebaseDatabase.getInstance()  ;
        reference = database.getReference("Interests").child(name).child("project") ;

        adapter = new FirebaseListAdapter<Projectitem>(this, Projectitem.class , R.layout.projectitem  , reference) {
            @Override
            protected void populateView(View v, Projectitem model, int position) {

             String name =    model.getName() ;
             String college =    model.getCollege() ;
             String url =    model.getUrl() ;
             String desc =   model.getDesc() ;

                Typeface caviar = Typeface.createFromAsset(getAssets() , "caviar.ttf") ;
                Typeface robotto = Typeface.createFromAsset(getAssets() , "roboto.ttf") ;

                TextView nametv = (TextView)v.findViewById(R.id.pname) ;
                TextView collegetv = (TextView)v.findViewById(R.id.college) ;
                TextView dectv = (TextView)v.findViewById(R.id.dec ) ;

                nametv.setTypeface(robotto);
                collegetv.setTypeface(caviar);
                nametv.setText(name);
                collegetv.setText(college);
                dectv.setText(desc);


            }
        } ;

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Projectitem item = (Projectitem) parent.getItemAtPosition(position) ;
             String url =    item.getUrl() ;

                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    getApplicationContext().startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    intent.setPackage(null);
                    getApplicationContext().startActivity(intent);
                }
            }


        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hj = new Intent(getApplicationContext(),Addpro.class) ;
                hj.putExtra("name",name) ;
                startActivity(hj);
            }
        });




    }




}

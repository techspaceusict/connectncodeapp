package sodevan.codennect;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private  static long splashTime = 3000 ;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv1=(TextView)findViewById(R.id.appname);
        tv2=(TextView)findViewById(R.id.tagline);
        Typeface moonbold=Typeface.createFromAsset(getAssets(),"moonbold.ttf");
        Typeface moonlight=Typeface.createFromAsset(getAssets(),"moonlight.ttf");

        tv2.setTypeface(moonlight);
        tv1.setTypeface(moonbold);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent changeIntent = new Intent(getApplicationContext() , SignUp.class)  ;
                startActivity(changeIntent);
                finish();
            }
        },splashTime) ;
    }
}

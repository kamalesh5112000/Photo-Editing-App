package pec.cse18cs1031.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SplachScreen extends AppCompatActivity {
    ImageView img;
    TextView textView;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);
        img=findViewById(R.id.logoimage);
        textView=findViewById(R.id.textView2);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplachScreen.this,Fragment_Activity.class);
                startActivity(i);

                finish();
            }
        },2000);

        img.setTranslationY(300);
        textView.setTranslationY(300);

        img.setAlpha(v);
        textView.setAlpha(v);
        img.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        textView.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();


    }
}
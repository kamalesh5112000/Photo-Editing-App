package pec.cse18cs1031.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import pec.cse18cs1031.fragments.databinding.ActivityResultBinding;

public class PictureResult extends AppCompatActivity {

    ImageView img;
    ImageView sharebtn,backbtn,homebtn;

    ActivityResultBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_result);
        img = findViewById(R.id.Pictureresult);
        sharebtn = findViewById(R.id.sharebtn);
        backbtn=findViewById(R.id.backbtn);
        homebtn = findViewById(R.id.homebtn);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            String uri_Str= b.getString("picname");
            Uri uri = Uri.parse(uri_Str);
            Toast.makeText(getApplicationContext(),"Photo Saved"+uri,Toast.LENGTH_LONG).show();
            img.setImageURI(uri);
        }
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PictureResult.this,ResultActivity.class);
                startActivity(intent);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PictureResult.this,MainActivity.class);
                startActivity(intent);
            }
        });
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Drawable drawable=img.getDrawable();
                Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();

                try {
                    File file = new File(getApplicationContext().getExternalCacheDir(), File.separator +"image.jpg");
                    FileOutputStream fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true, false);
                    final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID +".provider", file);

                    intent.putExtra(Intent.EXTRA_STREAM, photoURI);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setType("image/jpg");

                    startActivity(Intent.createChooser(intent, "Share image via"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        //Uri uri = getIntent().getStringExtra("picname");



    }
}
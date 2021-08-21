package pec.cse18cs1031.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;

import pec.cse18cs1031.fragments.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    int IMAGE_REQUEST_CODE = 45;
    int CAMERA_REQUEST_CODE = 102;
    int RESULT_REQUEST_CODE = 45;
    public String name;

    TextView username,namefield;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namefield = findViewById(R.id.mainname);

        username = (TextView) findViewById(R.id.textView6);
        Intent i = getIntent();
        name = i.getStringExtra("namekey");

        username.setText(name);
        namefield.setText(name);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                startActivity(intent);
            }
        });

        binding.camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) !=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},32);

                }else {
                    Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraintent,CAMERA_REQUEST_CODE);
                }
            }
        });

        if(savedInstanceState !=null){
           name= savedInstanceState.getString("data");
           username.setText(name);
            Toast.makeText(this, name+", Don't Rotate Your Screen", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    protected void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data",name);
    }


    private void checkPermission(){
        int permission= ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.Q){

            pickImage();
        }
        else{
            if(permission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);

            }else{
                pickImage();
            }
        }
    }
    private void pickImage(){
        Intent intent= new Intent(Intent.ACTION_PICK);

        intent.setType("image/*");

        startActivityForResult(intent,100);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            pickImage();
        }else{
            Toast.makeText(getApplicationContext(),"Permission Denied.",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Uri uri = data.getData();
            switch(requestCode){
                case 100:
                {
                    Intent intent = new Intent(MainActivity.this, DsPhotoEditorActivity.class);
                    intent.setData(uri);
                    intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY,"Images");

                    intent.putExtra(DsPhotoEditorConstants.DS_TOOL_BAR_BACKGROUND_COLOR, Color.parseColor("#FF6200EE"));
                    intent.putExtra(DsPhotoEditorConstants.DS_MAIN_BACKGROUND_COLOR,Color.parseColor("#FFFFFF"));

                    intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE,new int[]{DsPhotoEditorActivity.TOOL_WARMTH,DsPhotoEditorActivity.TOOL_PIXELATE});
                    startActivityForResult(intent,101);
                    break;
                }
                case 101:
                {
                    Intent intent = new Intent(MainActivity.this,PictureResult.class);
                    intent.setData(uri);
                    intent.putExtra("picname",uri.toString());
                    startActivity(intent);
                    break;
                }
                case 102:
                {
                    Bitmap photo =(Bitmap) data.getExtras().get("data");
                    Uri uri1 = getImageUri(photo);
                    Intent intent = new Intent(MainActivity.this, DsPhotoEditorActivity.class);
                    intent.setData(uri1);
                    intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY,"Images");

                    intent.putExtra(DsPhotoEditorConstants.DS_TOOL_BAR_BACKGROUND_COLOR, Color.parseColor("#FF6200EE"));
                    intent.putExtra(DsPhotoEditorConstants.DS_MAIN_BACKGROUND_COLOR,Color.parseColor("#FFFFFF"));

                    intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE,new int[]{DsPhotoEditorActivity.TOOL_WARMTH,DsPhotoEditorActivity.TOOL_PIXELATE});
                    startActivityForResult(intent,101);
                    break;
                }

            }
        }
    }
    public Uri getImageUri(Bitmap bitmap){
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,arrayOutputStream);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);

    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST_CODE){
            if(data.getData()!=null){
                Uri filepath = data.getData();
                Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);

                dsPhotoEditorIntent.setData(filepath);
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "PixelEdit");
                int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
                startActivityForResult(dsPhotoEditorIntent,200);
            }
        }
        if(requestCode == 200){
            Intent intent = new Intent(MainActivity.this,PictureResult.class);
            intent.setData(data.getData());
            startActivity(intent);
        }
    }*/
}
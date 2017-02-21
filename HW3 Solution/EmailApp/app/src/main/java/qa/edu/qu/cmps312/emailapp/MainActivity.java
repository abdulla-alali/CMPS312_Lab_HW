package qa.edu.qu.cmps312.emailapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import static android.R.attr.data;

public class MainActivity extends Activity {

    private static final Integer EMAIL_CODE = 2;
    String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView subject = (TextView) findViewById(R.id.subjectField);
        TextView body = (TextView) findViewById(R.id.bodyField);



        if (getIntent().getExtras().getString(Intent.EXTRA_SUBJECT) !=null){
        subject.setText(getIntent().getExtras().getString(Intent.EXTRA_SUBJECT));
            body.setText(getIntent().getExtras().getString(Intent.EXTRA_TEXT));
            body.setMovementMethod(new ScrollingMovementMethod());
            int permCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permCheck == PackageManager.PERMISSION_GRANTED) {
                showAttachment();
            } else {
                ActivityCompat.requestPermissions(this, permissions, EMAIL_CODE);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == EMAIL_CODE && permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           showAttachment();
        }
    }

    public void showAttachment(){
        Bitmap bmp = null;

        Uri imageUri =  getIntent().getExtras().getParcelable(Intent.EXTRA_STREAM);

        ImageView attachement = (ImageView) findViewById(R.id.attachment);

        bmp = BitmapFactory.decodeFile(imageUri.toString().substring(7));

        attachement.setImageBitmap(bmp);
    }
}

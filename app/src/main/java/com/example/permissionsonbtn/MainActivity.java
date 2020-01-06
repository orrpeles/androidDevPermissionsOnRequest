package com.example.permissionsonbtn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
// reference: https://www.youtube.com/watch?v=tXPi1F2BNOA&t=12s
public class MainActivity extends AppCompatActivity {
    // create btn object
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize button
        btn = findViewById(R.id.getPermissionBtn);
        //setOnClickListener View
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission_ALL = 1;
                //set the order of permissions with which the app will be requesting:
                String [] permission = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
                if(!hasPermission(MainActivity.this, permission))
                {
                    ActivityCompat.requestPermissions(MainActivity.this,permission,permission_ALL);
                }
            }
        });
    }
    // create a method for permission
    public static boolean hasPermission(Context context, String... permissions)
    { // check if version is M (Marshmallow) or higher)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M &&
        context!=null && permissions!=null)
        {
            for (String permission : permissions)
            {
                if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }
}

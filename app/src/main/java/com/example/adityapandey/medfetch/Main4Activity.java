package com.example.adityapandey.medfetch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.glxn.qrgen.android.QRCode;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent = getIntent();
        String uuid = intent.getStringExtra(MainActivity.EXTRA_MESSAGE1);
        Bitmap myBitmap= QRCode.from(uuid).bitmap();
        ImageView ib = findViewById(R.id.imageView2);
        ib.setImageBitmap(myBitmap);
        final Button b1 = findViewById(R.id.button6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Main4Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

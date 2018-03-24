package com.example.adityapandey.medfetch;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE1 = "com.example.adityapandey.medfetch.MESSAGE";
    EditText[] EditTextVariable = new EditText[6];
    String[] drug_list = {null};
    String place=null ;
    int EditTextCount = 0;
    int topMargin = 450;
    List<EditText> allEds = new ArrayList<EditText>();

    SharedPreferences prefs = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_page);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                setContentView(R.layout.main);

                //adding extra search fields when checkbox is checked
                final CheckBox cb1 = findViewById(R.id.checkBox);
                final Button    b1 = findViewById(R.id.button7);
                b1.setVisibility(View.INVISIBLE);
                final TextView tv3 = findViewById(R.id.textView3);
                //realigning textFields
                RelativeLayout.LayoutParams tvLayout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tvLayout.setMargins(100,800,0,50);
                tv3.setLayoutParams(tvLayout);
                cb1.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (cb1.isChecked()){
                            b1.setVisibility(View.VISIBLE);
                            RelativeLayout.LayoutParams tvLayout1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            tvLayout1.setMargins(100,1000,0,50);
                            tv3.setLayoutParams(tvLayout1);
                            //creating a new EditText-button
                            b1.setOnClickListener(new OnClickListener() {
                                @Override
                                //creating a new EditText
                                public void onClick(View view) {
                                    if (EditTextCount < 5) {
                                        generateEditTextField(EditTextVariable[EditTextCount], topMargin);
                                        EditTextCount++;
                                        topMargin = topMargin + 100;
                                    }
                                }
                            });
                        }
                        else {
                            final TextView tv3 = findViewById(R.id.textView3);
                            RelativeLayout.LayoutParams tvLayout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            tvLayout.setMargins(100,800,0,50);
                            tv3.setLayoutParams(tvLayout);
                            b1.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                //starts intents and takes you to result pag
                //not to be edited any further
                final Button button = findViewById(R.id.button);
                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText editText1 =  findViewById(R.id.editText);
                        EditText editText2 =  findViewById(R.id.editText2);
                      if (EditTextCount==0) {
                          String message1 = editText1.getText().toString();
                          String message2 = editText2.getText().toString();
                          String message = message1 + "," + message2;
                          Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                          intent1.putExtra(EXTRA_MESSAGE1, message);
                          startActivity(intent1);
                      }
                      else {
                          //these are for test
                          drug_list[0]=String.valueOf(editText1.getText());
                          for(int i=1;i<allEds.size();i++){
                              if (allEds.get(i).getText()!=null) {
                                  drug_list[i] = allEds.get(i).getText().toString();
                              }
                              else {
                                  drug_list[i]=null;
                              }
                          }
                          place = editText2.getText().toString();
                          //end test
                          Intent intent2 = new Intent(MainActivity.this, Main3Activity.class);
                          startActivity(intent2);
                      }
                    }
                });


                

            }
        }, 3000);

        prefs = getSharedPreferences("com.example.adityapandey.medfetch", MODE_PRIVATE);




        }
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            setContentView(R.layout.initial_page);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    setContentView(R.layout.activity_main22);
                    final Button bttn = findViewById(R.id.button2);
                    bttn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setContentView(R.layout.activity_main23);
                            final Button bttn1 = findViewById(R.id.button3);
                            bttn1.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    setContentView(R.layout.main);
                                    final Button button = findViewById(R.id.button);
                                    final CheckBox cb1 = findViewById(R.id.checkBox);
                                    final Button    b1 = findViewById(R.id.button7);
                                    b1.setVisibility(View.INVISIBLE);
                                    final TextView tv3 = findViewById(R.id.textView3);
                                    //realigning textFields
                                    RelativeLayout.LayoutParams tvLayout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    tvLayout.setMargins(100,800,0,50);
                                    tv3.setLayoutParams(tvLayout);
                                    cb1.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (cb1.isChecked()){
                                                b1.setVisibility(View.VISIBLE);
                                                RelativeLayout.LayoutParams tvLayout1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                tvLayout1.setMargins(100,1000,0,50);
                                                tv3.setLayoutParams(tvLayout1);
                                                //creating a new EditText-button
                                                b1.setOnClickListener(new OnClickListener() {
                                                    @Override
                                                    //creating a new EditText
                                                    public void onClick(View view) {
                                                        if (EditTextCount < 5) {
                                                            generateEditTextField(EditTextVariable[EditTextCount], topMargin);
                                                            EditTextCount++;
                                                            topMargin = topMargin + 100;
                                                        }
                                                    }
                                                });
                                            }
                                            else {
                                                final TextView tv3 = findViewById(R.id.textView3);
                                                RelativeLayout.LayoutParams tvLayout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                tvLayout.setMargins(100,800,0,50);
                                                tv3.setLayoutParams(tvLayout);
                                                b1.setVisibility(View.INVISIBLE);
                                            }
                                        }
                                    });

                                    button.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            EditText editText1 =  findViewById(R.id.editText);
                                            EditText editText2 =  findViewById(R.id.editText2);
                                            if (EditTextCount==0) {
                                                String message1 = editText1.getText().toString();
                                                String message2 = editText2.getText().toString();
                                                String message = message1 + "," + message2;
                                                Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                                                intent1.putExtra(EXTRA_MESSAGE1, message);
                                                startActivity(intent1);
                                            }
                                            else {
                                                //these are for test
                                                drug_list[0]=String.valueOf(editText1.getText());
                                                for(int i=1;i<allEds.size();i++){
                                                    if (allEds.get(i).getText()!=null) {
                                                        drug_list[i] = allEds.get(i).getText().toString();
                                                    }
                                                    else {
                                                        drug_list[i]=null;
                                                    }
                                                }
                                                place = editText2.getText().toString();
                                                //end test
                                                Intent intent2 = new Intent(MainActivity.this, Main3Activity.class);
                                                startActivity(intent2);
                                            }

                                        }
                                    });


                                }
                            });
                        }
                    } );
                    prefs.edit().putBoolean("firstrun", false).apply();

                }
            }, 3000);

        }

    }
    //generate_EditText_fields
    public  void  generateEditTextField(EditText et,int top_margin){
        et = new EditText(MainActivity.this);
        RelativeLayout.LayoutParams el_layout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        el_layout.setMargins(610,top_margin,0,50);
        el_layout.width=410;
        et.setId(EditTextCount);
        et.setLayoutParams(el_layout);
        et.setHint("drug name");
        et.setTextSize(14);
        allEds.add(et);
        RelativeLayout rl = findViewById(R.id.rl1);
        rl.addView(et);
    }
}


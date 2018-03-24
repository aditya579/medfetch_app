package com.example.adityapandey.medfetch;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class show_details extends AppCompatActivity {
    String[] values = new  String [4];
    public static final String EXTRA_MESSAGE2 = "com.example.adityapandey.medfetch.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(MainActivity.EXTRA_MESSAGE1);
         values = message.split(",");

        TextView tv1 = findViewById(R.id.textView11);
        tv1.setText(values[1]);
        TextView tv2 = findViewById(R.id.textView10);
        tv2.setText(values[0]);
        final EditText et1 = findViewById(R.id.editText3);


        final Button b1 = findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new fetch_result().execute();
            }
        });

    }
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-"," ") ;
    }
    private class fetch_result extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void doInBackground(Void... arg0) {
            String order_id = generateString();

            final EditText et = findViewById(R.id.editText3);
            httphandler sh = new httphandler();
            // Making a request to url and getting response
            String url = "http://shopkeeper.medfetch.co/shop_order.php?drug="+values[2]+"&company="+values[1]+"&shop_name="+values[0]+"&quantity="+Integer.valueOf(String.valueOf(et.getText()))+"&uuid="+order_id;
            String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {

                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    if(jsonObj.getString("status").equals("success")){
                        Intent intent = new Intent(show_details.this,Main4Activity.class);
                        intent.putExtra(EXTRA_MESSAGE2,order_id);
                        startActivity(intent);
                    }

                } catch (final JSONException e) {
                   e.printStackTrace();


                }

            } else {
                Toast.makeText(show_details.this, "NO DATA FOUND", Toast.LENGTH_LONG).show();
            }
            return null;
        }
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }
}

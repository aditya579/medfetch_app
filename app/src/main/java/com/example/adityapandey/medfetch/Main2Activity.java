package com.example.adityapandey.medfetch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    TextView tv;
    private ListView lv;
    ArrayList<HashMap<String, String>> shopList;
    String drug;
    String place;
    JSONObject jsonObj;
    public static final String EXTRA_MESSAGE1 = "com.example.adityapandey.medfetch.MESSAGE";
    String[] shop_names = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_result);
        Intent intent1 = getIntent();
         String message = intent1.getStringExtra(MainActivity.EXTRA_MESSAGE1);
        String[] values = message.split(",");
        drug= values[0];
        place= values[1];
        lv =  findViewById(R.id.list_view);
        shopList = new ArrayList<>();
        new GetContacts().execute();


    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Main2Activity.this, "Data is downloading", Toast.LENGTH_LONG).show();

        }

        protected Void doInBackground(Void... arg0) {
            String previous=null;
            String next;
            httphandler sh = new httphandler();
            // Making a request to url and getting response
            String url = "http://shopkeeper.medfetch.co/medfetch_get_data.php?drug="+drug+"&place="+place;
            String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {

                try {
                    jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray query = jsonObj.getJSONArray("shop_number");
                        for (int i = 0; i < query.length(); i++) {
                            JSONObject c = query.getJSONObject(i);
                            String shop_name = c.getString(String.valueOf(i + 1));
                            shop_names[i]=shop_name;
                            if (shop_name.equals(previous)) {
                            } else {
                                JSONArray shop_detail = jsonObj.getJSONArray(shop_name);
                                for (int e = 0; e < shop_detail.length() / 5; e++) {
                                    HashMap<String, String> result;
                                    JSONObject d = shop_detail.getJSONObject(0 + e * 5);
                                    String drug_name = d.getString("drug_name");
                                    JSONObject d1 = shop_detail.getJSONObject(1 + e * 5);
                                    String company = d1.getString("company");
                                    JSONObject d2 = shop_detail.getJSONObject(2 + e * 5);
                                    String quantity = d2.getString("quantity");
                                    JSONObject d3 = shop_detail.getJSONObject(3 + e * 5);
                                    String cost_effectiveness = d3.getString("cost_effectiveness");
                                    JSONObject d4 = shop_detail.getJSONObject(4 + e * 5);
                                    String rate = d4.getString("rate");
                                    result = new HashMap<>();
                                    result.put("shop name", "shop name: \t" + shop_name);
                                    result.put("drug_name", "drug name: \t" + drug_name);
                                    result.put("company", "company: \t" + company);
                                    result.put("quantity", "quantity: \t" + quantity);
                                    result.put("cost_effectiveness", "cost effectiveness: \t" + cost_effectiveness);
                                    result.put("rate","rate:\t"+ rate);
                                    shopList.add(result);
                                }
                                // adding contact to contact list

                                previous = shop_name;
                            }
                        }
                    }
                 catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());


                }

            }
            else{
                Toast.makeText(Main2Activity.this,"NO DATA FOUND",Toast.LENGTH_LONG).show();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(Main2Activity.this, shopList,
                    R.layout.list_item, new String[]{"shop name", "drug_name","company","quantity","cost_effectiveness","rate"},
                    new int[]{R.id.shop_name, R.id.drug_name,R.id.company,R.id.quantity,R.id.cost_effectiveness,R.id.rate});
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    int position1 = 0;
                    String company=null;
                    JSONObject d1;
                    try {

                        for(int i=0;i<shop_names.length;i++){
                           if (shop_names[i].equals(shop_names[position]) ){
                                position1=i;
                                break;
                            }
                        }
                        int difference = position-position1;
                        JSONArray result = jsonObj.getJSONArray(shop_names[position1]);
                         d1 = result.getJSONObject(1 + difference*5);
                         company = d1.getString("company");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intent1 = new Intent(Main2Activity.this, show_details.class);
                    String message = shop_names[position]+","+company+","+drug;
                    intent1.putExtra(EXTRA_MESSAGE1, message);
                    Toast.makeText(getBaseContext(), "taking you to transaction page", Toast.LENGTH_LONG).show();
                    startActivity(intent1);

                }
            });


        }


    }
}


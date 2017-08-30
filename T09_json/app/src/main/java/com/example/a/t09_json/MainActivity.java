package com.example.a.t09_json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    String str = "[{'name':'kim', 'tel':'010-1111-2222','age':'30'}"+
            "{'name':'park', 'tel':'010-2222-3333'}]";

    private void test(){

        try {
            JSONArray arr = new JSONArray(str);
            for(int i=0; i<arr.length(); i++){
                JSONObject object = arr.getJSONObject(i);
                String name = object.getString("name");
                String tel = object.getString("tel");
                int age = object.getInt("age");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.androidhive.info/contacts/",null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray contacts = response.getJSONArray("contacts");
                    for(int i=0; i<contacts.length(); i++){
                        JSONObject contact = contacts.getJSONObject(i);
                        Log.d("json","name:"+contact.getString("name"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });

    }
}

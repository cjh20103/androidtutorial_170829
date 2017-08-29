package com.example.a.t06_asynctesk;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    class MyTask extends AsyncTask<Integer, Float, String>{

        ProgressDialog dig = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dig.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dig.dismiss();
            Toast.makeText(MainActivity.this,"res : "+s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            super.onProgressUpdate(values);
            textView.setText(""+values[0]);
        }

        // thread 해당 메소드 Integer...params 배열
        @Override
        protected String doInBackground(Integer... params) {
            for(int i=params[0]; i<100; i++){
                Log.d("asyncTask","count:"+i);

                publishProgress((float)i);

                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return "task done!!!!";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = 
        MyTask task = new MyTask();
        task.execute(30);

    }
}
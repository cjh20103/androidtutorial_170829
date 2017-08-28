package com.example.a.t02_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int my_req_code_my1 = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                intent.putExtra("id","abcde");
                intent.putExtra("pw","123456");
                //startActivity(intent);
                startActivityForResult(intent,my_req_code_my1);
            }
        });
    }

    //데이터를 받아서 처리하는
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == my_req_code_my1){
            if(resultCode == RESULT_OK){
               String str =  data.getStringExtra("myResult");
                Toast.makeText(this,"result : "+str, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

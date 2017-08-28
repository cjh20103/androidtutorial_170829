package com.example.a.to1_widget;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;
    EditText myEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget);

        resultTextView  = (TextView) findViewById(R.id.resultTextView);
        myEdit = (EditText) findViewById(R.id.myEdit);
        // 반환 값이 view 타입이기때문에 형변환을 해야함
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.radio1:
                        // 간단한 정보를 알려주는 메소드
                        Toast.makeText(MainActivity.this, "radio1", Toast.LENGTH_SHORT).show();
                        resultTextView.setText("radio1 click");
                        break;
                    case R.id.radio2:
                        // 간단한 정보를 알려주는 메소드
                        Toast.makeText(MainActivity.this, "radio2", Toast.LENGTH_SHORT).show();
                        resultTextView.setText("radio2 click");
                        break;
                    case R.id.radio3:
                        // 간단한 정보를 알려주는 메소드
                        Toast.makeText(MainActivity.this, "radio3", Toast.LENGTH_SHORT).show();
                        resultTextView.setText("radio3 click");
                        break;
                }
            }
        });

        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = myEdit.getText().toString();
                resultTextView.setText(str);
            }
        });

        CheckBox myCheckBox = (CheckBox)findViewById(R.id.myCheckBox);
        myCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "checkbox value:"+isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

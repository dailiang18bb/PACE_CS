package com.example.charles.a2_9_test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //

        TextView textView = findViewById(R.id.text);

        SpannableString spanString = new SpannableString(textView.getText());  //getString(R.string.app_name)
        spanString.setSpan(new ForegroundColorSpan(Color.RED),0,4,0);
        spanString.setSpan(new RelativeSizeSpan(3.0f),2,7,0);

        textView.setText(spanString);


        //drawable
        findViewById(R.id.text).setBackgroundResource(R.drawable.bird);




    }
}

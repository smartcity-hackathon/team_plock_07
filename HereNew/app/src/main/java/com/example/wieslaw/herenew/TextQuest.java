package com.example.wieslaw.herenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TextQuest extends AppCompatActivity {
    TextView text;
    public static String tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_quest);
        text = findViewById(R.id.text_quest);

        text.setText(tx);
    }
}

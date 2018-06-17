package com.example.wieslaw.herenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImagedQuest extends AppCompatActivity {

    TextView text;
    ImageView image;
    public static String text_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imaged_quest);
        text = findViewById(R.id.quest_text_imaged);
        image = findViewById(R.id.quest_image);
        text.setText(text_);
        switch (MapFragmentView.Quest_id){
            case 1:
                image.setImageResource(R.drawable.zdj1);
                break;
        }
    }
}

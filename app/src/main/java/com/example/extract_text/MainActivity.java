package com.example.extract_text;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.display_image);
        button=findViewById(R.id.button);
        imageView=findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextFromImage();

            }
        });
    }
    public void getTextFromImage()
    {
        Bitmap bitmap= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.galaxy);
        TextRecognizer textRecognizer=new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Toast.makeText(this, "Could not get Text..sorry dear..", Toast.LENGTH_SHORT).show();
        }
        else {
            Frame frame=new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock>items=textRecognizer.detect(frame);
            StringBuilder stringBuilder=new StringBuilder();
            for (int i=0;i<items.size();++i) {
                TextBlock myitem=items.valueAt(i);
                stringBuilder.append(myitem.getValue());
                stringBuilder.append("\n");

            }
            textView.setText(stringBuilder.toString());
        }

    }
}
package com.example.matthewtucker.ist412application.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.matthewtucker.ist412application.R;
import com.example.matthewtucker.ist412application.Util.RunData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// Adam Warfield took the lead on this class with help from Matt Peron and Matt Tucker
public class CreatePhotoActivity extends AppCompatActivity {
    private Button exportPhotoButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_photo);

        int id = getBaseContext().getResources().getIdentifier("image2", "mimap", getBaseContext().getPackageName());
        imageView = (ImageView) findViewById(R.id.run_image_view);
        imageView.setImageResource(id);
        imageView.setVisibility(View.VISIBLE);
        RunData runData = RunData.getInstance();




        exportPhotoButton = (Button) findViewById(R.id.export_photo_button);
        exportPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Bitmap image1 = writeText("Average Speed:"+runData.getAverageSpeed(),imageView.getWidth(), Color.RED);
               imageView.setImageBitmap(image1);
               imageView.buildDrawingCache();
               Bitmap map = imageView.getDrawingCache();


                FileOutputStream out = null;
                File location = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File file  = new File(location, "image"+".jpg");

                try{
                    FileOutputStream out1 = new FileOutputStream(file);
                    map.compress(Bitmap.CompressFormat.JPEG, 100, out1);
                    out1.close();

                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(file));
                    sendBroadcast(intent);
                }
                catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
        });


    }

    public void scanFile(Context context, Uri imageUri){

    }

    public Bitmap writeText(String text, int width, int color){
        TextPaint textpaint = new TextPaint(Paint.ANTI_ALIAS_FLAG| Paint.LINEAR_TEXT_FLAG);
        textpaint.setStyle(Paint.Style.FILL);
        textpaint.setColor(Color.BLACK);
        StaticLayout textLayout = new StaticLayout(text, textpaint, width, Layout.Alignment.ALIGN_CENTER, 50.0f, 50.0f, false);

        Bitmap map = Bitmap.createBitmap(width, textLayout.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(map);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.LINEAR_TEXT_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawPaint(paint);

        canvas.save();
        canvas.translate(0,0);
        textLayout.draw(canvas);
        canvas.restore();

        return map;
    }

}

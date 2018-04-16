package com.example.matthewtucker.ist412application;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreatePhotoActivity extends AppCompatActivity {
    private Button exportPhotoButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int id = getBaseContext().getResources().getIdentifier("image2", "mimap", getBaseContext().getPackageName());
        imageView = (ImageView) findViewById(R.id.run_image_view);
        imageView.setImageResource(id);
        imageView.setVisibility(View.VISIBLE);



        exportPhotoButton = (Button) findViewById(R.id.export_photo_button);
        exportPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
      ;
    }

    public Bitmap writeText(String text, int width, int color){
        TextPaint textpaint = new TextPaint(Paint.ANTI_ALIAS_FLAG| Paint.LINEAR_TEXT_FLAG);
        textpaint.setStyle(Paint.Style.FILL);
        textpaint.setColor(Color.BLACK);
        StaticLayout textLayout = new StaticLayout(text, textpaint, width, Layout.Alignment.ALIGN_CENTER, 1.0f, 1.0f, false);

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

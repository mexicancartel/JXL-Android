package com.nmmedit.libavif;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {
            final ImageView avifImage = findViewById(R.id.avif_img);
            final byte[] bytes = inputStreamToBytes(getAssets().open("test.avif"));
            Glide.with(this).load(bytes).into(avifImage);
        }
        catch (IOException ignored)
        {

        }
    }

    private byte[] inputStreamToBytes(InputStream is)
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(64 * 1024);
        try
        {
            int nRead;
            byte[] data = new byte[16 * 1024];
            while ((nRead = is.read(data)) != -1)
            {
                buffer.write(data, 0, nRead);
            }
            buffer.close();
        }
        catch (IOException e)
        {
            return null;
        }
        return buffer.toByteArray();
    }
}
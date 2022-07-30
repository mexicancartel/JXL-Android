package com.zoffcc.applications.avifview;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class ShareActivity extends AppCompatActivity
{
    private static final String TAG = "ShareActivity";

    Intent intent;
    String action;
    String type;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        intent = getIntent();
        // Log.i(TAG, "onCreate:intent=" + intent);
        action = intent.getAction();
        type = intent.getType();

        try
        {
            if (Intent.ACTION_SEARCH.equals(action))
            {
                // Log.i(TAG, "onCreate:ACTION_SEARCH=" + action);
            }
            else if (Intent.ACTION_SEND.equals(action) && type != null)
            {
                // Log.i(TAG, "onCreate:ACTION_SEND=" + action);
            }
            else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null)
            {
                // Log.i(TAG, "onCreate:ACTION_SEND_MULTIPLE=" + action);
            }
            else if (Intent.ACTION_VIEW.equals(action))
            {
                // Log.i(TAG, "onCreate:ACTION_VIEW=" + action);

                Uri imageUri = intent.getData();
                if (imageUri != null)
                {
                    try
                    {
                        CircularProgressDrawable loading_animation_drawable = new CircularProgressDrawable(this);
                        loading_animation_drawable.setStyle(CircularProgressDrawable.LARGE);
                        loading_animation_drawable.setColorSchemeColors(Color.WHITE);
                        loading_animation_drawable.start();

                        final PhotoView photoView = (PhotoView) findViewById(R.id.avif_img);
                        // Log.i(TAG, "photoView=" + photoView + " imageUri=" + imageUri);
                        Glide.with(this).
                                load(imageUri).
                                diskCacheStrategy(DiskCacheStrategy.NONE).
                                skipMemoryCache(false).
                                placeholder(loading_animation_drawable).
                                fallback(R.drawable.ic_baseline_broken_image_24).
                                error(R.drawable.ic_baseline_error_outline_24).
                                into(photoView);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            else
            {
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // Log.i(TAG, "onCreate:EE:" + e.getMessage());
        }
    }
}
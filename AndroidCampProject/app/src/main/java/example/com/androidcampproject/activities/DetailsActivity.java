package example.com.androidcampproject.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.URL;

import de.greenrobot.event.EventBus;
import example.com.androidcampproject.R;

/**
 * Created by Esmond on 11.08.2015.
 */
public class DetailsActivity extends AppCompatActivity {

    public static Context detailsActivityContext;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        overridePendingTransition(R.animator.activity_open_translate, R.animator.activity_close_scale);

        String title = getIntent().getStringExtra("title");
        String image = getIntent().getStringExtra("image");

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        Glide.with(this).load(image).into(imageView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.animator.activity_open_scale, R.animator.activity_close_translate);
    }
}

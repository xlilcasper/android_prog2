package com.codepath.android.booksearch.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.android.booksearch.R;
import com.codepath.android.booksearch.models.Course;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CourseDetailActivity extends ActionBarActivity {
    private TextView courseTitle;
    private TextView courseAuthor;
    private TextView courseCredits;
    private TextView courseDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        // Fetch views
        courseTitle = (TextView) findViewById(R.id.courseTitle);
        courseAuthor = (TextView) findViewById(R.id.courseAuthor);
        courseCredits = (TextView) findViewById(R.id.courseCredits);
        courseDescription = (TextView) findViewById(R.id.courseDescription);

        // Use the course to populate the data into our views
        Course course = (Course) getIntent().getSerializableExtra(CourseListActivity.BOOK_DETAIL_KEY);
        loadCourse(course);
    }

    // Populate data for the course
    private void loadCourse(Course course) {
        //change activity title
        this.setTitle(course.getTitle());
        courseTitle.setText(course.getTitle());
        courseAuthor.setText(course.getInstructor());
        courseCredits.setText(course.getCredit());
        courseDescription.setText(course.getDescription());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_share) {
            setShareIntent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setShareIntent() {
        ImageView ivImage = (ImageView) findViewById(R.id.ivBookCover);
        final TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        // Get access to the URI for the bitmap
        Uri bmpUri = getLocalBitmapUri(ivImage);
        // Construct a ShareIntent with link to image
        Intent shareIntent = new Intent();
        // Construct a ShareIntent with link to image
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("*/*");
        shareIntent.putExtra(Intent.EXTRA_TEXT, (String)tvTitle.getText());
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        // Launch share menu
        startActivity(Intent.createChooser(shareIntent, "Share Image"));

    }

    // Returns the URI path to the Bitmap displayed in cover imageview
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
}

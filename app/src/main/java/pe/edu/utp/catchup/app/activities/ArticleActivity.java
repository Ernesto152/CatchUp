package pe.edu.utp.catchup.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import pe.edu.utp.catchup.R;
import pe.edu.utp.catchup.app.CatchUpApp;
import pe.edu.utp.catchup.backend.models.Article;

public class ArticleActivity extends AppCompatActivity {
    ANImageView pictureImageView;
    TextView titleTextView;
    TextView authorTextView;
    TextView descriptionTextView;
    ImageButton urlImageButton;
    ImageButton bookmarkImageButton;
    ImageButton bookmarkImageButton1;
    ANImageView sourceLogoImageView;
    boolean isBookmarked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pictureImageView = (ANImageView) findViewById(R.id.pictureImageView);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        authorTextView = (TextView) findViewById(R.id.authorTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        final Article currentArticle = CatchUpApp.getInstance().getCurrentArticle();
        pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        pictureImageView.setErrorImageResId(R.mipmap.ic_launcher);
        pictureImageView.setImageUrl(currentArticle.getUrlToImage());
        titleTextView.setText(currentArticle.getTitle());
        authorTextView.setText(currentArticle.getAuthorPhrase());
        descriptionTextView.setText(currentArticle.getDescription());
        urlImageButton = (ImageButton) findViewById(R.id.urlImageButton);
        urlImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(currentArticle.getUri());
                startActivity(browserIntent);
            }
        });
        sourceLogoImageView = (ANImageView) findViewById(R.id.sourceLogoImageView);
        sourceLogoImageView.setImageUrl(currentArticle.getSource().getUrlToSmallLogo());
        sourceLogoImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        sourceLogoImageView.setErrorImageResId(R.mipmap.ic_launcher);
        sourceLogoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), SourceActivity.class));
            }
        });
        bookmarkImageButton = (ImageButton) findViewById(R.id.bookmarkImageButton);
        bookmarkImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleImageButton(R.drawable.ic_bookmark_border_black_24dp,
                        R.drawable.ic_bookmark_black_24dp);
            }
        });
    }

    private void toggleImageButton(int inactiveResourceId, int activeResourceId) {
        isBookmarked = !isBookmarked;
        bookmarkImageButton.setImageResource(isBookmarked ? activeResourceId : inactiveResourceId);
    }
}

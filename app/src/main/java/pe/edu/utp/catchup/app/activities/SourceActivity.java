package pe.edu.utp.catchup.app.activities;

import android.content.Intent;
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
import pe.edu.utp.catchup.backend.models.Source;

public class SourceActivity extends AppCompatActivity {
    ANImageView logoImageView;
    TextView nameTextView;
    TextView descriptionTextView;
    TextView categoryTextView;
    TextView languageTextView;
    TextView countryTextView;
    ImageButton urlImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        logoImageView = (ANImageView) findViewById(R.id.logoImageView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        categoryTextView = (TextView) findViewById(R.id.categoryTextView);
        languageTextView = (TextView) findViewById(R.id.languageTextView);
        countryTextView = (TextView) findViewById(R.id.languageTextView);
        final Source currentSource = CatchUpApp.getInstance().getCurrentSource();
        logoImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        logoImageView.setErrorImageResId(R.mipmap.ic_launcher);
        logoImageView.setImageUrl(currentSource.getUrlToLargeLogo());
        nameTextView.setText(currentSource.getName());
        descriptionTextView.setText(currentSource.getDescription());
        categoryTextView.setText(currentSource.getCategory());
        languageTextView.setText(currentSource.getLanguage());
        countryTextView.setText(currentSource.getCountry());
        urlImageButton = (ImageButton) findViewById(R.id.urlImageButton);
        urlImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(currentSource.getUri());
                startActivity(browserIntent);
            }
        });
    }

}

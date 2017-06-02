package com.example.quad2.kickstarterprojects.activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quad2.kickstarterprojects.R;
import com.example.quad2.kickstarterprojects.pojo.ApiDatum;

public class ProjectDetailActivity extends AppCompatActivity {

    private TextView projectTitle, country, backers, pledgedAmount, location, blurb, owner;
    private LinearLayout visitLink;
    private ImageButton visitLinkBtn;
    private ApiDatum apiDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        // SupportActionBar to implement backwards
        getSupportActionBar().show();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeViews();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            apiDatum = bundle.getParcelable("projectDetail");
            setText();
        } else {
            projectTitle.setText("Something went wrong! Go Back.");
            country.setVisibility(View.GONE);
            location.setVisibility(View.GONE);
            blurb.setVisibility(View.GONE);
            owner.setVisibility(View.GONE);
            backers.setVisibility(View.GONE);
            pledgedAmount.setVisibility(View.GONE);
            visitLink.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        return true;
    }

    public void initializeViews() {
        visitLink = (LinearLayout) findViewById(R.id.visit_link);
        projectTitle = (TextView) findViewById(R.id.project_title);
        country = (TextView) findViewById(R.id.country);
        backers = (TextView) findViewById(R.id.backers);
        blurb = (TextView) findViewById(R.id.blurb);
        owner = (TextView) findViewById(R.id.by);
        location = (TextView) findViewById(R.id.location);
        pledgedAmount = (TextView) findViewById(R.id.pledged_amount);
        visitLinkBtn = (ImageButton) findViewById(R.id.visit_link_btn);
    }

    public void setText() {
        if (apiDatum != null) {
            if (apiDatum.getTitle() != null && apiDatum.getTitle().length() > 0) {
                projectTitle.setText(apiDatum.getTitle());
            }
            pledgedAmount.setText("Pledged Amount : " + String.valueOf(apiDatum.getAmtPledged()));

            if (apiDatum.getNumBackers() != null && apiDatum.getNumBackers().length() > 0) {
                backers.setText("Backers : " + apiDatum.getNumBackers());
            }
            if (apiDatum.getBlurb() != null && apiDatum.getBlurb().length() > 0) {
                blurb.setText("Blurb : " + apiDatum.getBlurb());
            }
            if (apiDatum.getBy() != null && apiDatum.getBy().length() > 0) {
                owner.setText("Owner : " + apiDatum.getBy());
            }
            if (apiDatum.getLocation() != null && apiDatum.getLocation().length() > 0) {
                location.setText("Location : " + apiDatum.getLocation());
            }
            if (apiDatum.getCountry() != null && apiDatum.getCountry().length() > 0) {
                country.setText("Country : " + apiDatum.getCountry());
            }

            visitLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openProjectPage();
                }
            });

            visitLinkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openProjectPage();
                }
            });
        }
    }

    public void openProjectPage() {
        if (apiDatum != null && apiDatum.getUrl() != null && apiDatum.getUrl().length() > 0) {
            Intent intent = new Intent(ProjectDetailActivity.this, WebViewActivity.class);
            intent.putExtra("url", apiDatum.getUrl());
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        } else {
            Toast.makeText(ProjectDetailActivity.this, "Sorry this project does not have any webpage!", Toast.LENGTH_SHORT).show();
        }
    }
}

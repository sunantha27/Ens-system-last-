package appewtc.masterung.enssystem;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class NewsDetail extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private TextView titleTextView, dateTextView, detailTextView,
            video1TextView , video2TextView;
    private ImageView newsImageView;
    private String titleString, dateString, detailString, urlNewsImageString, videoString;
    private Button backButton, videoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        //bindwidget
        bindwidget();

        //Show view
        showView();

        //button controller
        buttonController();



    } // mian method


    private void buttonController() {
        backButton.setOnClickListener(this);
        videoButton.setOnClickListener(this);


    }

    private void showView() {

        //Receive value From Intent
        titleString = getIntent().getStringExtra("Title");
        urlNewsImageString = getIntent().getStringExtra("Image");
        dateString = getIntent().getStringExtra("Date");
        detailString = getIntent().getStringExtra("Detail");
        videoString = getIntent().getStringExtra("Video");


        if (videoString.length() == 0) {
            //No video

            video1TextView.setVisibility(View.INVISIBLE);
            video2TextView.setVisibility(View.INVISIBLE);
        }

        titleTextView.setText(titleString);
        dateTextView.setText(dateString);
        detailTextView.setText(detailString);

        Picasso.with(NewsDetail.this)
                .load(urlNewsImageString)
                .resize(250, 125)
                .into(newsImageView);


    }//show view

    private void bindwidget() {

        titleTextView = (TextView) findViewById(R.id.txtTitleNewsDetail);
        dateTextView = (TextView) findViewById(R.id.txtDay);
        detailTextView = (TextView) findViewById(R.id.txtDetail);
        newsImageView = (ImageView) findViewById(R.id.imvDetail);
        backButton = (Button) findViewById(R.id.bttDetailBack);
        videoButton = (Button) findViewById(R.id.bttDetailVideo);
        video1TextView = (TextView) findViewById(R.id.textView26);
        video1TextView = (TextView) findViewById(R.id.textView27);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bttDetailBack:
                finish();
                break;
            case R.id.bttDetailVideo:

                //showVideoView();
                startActivity(new Intent(this, HubActivity.class));
                finish();

                break;
        } //switch
    } //onclick

    public void showVideoView(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(videoString));
        startActivity(intent);


    }//show video view
} //main class
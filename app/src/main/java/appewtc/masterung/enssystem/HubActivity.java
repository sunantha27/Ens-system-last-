package appewtc.masterung.enssystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HubActivity extends AppCompatActivity {

    //Explicit
    private TextView informTextView, newsTextView,
            searchTextView, staticTextView, callTextView;
    private boolean bolFlag = true; // Status ==> Eng
    private ImageView thaiImageView, engImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        //Bind Widget
        bindWidget();

    }   // Main Method

    public  void  clickNews(View view) {
        Intent intent = new Intent(HubActivity.this,NewsListView.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();

        engImageView.setVisibility(View.INVISIBLE);

    }

    public void clickInform(View view) {

        Intent intent = new Intent(HubActivity.this, InformActivity.class);
        String name = getIntent().getStringExtra("nameLogin");
        intent.putExtra("nameLogin", name);
        startActivity(intent);

    }

    public void clickTHAI(View view) {

        informTextView.setText(getResources().getString(R.string.th_infor));
        newsTextView.setText(getResources().getString(R.string.th_news));
        searchTextView.setText(getResources().getString(R.string.th_search));
        staticTextView.setText(getResources().getString(R.string.th_status));
        callTextView.setText(getResources().getString(R.string.th_call));

        changeThaiEng();

    }   // clickTHAI

    private void changeThaiEng() {
        bolFlag = !bolFlag;
        if (bolFlag) {
            //Eng
            engImageView.setVisibility(View.INVISIBLE);
            thaiImageView.setVisibility(View.VISIBLE);
        } else {
            //Thai
            engImageView.setVisibility(View.VISIBLE);
            thaiImageView.setVisibility(View.INVISIBLE);
        }
    }

    public void clickENG(View view) {

        informTextView.setText(getResources().getString(R.string.en_infor));
        newsTextView.setText(getResources().getString(R.string.en_news));
        searchTextView.setText(getResources().getString(R.string.en_search));
        staticTextView.setText(getResources().getString(R.string.en_status));
        callTextView.setText(getResources().getString(R.string.en_call));

        changeThaiEng();

    }   // clickENG

    private void bindWidget() {
        informTextView = (TextView) findViewById(R.id.textView9);
        newsTextView = (TextView) findViewById(R.id.textView10);
        searchTextView = (TextView) findViewById(R.id.textView11);
        staticTextView = (TextView) findViewById(R.id.textView12);
        callTextView = (TextView) findViewById(R.id.textView13);
        thaiImageView = (ImageView) findViewById(R.id.imageView5);
        engImageView = (ImageView) findViewById(R.id.imageView8);


    }

}   // Main Class

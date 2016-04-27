package appewtc.masterung.enssystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CallListView extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView cat0ImageView, cat1ImageView,
            cat2ImageView, cat3ImageView, cat4ImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_list_view);

        //Bind Widget
        bindWidget();

        //Image Controller
        imageController();

    }   // Main Method

    private void imageController() {
        cat0ImageView.setOnClickListener(this);
        cat1ImageView.setOnClickListener(this);
        cat2ImageView.setOnClickListener(this);
        cat3ImageView.setOnClickListener(this);
        cat4ImageView.setOnClickListener(this);
    }

    private void bindWidget() {
        cat0ImageView = (ImageView) findViewById(R.id.imageView10);
        cat1ImageView = (ImageView) findViewById(R.id.imageView11);
        cat2ImageView = (ImageView) findViewById(R.id.imageView12);
        cat3ImageView = (ImageView) findViewById(R.id.imageView13);
        cat4ImageView = (ImageView) findViewById(R.id.imageView14);
    }

    @Override
    public void onClick(View view) {

        int[] iconInts = {R.drawable.catagory1, R.drawable.catagory2,
                R.drawable.catagory3, R.drawable.catagory4, R.drawable.catagory5};
        int intUserChoose = R.drawable.catagory1;

        String[] callStrings, phoneStrings;


        switch (view.getId()) {

            case R.id.imageView10:
                intUserChoose = iconInts[0];
                callStrings = getResources().getStringArray(R.array.cat0_name);
                phoneStrings = getResources().getStringArray(R.array.cat0_phone);
                break;
            case R.id.imageView11:
                intUserChoose = iconInts[1];
                callStrings = getResources().getStringArray(R.array.cat1_name);
                phoneStrings = getResources().getStringArray(R.array.cat1_phone);
                break;
            case R.id.imageView12:
                intUserChoose = iconInts[2];
                callStrings = getResources().getStringArray(R.array.cat2_name);
                phoneStrings = getResources().getStringArray(R.array.cat2_phone);
                break;
            case R.id.imageView13:
                intUserChoose = iconInts[3];
                callStrings = getResources().getStringArray(R.array.cat3_name);
                phoneStrings = getResources().getStringArray(R.array.cat3_phone);
                break;
            case R.id.imageView14:
                intUserChoose = iconInts[4];
                callStrings = getResources().getStringArray(R.array.cat4_name);
                phoneStrings = getResources().getStringArray(R.array.cat4_phone);
                break;
            default:
                callStrings = getResources().getStringArray(R.array.cat0_name);
                phoneStrings = getResources().getStringArray(R.array.cat0_phone);
                break;

        }   // switch

        Intent intent = new Intent(CallListView.this, CallPhone.class);
        intent.putExtra("Icon", intUserChoose);
        intent.putExtra("Call", callStrings);


        startActivity(intent);

    }   // onClick

}   // Main Class
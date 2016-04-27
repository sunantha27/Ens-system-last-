package appewtc.masterung.enssystem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CallPhone extends AppCompatActivity {

    //Explicit
    private LinearLayout topLinearLayout;
    private ListView phoneListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);

        //Bind Widget
        bindWidget();

        //Show Choose
        showChoose();

        //Create ListView
        createListView();


    }   // Main Method
    public  void clickBackPhoneCall(View view){
        finish();
    }

    private void createListView() {
        String[] callStrings = getIntent().getStringArrayExtra("Call");
        final String[] phoneStrings = getIntent().getStringArrayExtra("Phone");

        PhoneAdapter phoneAdapter = new PhoneAdapter(CallPhone.this, callStrings, phoneStrings);
        phoneListView.setAdapter(phoneAdapter);
        phoneListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                myCallPhone(phoneStrings[i]);



            }//onItemClick


        });

    }//createListView

    private void myCallPhone(String phoneString) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneString));
        startActivity(intent);


    }//myCallPhone

    private void showChoose() {
        int intBackGround = getIntent().getIntExtra("Icon", R.drawable.catagory1);
        topLinearLayout.setBackgroundResource(intBackGround);
    }

    private void bindWidget() {
        topLinearLayout = (LinearLayout) findViewById(R.id.linTopCallPhone);
        phoneListView = (ListView) findViewById(R.id.listView2);

    }

}   // Main Class
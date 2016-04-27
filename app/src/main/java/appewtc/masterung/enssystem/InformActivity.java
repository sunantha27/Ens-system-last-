package appewtc.masterung.enssystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class InformActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private TextView showNameTextView;
    private DatePicker datePicker;
    private EditText phoneEditText, detailEditText;
    private Spinner typeSpinner, districtSpinner;
    private Button addPhotoButton, addVideoButton,
            addLocationButton, updateToSQLButton;
    private String nameLoginString, dateString, phoneString, detailString,
            typeString, photoURLString, videoURLString, latString, lngString ,districtString;
    private double latADouble, lngADouble;

    public  String[] districtStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        //Bind Widget
        bindWidget();

        //Show Name
        showName();

        typeController();
        districtController();

        //Button Controller
        buttonController();

        showLatLng();

    }   // Main Method

    private void districtController() {

            districtStrings = new String[11];
            districtStrings[0] = "อำเภอเมือง";
            districtStrings[1] = "อำเภอบางคล้า";
            districtStrings[2] = "อำเภอบางน้ำเปรี้ยว";
            districtStrings[3] = "อำเภอบางปะกง";
            districtStrings[4] = "อำเภอบ้านโพธิ์";
            districtStrings[5] = "อำเภอพนมสารคาม";
            districtStrings[6] = "อำเภอราชสาส์น";
            districtStrings[7] = "อำเภอสนามชัยเขต";
            districtStrings[8] = "อำเภอแปลงยาว";
            districtStrings[9] = "อำเภอท่าตะเกียบ";
            districtStrings[10] = "อำเภอคลองเขื่อน";

            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, districtStrings);

            districtSpinner.setAdapter(stringArrayAdapter);

            districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    districtString = districtStrings[i];

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    districtString = districtStrings[0];


                }
            });

        }//districtController


    @Override
    protected void onRestart(){
        super.onRestart();

        if (latADouble != 0) {
            TextView showLocation = (TextView) findViewById(R.id.textView20);
            showLocation.setText("Have Location Finish");
        }

    }

    private void showLatLng() {

        latADouble = getIntent().getDoubleExtra("douLat", 0);
        lngADouble = getIntent().getDoubleExtra("douLng", 0);


        Log.d("test", "Lat = " + latADouble);
        Log.d("test", "Lng = " + lngADouble);
    }


    @Override
    protected void onStart() {
        super.onStart();

        latADouble = getIntent().getDoubleExtra("douLat", 0);
        lngADouble = getIntent().getDoubleExtra("douLng", 0);
        if (latADouble != 0) {
            TextView showLocation = (TextView) findViewById(R.id.textView20);
            showLocation.setText("Have Location Finish");
        }

    }

    private void buttonController() {

        addPhotoButton.setOnClickListener(this);
        addVideoButton.setOnClickListener(this);
        addLocationButton.setOnClickListener(this);
        updateToSQLButton.setOnClickListener(this);

    }   // buttonController

    private void showName() {

        nameLoginString = getIntent().getStringExtra("nameLogin");
        showNameTextView.setText(getResources().getString(R.string.th_infor) +
                " โดย " +
                nameLoginString);

    }   // showName

    private void bindWidget() {

        showNameTextView = (TextView) findViewById(R.id.textView16);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        phoneEditText = (EditText) findViewById(R.id.editText7);
        detailEditText = (EditText) findViewById(R.id.editText11);
        typeSpinner = (Spinner) findViewById(R.id.spinner);
        addPhotoButton = (Button) findViewById(R.id.button4);
        addVideoButton = (Button) findViewById(R.id.button9);
        addLocationButton = (Button) findViewById(R.id.button11);
        updateToSQLButton = (Button) findViewById(R.id.button5);
        districtSpinner = (Spinner)findViewById(R.id.spinner2);

    }   // bindWidget

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button4:
                //Add Photo
                break;
            case R.id.button9:
                //Add Video
                break;
            case R.id.button11:
                //Add Location
                Intent intent = new Intent(InformActivity.this, MapsActivity.class);
                intent.putExtra("nameLogin", nameLoginString);
                startActivity(intent);
                finish();

                break;
            case R.id.button5:
                //Update

                phoneString = phoneEditText.getText().toString();
                detailString = detailEditText.getText().toString();

                if (phoneString.equals("") || detailString.equals("")) {
                    MyAlertDialog myAlertDialog = new MyAlertDialog();
                    myAlertDialog.MyDialog(InformActivity.this, R.drawable.icon_question,
                            "มีช่องว่าง", "กรุณากรอกให้ครบ คะ");
                } else {

                    if ((latADouble == 0) || (lngADouble == 0)) {

                        MyAlertDialog myAlertDialog = new MyAlertDialog();
                        myAlertDialog.MyDialog(InformActivity.this, R.drawable.icon_question,
                                "ยังไม่เลือกพิกัด", "กรุณาเลือก พิกัด");

                    } else {
                        getDateFromDatePicker();

                        getLatLng();

                        showLog();
                    }

                }

                break;

        } // switch

    }   // onClick

    private void getLatLng() {
        latString = Double.toString(latADouble);
        lngString = Double.toString(lngADouble);
    }

    private void typeController() {

        final String[] categoryStrings = new String[4];
        categoryStrings[0] = "อุบัติเหตุ";
        categoryStrings[1] = "ไฟไหม้";
        categoryStrings[2] = "แผ่นดินไหว";
        categoryStrings[3] = "น้ำท่วม";

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, categoryStrings);
        typeSpinner.setAdapter(stringArrayAdapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeString = categoryStrings[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                typeString = categoryStrings[0];
            }
        });

    }   // typeContorller



    private void getDateFromDatePicker() {

        int intDay = datePicker.getDayOfMonth();
        int intMonth = datePicker.getMonth() + 1;
        int intYear = datePicker.getYear();

        dateString = Integer.toString(intDay) + "/" +
                Integer.toString(intMonth) + "/" + Integer.toString(intYear);

    }   // getDateFromDatePicker

    private void showLog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.logo48);
        builder.setTitle("โปรดตรวจสอบข้อมูล");
        builder.setMessage("ผู้แจ้งเหตุ = " + nameLoginString + "\n" +
                "วันที่เกิดเหตุ = " + dateString + "\n" +
                "เบอร์ของผู้แจ้ง = " + phoneString + "\n" +
                "ประเภทเหตุการณ์ = " + typeString + "\n" +
                "อำเภอ =" + districtString +"\n" +
                "รายละเอียด = " + detailString + "\n" +
                "ละติจุต = " + latString + "\n" +
                "ลองจิจูต = " + lngString);
        builder.setCancelable(false);
        builder.setPositiveButton("แจ้งเหตุ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                updateDataToServer();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("แก้ไข", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }   // showLog

    private void updateDataToServer() {

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        try {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            nameValuePairs.add(new BasicNameValuePair("Day_Inform", dateString));
            nameValuePairs.add(new BasicNameValuePair("Phone_Inform", phoneString));
            nameValuePairs.add(new BasicNameValuePair("Type_Inform", typeString));
            nameValuePairs.add(new BasicNameValuePair("District", districtString));
            nameValuePairs.add(new BasicNameValuePair("Detail_Inform", detailString));
            nameValuePairs.add(new BasicNameValuePair("Photo_Inform", "testPhoto"));
            nameValuePairs.add(new BasicNameValuePair("Video_Inform", "testVideo"));
            nameValuePairs.add(new BasicNameValuePair("Latitude", latString));
            nameValuePairs.add(new BasicNameValuePair("Longitude", lngString));
            nameValuePairs.add(new BasicNameValuePair("User_ID", nameLoginString));
            nameValuePairs.add(new BasicNameValuePair("statusinform", "noData"));

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://swiftcodingthai.com/ens/php_add_inform_master.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpClient.execute(httpPost);

            Toast.makeText(InformActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(InformActivity.this, "Cannot Update to mySQL", Toast.LENGTH_SHORT).show();
        }


    }   // updateDataToServer

}   // Main Class

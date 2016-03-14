package appewtc.masterung.enssystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText, nameEditText, surnameEditText,
            phoneEditText, emailEditText, confirmPassEditText;
    private RadioGroup sexRadioGroup;
    private String userString, passwordString, nameString, surnameString,
            phoneString, emailString, sexString, confirmPassString;
    private boolean statusABoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindWidget();

        //RadioGroup Controller
        ragController();

    }   // Main Method

    private void ragController() {

        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkID) {

                statusABoolean = false;

                switch (checkID) {
                    case R.id.radioButton:
                        sexString = getResources().getString(R.string.male);
                        break;
                    case R.id.radioButton2:
                        sexString = getResources().getString(R.string.female);
                        break;
                }   // switch

            }   // event
        });

    }   // ragController

    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        nameEditText = (EditText) findViewById(R.id.editText5);
        surnameEditText = (EditText) findViewById(R.id.editText6);
        phoneEditText = (EditText) findViewById(R.id.editText8);
        emailEditText = (EditText) findViewById(R.id.editText9);
        sexRadioGroup = (RadioGroup) findViewById(R.id.ragSex);
        confirmPassEditText = (EditText) findViewById(R.id.editText10);


    }   // bindWidget

    public void clickSignUpSave(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        confirmPassString = confirmPassEditText.getText().toString().trim();

        if (checkSpace() || statusABoolean) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.MyDialog(SignUpActivity.this,
                    R.drawable.icon_question, "กรอกข้อมูลไม่ครบ",
                    "กรุณากรอกข้อมูล และ เลือกข้อมูล ให้ครบ คะ");
        } else {
            //No Space
            if (checkConfirmPass()) {
                //Password OK
                checkUser();
            } else {
                //Password False
                MyAlertDialog myAlertDialog = new MyAlertDialog();
                myAlertDialog.MyDialog(SignUpActivity.this,
                        R.drawable.icon_question,
                        "Password พิมพ์ผิด",
                        "กรุณาพิมพ์ Password ให้เหมือนกัน");
            }


        }   // if


    }   // clickSignUpSave

    private boolean checkConfirmPass() {

        boolean bolPassword = true;

        if (passwordString.equals(confirmPassString)) {
            bolPassword = true;
        } else {
            bolPassword = false;
        }

        return bolPassword;
    }

    private void checkUser() {

        ManageTABLE objManageTABLE = new ManageTABLE(this);

        try {

            String[] resultStrings = objManageTABLE.searchUser(userString);
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.MyDialog(SignUpActivity.this, R.drawable.icon_question,
                    "User ซ้ำ", "User " + resultStrings[1] + " ซ้ำ โปรดเปลี่ยน User ใหม่ ?");

        } catch (Exception e) {
            confirmSignUp();
        }

    }   // checkUser

    private void confirmSignUp() {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.logo48);
        objBuilder.setTitle("โปรดตรวจข้อมูล");
        objBuilder.setMessage("User = " + userString + "\n" +

                "ชื่อ = " + nameString + "\n" +
                "นามสกุล = " + surnameString + "\n" +
                "เพศ = " + sexString + "\n" +
                "เบอร์โทร = " + phoneString + "\n" +
                "Email = " + emailString);
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                updateValueToMySQL();
            }
        });
        objBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });



        objBuilder.show();
    }   // confirmSignUp

    private void updateValueToMySQL() {

        //Set Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        //Update to MySQL
        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            objNameValuePairs.add(new BasicNameValuePair("User", userString));
            objNameValuePairs.add(new BasicNameValuePair("Password", passwordString));
            objNameValuePairs.add(new BasicNameValuePair("Name", nameString));
            objNameValuePairs.add(new BasicNameValuePair("Surname", surnameString));
            objNameValuePairs.add(new BasicNameValuePair("Sex", sexString));
            objNameValuePairs.add(new BasicNameValuePair("Phone", phoneString));
            objNameValuePairs.add(new BasicNameValuePair("Email", emailString));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/ens/php_add_data_master.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);

            Toast.makeText(SignUpActivity.this, "Update New Value Success",
                    Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(SignUpActivity.this, "Cannot Connected Internet",
                    Toast.LENGTH_SHORT).show();
        }


    }   // updateValueToMySQL

    private boolean checkSpace() {
        return userString.equals("") ||
                passwordString.equals("") ||
                nameString.equals("") ||
                surnameString.equals("") ||
                phoneString.equals("") ||
                emailString.equals("") ||
                confirmPassString.equals("");
    }

}   // Main Class

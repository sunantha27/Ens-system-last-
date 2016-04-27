package appewtc.masterung.enssystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StaticDistrict extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_district);
    }//Main Method

    public void clickBackStaticDistrict(View view) {
        finish();
    }
}//Main Class

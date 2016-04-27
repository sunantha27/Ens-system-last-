package appewtc.masterung.enssystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseTypeGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type_graph);
    }//Main Method

    public void clickBackChooesTybe(View view) {
        finish();
    }

    public void clickChoose1(View view) {
        startActivity(new Intent(ChooseTypeGraph.this, StaticActivity.class));

    }
    public void clickChoose2(View view) {

    }
}//Main Class

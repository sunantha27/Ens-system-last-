package appewtc.masterung.enssystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class NewListView extends AppCompatActivity {


    //Explicit
    private ListView newListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list_view);

        //Bind Widdget
        newListView = (ListView) findViewById(R.id.listView);




    }//Main Method
    public void clickBackNewsList(View view) {
        finish();
    }


}// Main Class
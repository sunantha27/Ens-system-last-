package appewtc.masterung.enssystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class NewsListView extends AppCompatActivity {


    //Explicit
    private ListView newListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list_view);

        //Bind Widdget
        newListView = (ListView) findViewById(R.id.listView);

     //Create ListView
        createView();


    }//Main Method

    private void createView() {

        //Read All Data from SQLite
        int intDigit = 30; //จำนวนตัวอักษรที่จะอ่านมาแสดงที่ Title

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManageTABLE.TABLE_newsTABLE, null);
        cursor.moveToFirst();

        int intCount = cursor.getCount();
        String[] titleFullStrings = new String[intCount];
        String[] titleShortStrings =new String[intCount];
        String[] dateStrings = new String[intCount];
        String[] photoNewStrings = new String[intCount];

        for (int i = 0; i < intCount; i++){
                titleFullStrings[i] = cursor.getString(cursor.getColumnIndex(ManageTABLE.COLUMN_Title_News));
                dateStrings[i] = cursor.getString(cursor.getColumnIndex(ManageTABLE.COLUMN_Day_News));
            photoNewStrings[i] = cursor.getString(cursor.getColumnIndex(ManageTABLE.COLUMN_Photo_News));
            titleShortStrings[i] = titleFullStrings[i].substring(0,intDigit);
                cursor.moveToNext();

        } //for

             cursor.close();
        NewsAdapter newsAdapter = new NewsAdapter(NewsListView.this,
                titleShortStrings, dateStrings, photoNewStrings);
        newListView.setAdapter(newsAdapter);



    } //create ListView

    public void clickBackNewsList(View view) {
        finish();
    }


}// Main Class
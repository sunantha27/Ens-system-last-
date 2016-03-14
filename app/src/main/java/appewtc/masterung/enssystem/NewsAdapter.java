package appewtc.masterung.enssystem;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by User on 14/3/2559.
 */
public class NewsAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private  String[]titleStrings, dateStrings, iconStrings;

    public NewsAdapter(Context context, String[] titleStrings, String[] dateStrings, String[] iconStrings) {
        this.context = context;
        this.titleStrings = titleStrings;
        this.dateStrings = dateStrings;
        this.iconStrings = iconStrings;
    }//Consturctor


    @Override
    public int getCount() {
        return titleStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view1 = layoutInflater.inflate(R.layout.news_listview, viewGroup, false);
        //Title
        TextView titleTextView = (TextView) view1.findViewById(R.id.textView22);
        titleTextView.setText(titleStrings[i]);
        //Date
        TextView dateTextView =(TextView) view1.findViewById(R.id.textView23);
        dateTextView.setText(dateStrings[i]);

        //Icon
        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView9);
        Picasso.with(context).load(iconStrings[i]).resize(200, 100).into(iconImageView);

        return view1;
    }
}// Main Class
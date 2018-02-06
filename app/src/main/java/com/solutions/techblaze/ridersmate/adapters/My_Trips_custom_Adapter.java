package com.solutions.techblaze.ridersmate.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.solutions.techblaze.ridersmate.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techblaze on 23/01/18.
 */

public class My_Trips_custom_Adapter extends BaseAdapter {

    String [] result;
    List<String> title=new ArrayList<String>();
    List<String> host_by=new ArrayList<String>();
    Context context;
  //  int img[]={R.drawable.workbook,R.drawable.assignment,R.drawable.message,R.drawable.leave,R.drawable.alert,R.drawable.homework,R.drawable.library_icon,R.drawable.timetable, R.drawable.test};
    private static LayoutInflater inflater=null;


    public My_Trips_custom_Adapter(Activity activity,List<String> title,List<String> host_by)
    {
        this.title=title;
        this.host_by=host_by;
        this.context=activity;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int i) {
        return title.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        rowView = inflater.inflate(R.layout.my_trip_list_item, null);
        ImageView img_view=(ImageView)rowView.findViewById(R.id.imageView_trip);
      //  img_view.setImageResource(img[i]);
        TextView title_text=(TextView)rowView.findViewById(R.id.trip_title);
        TextView hostby=(TextView)rowView.findViewById(R.id.host_by);
        title_text.setText(title.get(i));
        hostby.setText("Hosted By : "+host_by.get(i));




        return rowView;
    }
}

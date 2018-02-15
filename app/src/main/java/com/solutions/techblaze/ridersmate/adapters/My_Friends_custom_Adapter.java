package com.solutions.techblaze.ridersmate.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.solutions.techblaze.ridersmate.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techblaze on 23/01/18.
 */

public class My_Friends_custom_Adapter extends BaseAdapter {

    String [] result;
    List<String> user_names=new ArrayList<String>();
    List<String> place_names=new ArrayList<String>();
    List<String> fb_id=new ArrayList<String>();
    Context context;
    private ProfilePictureView profilePictureView;
  //  int img[]={R.drawable.workbook,R.drawable.assignment,R.drawable.message,R.drawable.leave,R.drawable.alert,R.drawable.homework,R.drawable.library_icon,R.drawable.timetable, R.drawable.test};
    private static LayoutInflater inflater=null;


    public My_Friends_custom_Adapter(Activity activity, List<String> user_names, List<String> place_names,List<String> fb_id)
    {
        this.user_names=user_names;
        this.place_names=place_names;
        this.fb_id=fb_id;
        this.context=activity;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return user_names.size();
    }

    @Override
    public Object getItem(int i) {
        return user_names.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        rowView = inflater.inflate(R.layout.friends_item, null);
      //  ImageView img_view=(ImageView)rowView.findViewById(R.id.imageView_trip);
      //  img_view.setImageResource(img[i]);
        TextView u_names=(TextView)rowView.findViewById(R.id.u_name);
        TextView place_name =(TextView)rowView.findViewById(R.id.place_name);
        profilePictureView = (ProfilePictureView)rowView.findViewById(R.id.user_profile);
        u_names.setText(user_names.get(i));
        place_name.setText(place_names.get(i));
        profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
        profilePictureView.setProfileId(fb_id.get(i));
       // hostby.setText("Hosted By : "+host_by.get(i));




        return rowView;
    }
}

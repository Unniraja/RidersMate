package com.solutions.techblaze.ridersmate.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.solutions.techblaze.ridersmate.R;
import com.solutions.techblaze.ridersmate.interfaces.CustomItemClickListener;
import com.solutions.techblaze.ridersmate.models.Home_new_feed;
import com.solutions.techblaze.ridersmate.models.My_Event_Model;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by techblaze on 23/01/18.
 */

public class My_Trips_Adapter extends RecyclerView.Adapter<My_Trips_Adapter.ViewHolder> {

    private List<My_Event_Model> feed_list;
    int flag=0;
  //  String url="http://192.168.4.104/Riders/images/new_posts/";
    private Context context;
    CustomItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView caption;




        public MyViewHolder(View view) {
            super(view);

           caption = (TextView) view.findViewById(R.id.ride_title);


        }
    }


    public My_Trips_Adapter(List<My_Event_Model> feed_list,CustomItemClickListener listener) {
        this.feed_list = feed_list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_new_item, parent, false);
        final ViewHolder mViewHolder = new ViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        My_Event_Model feed = feed_list.get(position);


        holder.itemTitle.setText(feed.getCaption());



    }

    @Override
    public int getItemCount() {
        return feed_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public ImageView thumbnailImage;

        ViewHolder(View v) {
            super(v);
            itemTitle = (TextView) v
                    .findViewById(R.id.ride_title);
           // thumbnailImage = (ImageView) v.findViewById(R.id.post_thumb_image);
        }
    }
}
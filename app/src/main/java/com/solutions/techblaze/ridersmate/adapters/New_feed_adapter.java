package com.solutions.techblaze.ridersmate.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;
import com.solutions.techblaze.ridersmate.R;
import com.solutions.techblaze.ridersmate.models.Home_new_feed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techblaze on 23/01/18.
 */

public class New_feed_adapter extends RecyclerView.Adapter<New_feed_adapter.MyViewHolder> {

    private List<Home_new_feed> feed_list;
    int flag=0;
    String url="http://192.168.4.104/Riders/images/new_posts/";
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView like_option, caption,name,optios;
        public ImageView post_image;
        public ProfilePictureView profilePictureView;


        public MyViewHolder(View view) {
            super(view);
           like_option = (TextView) view.findViewById(R.id.like_option);
           caption = (TextView) view.findViewById(R.id.caption_text);
           name = (TextView) view.findViewById(R.id.name);
           post_image=(ImageView)view.findViewById(R.id.post_img);
           optios=(TextView)view.findViewById(R.id.textViewOptions);
//            year = (TextView) view.findViewById(R.id.year);
            profilePictureView=(ProfilePictureView)view.findViewById(R.id.profilePictureView);
        }
    }


    public New_feed_adapter(List<Home_new_feed> feed_list) {
        this.feed_list = feed_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_feed_item, parent, false);
        context = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Home_new_feed feed = feed_list.get(position);

        holder.profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
        holder.profilePictureView.setProfileId(feed.getProPic());
        holder.caption.setText(feed.getCaption());
        holder.name.setText(feed.getName());
        Picasso.with(context).load(url+feed.getImg_name()).into(holder.post_image);
        holder.like_option.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(flag==0)
                {
                    holder.like_option.setTextColor(R.color.likecolor);
                    holder.like_option.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_loved, 0, 0, 0);
                    flag=1;
                }
                else if (flag==1)
                {
                    holder.like_option.setTextColor(Color.GRAY);
                    holder.like_option.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_normal_love, 0, 0, 0);
                    flag=0;
                }

            }
        });
        holder.optios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();

                PopupMenu popup = new PopupMenu(context, holder.optios);
                //inflating menu from xml resource
                popup.inflate(R.menu.side_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.share:
                                //handle menu1 click
                                break;
                            case R.id.hide:
                                //handle menu2 click
                                break;

                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();



            }
        });

    }

    @Override
    public int getItemCount() {
        return feed_list.size();
    }
}
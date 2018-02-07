package com.solutions.techblaze.ridersmate;

/**
 * Created by techblaze on 11/01/18.
 */
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayList;

public class ProfileFragment extends android.support.v4.app.Fragment {
    private ProfilePictureView profilePictureView;
    SharedPreferences prefs;
    String user_id,user_name;
    TextView u_name;
    private ListView lvCountry;

    public static ProfileFragment newInstance() {

        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
      //  return inflater.inflate(R.layout.fragment_profile, container, false);
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        profilePictureView = (ProfilePictureView)v.findViewById(R.id.user_profile);
        u_name=(TextView)v.findViewById(R.id.rider_name);
        prefs = getActivity().getSharedPreferences("Login", 0);
        user_id=prefs.getString("uid", null);
        u_name.setText(prefs.getString("u_name", null));
        profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
        profilePictureView.setProfileId(user_id);



        lvCountry = (ListView)v.findViewById(R.id.profile_list);

        ArrayList<Item> countryList = new ArrayList<Item>();
        // Header
        countryList.add(new SectionItem("General"));
        // State Name
        countryList.add(new EntryItem("Edit Profile"));
        countryList.add(new EntryItem("Edit Medical Records"));
        countryList.add(new EntryItem("Manage Motor Cycles"));
        countryList.add(new EntryItem("Emergency Contacts"));

        // Header
        countryList.add(new SectionItem("Manage Documents"));
        // State Name
        countryList.add(new EntryItem("Vehicle insurance"));
        countryList.add(new EntryItem("Pollution Certificate"));
        countryList.add(new EntryItem("Vehicle Details"));
        countryList.add(new EntryItem("Logout"));

        // Header


        // set adapter
        final CountryAdapter adapter = new CountryAdapter(getActivity(), countryList);
        lvCountry.setAdapter(adapter);
        lvCountry.setTextFilterEnabled(true);
        lvCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 1:
                        Intent edit_prof=new Intent(getActivity(),Edit_User_details.class);
                        startActivity(edit_prof);
                        break;

                    case 3:
                        Intent motor=new Intent(getActivity(),Manage_Motorcycle.class);
                        startActivity(motor);
                        break;
                    case 6:
                        Intent insurance=new Intent(getActivity(),Manage_Insurance_Certificate.class);
                        startActivity(insurance);
                        break;
                    case 7:
                        Intent pollution=new Intent(getActivity(),Manage_Polution_Certificate.class);
                        startActivity(pollution);
                        break;
                }
            }
        });


        return v;
    }


    /**
     * row item
     */
    public interface Item {
        public boolean isSection();
        public String getTitle();
    }

    /**
     * Section Item
     */
    public class SectionItem implements Item {
        private final String title;

        public SectionItem(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public boolean isSection() {
            return true;
        }
    }

    /**
     * Entry Item
     */
    public class EntryItem implements Item {
        public final String title;

        public EntryItem(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public boolean isSection() {
            return false;
        }
    }

    /**
     * Adapter
     */
    public class CountryAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Item> item;
        private ArrayList<Item> originalItem;

        public CountryAdapter() {
            super();
        }

        public CountryAdapter(Context context, ArrayList<Item> item) {
            this.context = context;
            this.item = item;
            //this.originalItem = item;
        }

        @Override
        public int getCount() {
            return item.size();
        }

        @Override
        public Object getItem(int position) {
            return item.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (item.get(position).isSection()) {
                // if section header
                convertView = inflater.inflate(R.layout.layout_section, parent, false);
                TextView tvSectionTitle = (TextView) convertView.findViewById(R.id.tvSectionTitle);
                tvSectionTitle.setText(((SectionItem) item.get(position)).getTitle());
            }
            else
            {
                // if item
                convertView = inflater.inflate(R.layout.layout_item, parent, false);
                TextView tvItemTitle = (TextView) convertView.findViewById(R.id.tvItemTitle);

                tvItemTitle.setText(((EntryItem) item.get(position)).getTitle());
            }

            return convertView;
        }

        /**
         * Filter
         */
//        public Filter getFilter()
//        {
//            Filter filter = new Filter() {
//
//                @SuppressWarnings("unchecked")
//                @Override
//                protected void publishResults(CharSequence constraint, FilterResults results) {
//
//                    item = (ArrayList<Item>) results.values;
//                    notifyDataSetChanged();
//                }
//
//                @SuppressWarnings("null")
//                @Override
//                protected FilterResults performFiltering(CharSequence constraint) {
//
//                    FilterResults results = new FilterResults();
//                    ArrayList<Item> filteredArrayList = new ArrayList<Item>();
//
//
//                    if(originalItem == null || originalItem.size() == 0)
//                    {
//                        originalItem = new ArrayList<Item>(item);
//                    }
//
//                    /*
//                     * if constraint is null then return original value
//                     * else return filtered value
//                     */
//                    if(constraint == null && constraint.length() == 0)
//                    {
//                        results.count = originalItem.size();
//                        results.values = originalItem;
//                    }
//                    else
//                    {
//                        constraint = constraint.toString().toLowerCase(Locale.ENGLISH);
//                        for (int i = 0; i < originalItem.size(); i++)
//                        {
//                            String title = originalItem.get(i).getTitle().toLowerCase(Locale.ENGLISH);
//                            if(title.startsWith(constraint.toString()))
//                            {
//                                filteredArrayList.add(originalItem.get(i));
//                            }
//                        }
//                        results.count = filteredArrayList.size();
//                        results.values = filteredArrayList;
//                    }
//
//                    return results;
//                }
//            };
//
//            return filter;
//        }
    }


}
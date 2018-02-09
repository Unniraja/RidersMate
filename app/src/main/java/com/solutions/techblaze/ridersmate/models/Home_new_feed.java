package com.solutions.techblaze.ridersmate.models;

/**
 * Created by techblaze on 09/02/18.
 */


public class Home_new_feed {
    private String user_id, genre, year;

    public Home_new_feed() {
    }

    public Home_new_feed(String user_id) {
        this.user_id = user_id;
//        this.genre = genre;
//        this.year = year;
    }

    public String getProPic() {
        return user_id;
    }

    public void setProPic(String u_id) {
        this.user_id = u_id;
    }

}
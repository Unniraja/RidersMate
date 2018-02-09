package com.solutions.techblaze.ridersmate.models;

/**
 * Created by techblaze on 09/02/18.
 */


public class Home_new_feed {
    private String user_id,caption,img_name,name;

    public Home_new_feed() {
    }

    public Home_new_feed(String user_id,String caption,String img_name,String name) {
        this.user_id = user_id;
        this.caption = caption;
        this.img_name = img_name;
        this.name=name;
    }

    public String getProPic() {
        return user_id;
    }

    public void setProPic(String u_id) {
        this.user_id = u_id;
    }
    public String getCaption() {
        return caption;
    }

    public void setCaption(String cap) {
        this.caption = cap;
    }
    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String im_name) {
        this.img_name = im_name;
    }
    public String getName() {
        return name;
    }

    public void setName(String nme) {
        this.name =nme;
    }

}
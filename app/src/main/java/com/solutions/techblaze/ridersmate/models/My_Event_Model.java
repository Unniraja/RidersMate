package com.solutions.techblaze.ridersmate.models;

/**
 * Created by techblaze on 09/02/18.
 */


public class My_Event_Model {
    private String user_id,caption,img_name,name;

    public My_Event_Model() {
    }

    public My_Event_Model(String caption) {

        this.caption = caption;

    }


    public String getCaption() {
        return caption;
    }

    public void setCaption(String cap) {
        this.caption = cap;
    }


}
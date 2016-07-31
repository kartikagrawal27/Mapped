package com.mapped;

/**
 * Created by Pegasus on 7/28/16.
 */
public class NewsFeedEvents {

    String imageCode;
    String orgName;
    String desc;



    String eventKey;

    public String getImageCode() {
        return imageCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getDesc() {
        return desc;
    }

    public String getEventKey() {
        return eventKey;
    }

    public NewsFeedEvents(String imageCode, String orgName, String desc, String eventKey) {
        this.imageCode = imageCode;
        this.orgName = orgName;
        this.desc = desc;
        this.eventKey = eventKey;
    }


}
